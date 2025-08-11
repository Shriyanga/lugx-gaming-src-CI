package com.example.analyticservice.util;

import com.example.analyticservice.repo.ClickEventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class IdGenerator {
    @Autowired
    private ClickEventRepo clickEventRepo;

    private final SecureRandom random = new SecureRandom();

    public IdGenerator(ClickEventRepo clickEventRepo) {
        this.clickEventRepo = clickEventRepo;
    }

    public long generateUniqueId() {
        long id;
        do {
            id = Math.abs(random.nextLong());
        } while (clickEventRepo.existsById(id));
        return id;
    }
}

