package com.umlaut.patterntraining.mvcspring;

import org.springframework.stereotype.Service;

@Service
public class GreetingsService {

    String greetings(String name) {
        return "Hallo " + name + "!";
    }
}
