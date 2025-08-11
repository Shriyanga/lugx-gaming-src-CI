package com.example.analyticservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Thrimal Avishka
 * @since 08-Aug-25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClickEventDTO {
    private Long eventId;
    private LocalDate eventDate;
    private LocalDateTime eventTime;
    private Long userId;
    private String sessionId;
    private String pageUrl;
    private String referrerUrl;
    private String eventType;
    private String userAgent;
    private String ipAddress;
}
