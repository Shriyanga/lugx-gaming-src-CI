package com.example.analyticservice.service;

import com.example.analyticservice.dto.ClickEventDTO;

import java.util.List;


public interface ClickEventService {
    boolean insertClickEvents(List<ClickEventDTO> clickEventDTOList);
}
