package com.umlaut.patterntraining.mvpswing.base;

import javax.inject.Inject;
import javax.swing.*;

public abstract class AbstractPresenter<MODEL extends AbstractModel, VIEW extends AbstractView> {
    @Inject
    private VIEW view;
    @Inject
    private MODEL model;

    public void init(JFrame frame) {
        frame.add(view.getMainPanel());
        initBindings();
        model.init();
    }

    public void shutDown() {
        model.shutdown();
    }

    public void initBindings() {
        // empty default
    }

    public MODEL getModel() {
        return model;
    }

    public VIEW getView() {
        return view;
    }
}
