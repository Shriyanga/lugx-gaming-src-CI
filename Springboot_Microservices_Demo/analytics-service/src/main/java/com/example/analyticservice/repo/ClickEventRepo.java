package com.example.analyticservice.repo;

import com.example.analyticservice.entity.ClickEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClickEventRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertClickEventsBatch(List<ClickEvent> events) {
        String sql = "INSERT INTO click_events (" +
                "event_id, event_date, event_time, user_id, session_id, " +
                "page_url, referrer_url, event_type, user_agent, ip_address" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, events, events.size(), (ps, event) -> {
            ps.setLong(1, event.getEventId());
            ps.setDate(2, java.sql.Date.valueOf(event.getEventDate()));
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(event.getEventTime()));
            ps.setLong(4, event.getUserId());
            ps.setString(5, event.getSessionId());
            ps.setString(6, event.getPageUrl());
            ps.setString(7, event.getReferrerUrl());
            ps.setString(8, event.getEventType());
            ps.setString(9, event.getUserAgent());
            ps.setString(10, event.getIpAddress());
        });
    }

    public boolean existsById(Long eventId) {
        String sql = "SELECT count(*) FROM click_events WHERE event_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, eventId);
        return count > 0;
    }
}
