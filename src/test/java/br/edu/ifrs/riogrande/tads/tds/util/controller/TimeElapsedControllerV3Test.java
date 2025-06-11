package br.edu.ifrs.riogrande.tads.tds.util.controller;

import br.edu.ifrs.riogrande.tads.tds.util.dto.TimeElapsedRequest;
import br.edu.ifrs.riogrande.tads.tds.util.service.TimeElapsedCalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TimeElapsedController.class)
public class TimeElapsedControllerV3Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimeElapsedCalculatorService calculatorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCalculateTimeElapsedV3() throws Exception {
        TimeElapsedRequest request = new TimeElapsedRequest();
        request.setStartTime(LocalTime.of(10, 0));
        request.setEndTime(LocalTime.of(12, 30));

        when(calculatorService.calculateTimeElapsed(any(LocalTime.class), any(LocalTime.class)))
                .thenReturn("02:30:00");
        when(calculatorService.calculateTotalSeconds(any(LocalTime.class), any(LocalTime.class)))
                .thenReturn(9000L);

        mockMvc.perform(post("/api/time/v3/elapsed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.elapsedTime").value("02:30:00"))
                .andExpect(jsonPath("$.data.totalSeconds").value(9000))
                .andExpect(jsonPath("$.data.totalMinutes").value(150))
                .andExpect(jsonPath("$.data.totalHours").value(2))
                .andExpect(jsonPath("$.data.calculatedAt").exists());
    }
}