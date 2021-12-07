package com.umlaut.patterntraining.mvchistorical;

public class Model {

    private String name;
    private String greetingsFromComputer;
    private boolean greetingsButtonEnabled;
    private String timeOfDay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGreetingsButtonEnabled() {
        return greetingsButtonEnabled;
    }

    public void setGreetingsButtonEnabled(boolean greetingsButtonEnabled) {
        this.greetingsButtonEnabled = greetingsButtonEnabled;
    }

    public String getGreetingsFromComputer() {
        return greetingsFromComputer;
    }

    public void setGreetingsFromComputer(String greetingsFromComputer) {
        this.greetingsFromComputer = greetingsFromComputer;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
}
