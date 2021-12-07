package com.umlaut.patterntraining.service;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TimeOfDayService {
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private volatile TimeOfDay timeOfDay = TimeOfDay.DAY;
    private Thread timeOfDayChangeThread;

    public void startService() {
        timeOfDayChangeThread = new Thread(() -> {
            while (true) {
                try {
                    changeTimeOfDay();
                    //noinspection BusyWait
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        timeOfDayChangeThread.setDaemon(true);
        timeOfDayChangeThread.start();
    }

    public void stopService() {
        timeOfDayChangeThread.interrupt();
    }

    public void addTimeOfDayListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener("timeOfDay", listener);
    }

    private void changeTimeOfDay() {
        if (timeOfDay == TimeOfDay.DAY) {
            setTimeOfDay(TimeOfDay.NIGHT);
        } else {
            setTimeOfDay(TimeOfDay.DAY);
        }
    }

    private void setTimeOfDay(TimeOfDay timeOfDay) {
        changeSupport.firePropertyChange("timeOfDay", this.timeOfDay, this.timeOfDay = timeOfDay);
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }
}
