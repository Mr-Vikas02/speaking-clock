package com.speaking.clock.service;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class SpeakingClockService {
    public String getTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH);
        String currentTimeFormat = currentTime.format(formatter);
        return convertToWords(currentTimeFormat);
    }

    public String convertToWords(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3));

        if (hour == 12) {
            if (minute == 0) {
                return "It's midday";
            } else {
                return "It's twelve " + minute + " minutes after midday";
            }
        } else if (hour == 24 || hour == 0) {
            if (minute == 0) {
                return "It's midnight";
            } else {
                return "It's twelve " + minute + " minutes after midnight";
            }
        }

        //** Handle invalid hour and minute values **//
        if (hour < 0 || hour > 23) {
            if (minute < 0 || minute > 59) {
                return "Invalid time format";
            }
        }

        String convertedHour;
        if (hour == 0 || hour == 12) {
            convertedHour = "twelve";
        } else {
            convertedHour = convertNumberToWords(hour % 12);
        }

        String convertedMinute = convertNumberToWords(minute);
        return "It's " + convertedHour + (convertedMinute.isEmpty() ? "" : " " + convertedMinute);
    }

    private String convertNumberToWords(int number) {
        String[] onesWords = {
                "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
        };

        String[] tensWords = {
                "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
        };

        if (number == 0) {
            return "";
        } else if (number < 20) {
            return onesWords[number];
        } else {
            int tens = number / 10;
            int ones = number % 10;
            return tensWords[tens] + (ones != 0 ? " " + onesWords[ones] : "");
        }
    }
}


