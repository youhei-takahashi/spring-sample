package com.example.musicmanagement.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class Music {
    private long musicId;
    private String title;
    private LocalTime duration;
    private long albumId;
    private LocalDateTime createdAt;
}
