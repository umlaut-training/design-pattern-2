package com.umlaut.patterntraining.mvcspring;

import javax.validation.constraints.Size;

public class Model {
    @Size(max = 8, message = "Kann mir maximal 8 Zeichen merken")
    private String name;
    private String greetingsFromComputer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGreetingsFromComputer() {
        return greetingsFromComputer;
    }

    public void setGreetingsFromComputer(String greetingsFromComputer) {
        this.greetingsFromComputer = greetingsFromComputer;
    }

}
