package com.umlaut.patterntraining.mvchistorical;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Program {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
            }
        });
        Controller controller = injector.getInstance(Controller.class);
        initFrame(controller);
    }

    private static void initFrame(Controller controller) {
        JFrame mainFrame = new JFrame("Hallo, Hello, Hola, Bonjour, Ciao.");
        mainFrame.setSize(350, 200);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());
        controller.init(mainFrame);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.shutDown();
            }
        });

        mainFrame.setVisible(true);
    }
}
