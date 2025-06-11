package br.edu.ifrs.riogrande.tads.tds.util.controller.dto;

import java.time.LocalDateTime;

public class TimeElapsedResponseDTOV3 {
    private String elapsedTime;
    private Long totalSeconds;
    private Long totalMinutes;
    private Long totalHours;
    private LocalDateTime calculatedAt;

    public TimeElapsedResponseDTOV3(String elapsedTime, Long totalSeconds) {
        this.elapsedTime = elapsedTime;
        this.totalSeconds = totalSeconds;
        this.totalMinutes = totalSeconds / 60;
        this.totalHours = totalSeconds / 3600;
        this.calculatedAt = LocalDateTime.now();
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Long getTotalSeconds() {
        return totalSeconds;
    }

    public void setTotalSeconds(Long totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

    public Long getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(Long totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public Long getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Long totalHours) {
        this.totalHours = totalHours;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}