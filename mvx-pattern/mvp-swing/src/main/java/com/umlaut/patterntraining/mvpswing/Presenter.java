package com.umlaut.patterntraining.mvpswing;

import com.umlaut.patterntraining.mvpswing.base.AbstractPresenter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Presenter extends AbstractPresenter<Model, View> {
    private final DocumentListener textFieldnameListener = new DocumentListener() {
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
            getModel().setName(getView().textFieldName.getText());
        }
    };

    @Override
    public void initBindings() {
        getView().buttonGreetingsToComputer.addActionListener(e -> getModel().calculateGreetings());
        getView().textFieldName.getDocument().addDocumentListener(textFieldnameListener);
        getModel().addListener("greetingsFromComputer", e -> getView().labelGreetingsFromComputer.setText((String) e.getNewValue()));
        getModel().addListener("timeOfDayAsString", e -> setTimeOfDayInEdt((String) e.getNewValue()));
        getModel().addListener("nameValid", e -> getView().buttonGreetingsToComputer.setEnabled((boolean) e.getNewValue()));
    }

    private void setTimeOfDayInEdt(String timeOfDay) {
        SwingUtilities.invokeLater(() -> getView().labelTimeOfDay.setText(timeOfDay));
    }
}
