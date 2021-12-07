module com.umlaut.patterntraining.service {
    requires javax.inject;
    requires java.desktop;

    opens com.umlaut.patterntraining.service to com.google.guice;
    opens com.umlaut.patterntraining.database to com.google.guice;

    exports com.umlaut.patterntraining.service;
}