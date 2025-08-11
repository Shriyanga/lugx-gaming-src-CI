package com.example.analyticservice.controller;

import com.example.analyticservice.dto.ClickEventDTO;
import com.example.analyticservice.service.ClickEventService;
import com.example.analyticservice.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Thrimal Avishka
 * @since 08-Aug-25
 **/
@RestController
@RequestMapping("/api/v1/analytic/click-events")
public class ClickEventController {

    @Autowired
    private ClickEventService clickEventService;

    @PostMapping
    public ResponseEntity<?> saveClickEvent(@RequestBody List<ClickEventDTO> clickEventDTOList) {
        boolean b = clickEventService.insertClickEvents(clickEventDTOList);
        return new ResponseEntity<>(new StandardResponse<>(201, "Success", b), HttpStatus.CREATED);
    }
}
