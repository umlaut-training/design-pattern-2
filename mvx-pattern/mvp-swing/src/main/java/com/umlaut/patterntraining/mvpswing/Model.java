package com.umlaut.patterntraining.mvpswing;

import com.umlaut.patterntraining.mvpswing.base.AbstractModel;
import com.umlaut.patterntraining.service.GreetingService;
import com.umlaut.patterntraining.service.TimeOfDay;
import com.umlaut.patterntraining.service.TimeOfDayService;

import javax.inject.Inject;

public class Model extends AbstractModel {

    //Businesslogic inside Model
    @Inject
    private GreetingService greetingService;
    @Inject
    private TimeOfDayService timeOfDayService;

    private String name;
    private String greetingsFromComputer;
    // since Presenter connects model and View, we can use domain names instead of view state buttonEnabled
    private boolean nameValide;
    // TimeOfDay is included to the Model here, we don't need it for MVC historical
    private TimeOfDay timeOfDay;
    // Could also be in the presenter if it is seen as view detail, but typically you want to check this with unit tests of the model
    private String timeOfDayAsString;

    @Override
    public void init() {
        timeOfDayService.startService();
        initBindings();
    }

    @Override
    public void shutdown() {
        timeOfDayService.stopService();
    }

    private void initBindings() {
        timeOfDayService.addTimeOfDayListener(e -> setTimeOfDay((TimeOfDay) e.getNewValue()));
        addListener("name", e -> setNameValide(greetingService.isNameValid((String) e.getNewValue())));
        addListener("timeOfDay", e -> setTimeOfDayAsString(convertTimeOfDay((TimeOfDay) e.getNewValue())));
        setTimeOfDay(timeOfDayService.getTimeOfDay());
    }

    public void calculateGreetings() {
        setGreetingsFromComputer(greetingService.greetingsToComputer(name, timeOfDay));
    }

    private String convertTimeOfDay(TimeOfDay timeOfDay) {
        return timeOfDay == TimeOfDay.DAY ? "Es ist Tag." : "Es ist Nacht.";
    }

    public void setName(String name) {
        firePropertyChange("name", this.name, this.name = name);
    }

    private void setNameValide(boolean nameValide) {
        firePropertyChange("nameValid", this.nameValide, this.nameValide = nameValide);
    }

    private void setGreetingsFromComputer(String greetingsFromComputer) {
        firePropertyChange("greetingsFromComputer", this.greetingsFromComputer, this.greetingsFromComputer = greetingsFromComputer);
    }

    private void setTimeOfDay(TimeOfDay timeOfDay) {
        firePropertyChange("timeOfDay", this.timeOfDay, this.timeOfDay = timeOfDay);
    }

    private void setTimeOfDayAsString(String timeOfDayAsString) {
        firePropertyChange("timeOfDayAsString", this.timeOfDayAsString, this.timeOfDayAsString = timeOfDayAsString);
    }
}
