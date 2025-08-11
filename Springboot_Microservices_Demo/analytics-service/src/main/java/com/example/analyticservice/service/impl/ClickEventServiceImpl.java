package com.example.analyticservice.service.impl;

import com.example.analyticservice.dto.ClickEventDTO;
import com.example.analyticservice.entity.ClickEvent;
import com.example.analyticservice.repo.ClickEventRepo;
import com.example.analyticservice.service.ClickEventService;
import com.example.analyticservice.util.IdGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Thrimal Avishka
 * @since 08-Aug-25
 **/
@Service
@Transactional
public class ClickEventServiceImpl implements ClickEventService {

    @Autowired
    private ClickEventRepo clickEventRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public boolean insertClickEvents(List<ClickEventDTO> clickEventDTOList) {
        try {
            List<ClickEvent> events = new ArrayList<>();
            for (ClickEventDTO dto : clickEventDTOList) {
                long id = idGenerator.generateUniqueId();
                ClickEvent clickEvent = modelMapper.map(dto, ClickEvent.class);
                clickEvent.setEventId(id);
                events.add(clickEvent);
            }
            clickEventRepo.insertClickEventsBatch(events);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to insert click events", e);
        }
    }

}
