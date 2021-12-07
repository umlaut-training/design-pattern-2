package com.umlaut.patterntraining.mvcspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class GreetingsController {

    @Autowired
    private GreetingsService greetingsService;

    @GetMapping("greetings")
    public String getGreetings(@ModelAttribute("greetingsmodel") Model model) {
        return "greetings";
    }

    @PostMapping("greetings")
    public String postGreetings(@Valid @ModelAttribute("greetingsmodel") Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.setGreetingsFromComputer("Hallo ähhh?");
        } else {
            model.setGreetingsFromComputer(greetingsService.greetings(model.getName()));
        }
        model.setName("");
        return "greetings";
    }
}
