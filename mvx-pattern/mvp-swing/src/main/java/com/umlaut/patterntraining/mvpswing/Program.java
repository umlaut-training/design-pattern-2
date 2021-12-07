package com.umlaut.patterntraining.mvpswing;

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
        Presenter presenter = injector.getInstance(Presenter.class);
        initFrame(presenter);
    }

    private static void initFrame(Presenter presenter) {
        JFrame mainFrame = new JFrame("Hallo, Hello, Hola, Bonjour, Ciao.");
        mainFrame.setSize(350, 200);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());
        presenter.init(mainFrame);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                presenter.shutDown();
            }
        });

        mainFrame.setVisible(true);
    }
}
