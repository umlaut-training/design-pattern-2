module com.umlaut.patterntraining.mvvm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javax.inject;
    requires com.umlaut.patterntraining.service;
    requires java.desktop;
    requires com.google.guice;

    opens com.umlaut.patterntraining.mvvm to javafx.fxml, com.google.guice;
    exports com.umlaut.patterntraining.mvvm;
}