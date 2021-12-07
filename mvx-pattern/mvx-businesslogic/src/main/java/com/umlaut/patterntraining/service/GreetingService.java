package com.umlaut.patterntraining.service;

import com.umlaut.patterntraining.database.KnownNamesRepository;

import javax.inject.Inject;

public class GreetingService {
    public static final int MAX_LENGHT_NAME = 8;

    @Inject
    private KnownNamesRepository knownNamesRepository;

    public boolean isNameValid(String name) {
        return name == null || name.length() <= MAX_LENGHT_NAME;
    }

    public String greetingsToComputer(String name, TimeOfDay timeOfDay) {
        if (name == null || name.isBlank()) {
            return "Hey Niemand";
        }
        if (name.length() > MAX_LENGHT_NAME) {
            throw new RuntimeException("Name zu lang");
        }
        String greetings = getTimeOfDayPraefix(timeOfDay) + " " + name + ", " + getKnownNameSuffix(name);
        knownNamesRepository.addName(name);
        return greetings;
    }

    private String getTimeOfDayPraefix(TimeOfDay timeOfDay) {
        return timeOfDay == TimeOfDay.DAY ? "Hallo" : "Schlaf gut";
    }

    private String getKnownNameSuffix(String name) {
        if (knownNamesRepository.getKnownNames().contains(name)) {
            if (name.endsWith("e") || name.endsWith("a")) {
                return "meine alte Freundin";
            } else {
                return "mein alter Freund";
            }
        } else {
            return "Schön dich kennenzulernen";
        }
    }
}
