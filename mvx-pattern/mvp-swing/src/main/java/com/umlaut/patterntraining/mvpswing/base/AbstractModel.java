package com.umlaut.patterntraining.mvpswing.base;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AbstractModel {
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    public void addListener(String propertyName, PropertyChangeListener listener)
    {
        changeSupport.addPropertyChangeListener(propertyName, listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue)
    {
        changeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void init()
    {
        // empty default
    }

    public void shutdown()
    {
        // empty default
    }
}
