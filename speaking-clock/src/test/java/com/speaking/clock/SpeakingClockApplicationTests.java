package com.speaking.clock;

import com.speaking.clock.service.SpeakingClockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpeakingClockApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testConvertToWordsMidday() {
        SpeakingClockService speakingClockService = new SpeakingClockService();
        String words = speakingClockService.convertToWords("12:00");
        Assertions.assertEquals("it's midday", words);
    }

    @Test
    public void testConvertToWordsMidnight() {
        SpeakingClockService speakingClockService = new SpeakingClockService();
        String words = speakingClockService.convertToWords("00:00");
        Assertions.assertEquals("It's midnight", words);
    }

    @Test
    public void testConvertToWordsPastMidnight() {
        SpeakingClockService speakingClockService = new SpeakingClockService();
        String words = speakingClockService.convertToWords("00:45");
        Assertions.assertEquals("It's twelve 45 minutes after midnight", words);
    }

    @Test
    public void testInvalidTimeFormat() {
        SpeakingClockService speakingClockService = new SpeakingClockService();
        String actualOutput = speakingClockService.convertToWords("-1:75");
        String expectedOutput = "Invalid time format";
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}
