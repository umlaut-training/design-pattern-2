package com.umlaut.patterntraining.mvchistorical;

import com.umlaut.patterntraining.service.GreetingService;
import com.umlaut.patterntraining.service.TimeOfDay;
import com.umlaut.patterntraining.service.TimeOfDayService;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Controller {

    private final DocumentListener textFieldNameListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateName(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateName(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateName(e);
        }

        private void updateName(DocumentEvent e) {
            Controller.this.onNameInputUpdate(view.textFieldName.getText());
        }
    };

    @Inject
    private View view;
    @Inject
    private Model model;
    @Inject
    private GreetingService greetingService;
    @Inject
    private TimeOfDayService timeOfDayService;

    public void init(JFrame frame) {
        frame.add(view.getMainPanel());
        timeOfDayService.startService();
        timeOfDayService.addTimeOfDayListener(e -> onTimeOfDayDataUpdate((TimeOfDay) e.getNewValue()));
        view.textFieldName.getDocument().addDocumentListener(textFieldNameListener);
        view.buttonGreetingsToComputer.addActionListener(e -> onGreetingsButtonClick());
        initModel();
    }

    private void initModel() {
        model.setGreetingsButtonEnabled(true);
        model.setTimeOfDay(convertTimeOfDay(timeOfDayService.getTimeOfDay()));
        updateUIinEdt();
    }

    private void onGreetingsButtonClick() {
        TimeOfDay timeOfDay = timeOfDayService.getTimeOfDay();
        String name = model.getName();
        String greetings = greetingService.greetingsToComputer(name, timeOfDay);
        model.setGreetingsFromComputer(greetings);
        updateUI();
    }

    private void onNameInputUpdate(String name) {
        model.setName(name);
        model.setGreetingsButtonEnabled(greetingService.isNameValid(name));
        updateUI();
    }

    private void onTimeOfDayDataUpdate(TimeOfDay timeOfDay) {
        model.setTimeOfDay(convertTimeOfDay(timeOfDay));
        updateUIinEdt();
    }

    private String convertTimeOfDay(TimeOfDay timeOfDay) {
        return timeOfDay == TimeOfDay.DAY ? "Es ist Tag." : "Es ist Nacht.";
    }

    private void updateUIinEdt() {
        SwingUtilities.invokeLater(this::updateUI);
    }

    private void updateUI() {
        view.updateUI(model);
    }

    public void shutDown() {
        timeOfDayService.stopService();
    }
}
