package com.umlaut.patterntraining.mvchistorical;

import javax.swing.*;
import java.awt.*;

public class View {

    private final JLabel labelName = new JLabel("Name?");
    public final JTextField textFieldName = new JTextField();
    public final JButton buttonGreetingsToComputer = new JButton("Gruss an den Computer");
    private final JLabel labelGreetingsFromComputer = new JLabel();
    private final JLabel labelTimeOfDay = new JLabel();

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

    public void updateUI(Model model) {
        labelGreetingsFromComputer.setText(model.getGreetingsFromComputer());
        labelTimeOfDay.setText(model.getTimeOfDay());
        buttonGreetingsToComputer.setEnabled(model.isGreetingsButtonEnabled());
    }
}
