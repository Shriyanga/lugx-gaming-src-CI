package com.example.analyticservice.service;

import com.example.analyticservice.dto.ClickEventDTO;

import java.util.List;

/**
 * @author Thrimal Avishka
 * @since 08-Aug-25
 **/
public interface ClickEventService {
    boolean insertClickEvents(List<ClickEventDTO> clickEventDTOList);
}
