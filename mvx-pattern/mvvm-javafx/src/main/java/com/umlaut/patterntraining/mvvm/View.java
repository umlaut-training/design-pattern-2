package com.umlaut.patterntraining.mvvm;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class View implements Initializable {
    @Inject
    private ViewModel viewModel;

    @FXML
    private Button buttonGreetingsToComputer;

    @FXML
    private Label labelGreetingsFromComputer;

    @FXML
    private Label labelTimeOfDay;

    @FXML
    private TextField textFieldName;

    @FXML
    void onGreetingsButtonClicked(ActionEvent event) {
        viewModel.greetingsToComputer();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel.nameProperty.bind(textFieldName.textProperty());
        labelTimeOfDay.textProperty().bind(viewModel.timeOfDayProperty);
        buttonGreetingsToComputer.disableProperty().bind(viewModel.nameInvalidProperty);
        labelGreetingsFromComputer.textProperty().bind(viewModel.greetingsFromComputerProperty);
        viewModel.init(Platform::runLater);
    }

    public void stop() {
        viewModel.stop();
    }
}
