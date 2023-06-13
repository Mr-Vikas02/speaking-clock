package com.speaking.clock.controller;

import com.speaking.clock.service.SpeakingClockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SpeakingClockController {
    private final Map<String, String> spokenTimes = new HashMap<>();
    private final SpeakingClockService SpeakingClockService;

    public SpeakingClockController(com.speaking.clock.service.SpeakingClockService speakingClockService) {
        this.SpeakingClockService = speakingClockService;
    }

    @GetMapping("/time")
    public ResponseEntity<String> getCurrentTime() {
        String spokenTime = SpeakingClockService.getTime();
        return ResponseEntity.ok(spokenTime);
    }

    @GetMapping("/time/{time}")
    public ResponseEntity<String> getInputTime(@PathVariable("time") String time) {
        if (spokenTimes.containsKey(time)) {
           return ResponseEntity.ok(spokenTimes.get(time));
        } else {
            String spokenTime = SpeakingClockService.convertToWords(time);
            spokenTimes.put(time, spokenTime);
            return ResponseEntity.ok(spokenTime);
        }
    }

}
