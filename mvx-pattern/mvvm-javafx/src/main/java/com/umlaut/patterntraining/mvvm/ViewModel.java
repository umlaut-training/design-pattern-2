package com.umlaut.patterntraining.mvvm;

import com.umlaut.patterntraining.service.GreetingService;
import com.umlaut.patterntraining.service.TimeOfDay;
import com.umlaut.patterntraining.service.TimeOfDayService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.inject.Inject;
import java.util.concurrent.Executor;

public class ViewModel {

    @Inject
    private GreetingService greetingService;
    @Inject
    private TimeOfDayService timeOfDayService;

    private Executor appThreadExecutor;

    public StringProperty nameProperty = new SimpleStringProperty();
    public StringProperty greetingsFromComputerProperty = new SimpleStringProperty();
    public StringProperty timeOfDayProperty = new SimpleStringProperty();
    public BooleanProperty nameInvalidProperty = new SimpleBooleanProperty();

    public void greetingsToComputer() {
        String gruss = greetingService.greetingsToComputer(nameProperty.getValue(), timeOfDayService.getTimeOfDay());
        greetingsFromComputerProperty.setValue(gruss);
    }

    public void init(Executor appThreadExecutor) {
        this.appThreadExecutor = appThreadExecutor;
        timeOfDayService.startService();
        setDayOfTimeInAppTread(timeOfDayService.getTimeOfDay());
        timeOfDayService.addTimeOfDayListener(evt -> setDayOfTimeInAppTread((TimeOfDay) evt.getNewValue()));
        nameInvalidProperty.bind(nameProperty.length().greaterThan(GreetingService.MAX_LENGHT_NAME));
    }

    private void setDayOfTimeInAppTread(TimeOfDay timeOfDay) {
        appThreadExecutor.execute(() -> timeOfDayProperty.setValue(convertTimeOfday(timeOfDay)));
    }

    private String convertTimeOfday(TimeOfDay timeOfDay) {
        return timeOfDay == TimeOfDay.DAY ? "Es ist Tag." : "Es ist Nacht.";
    }

    public void stop() {
        timeOfDayService.stopService();
    }
}
