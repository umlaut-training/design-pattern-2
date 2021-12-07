package com.umlaut.patterntraining.mvpswing;

import com.umlaut.patterntraining.mvpswing.base.AbstractView;

import javax.swing.*;
import java.awt.*;

public class View extends AbstractView {

    private final JLabel labelName = new JLabel("Name?");
    public final JTextField textFieldName = new JTextField();
    // are public now for the Presenter
    public final JButton buttonGreetingsToComputer = new JButton("Gruss an den Computer");
    public final JLabel labelGreetingsFromComputer = new JLabel();
    public final JLabel labelTimeOfDay = new JLabel();

    @Override
    public JPanel getMainPanel() {
        textFieldName.setMinimumSize(new Dimension(200, 10));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1));
        mainPanel.add(labelName);
        mainPanel.add(textFieldName);
        mainPanel.add(buttonGreetingsToComputer);
        mainPanel.add(labelGreetingsFromComputer);
        mainPanel.add(labelTimeOfDay);
        return mainPanel;
    }
}
