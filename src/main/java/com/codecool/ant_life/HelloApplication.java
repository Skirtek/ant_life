package com.codecool.ant_life;

import com.codecool.ant_life.model.Animal;
import com.codecool.ant_life.model.Colony;
import com.codecool.ant_life.model.ants.*;
import com.codecool.ant_life.view.ColonyCanvas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import lombok.Data;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class HelloApplication extends Application {
    private static final int COLONY_CANVAS_MARGIN = 50;

    @Override
    public void start(Stage stage) {
        // create a colony
        int colonyWidth = 80;
        int colonyHeight = 40;

        Colony<Ant> colony = new AntColony(colonyWidth, colonyHeight);

        Map<Class<? extends Animal>, Integer> colonyConfiguration = new HashMap<>();
        colonyConfiguration.put(Worker.class, 40);
        colonyConfiguration.put(Soldier.class, 10);
        colonyConfiguration.put(Drone.class, 20);

        colony.generateNewColony(colonyConfiguration);

        // create view`
        int cellSize = 16;
        ColonyCanvas<Ant> antColonyCanvas = new ColonyCanvas<>(cellSize, colony);
        antColonyCanvas.draw();

        Canvas canvas = antColonyCanvas.getCanvas();
        canvas.setLayoutX(200);
        canvas.setLayoutY(10);

        // Update button
        Button updateButton = new Button("Update colony");
        updateButton.setOnMouseClicked(mouseEvent -> {
            updateColony(colony, antColonyCanvas);
        });

        // Colony structure info
        // Drone.class: 60
        // Queen.class: 1
        int leftPaneLeftOffset = 5;

        Text colonyInfoText = new Text();
        colonyInfoText.setLayoutX(leftPaneLeftOffset);
        colonyInfoText.setLayoutY(40);
        colonyInfoText.setWrappingWidth(canvas.getLayoutX() - 40);
        updateColonyStructure(colony, colonyInfoText);
        colonyInfoText.autosize();

        // Cycles input
        TextField simulationText = new TextField();
        simulationText.setLayoutX(leftPaneLeftOffset);
        simulationText.setLayoutY(140);

        TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), 0);

        textFormatter.valueProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue == null){
                simulationText.setText("");
            }
        });

        simulationText.setTextFormatter(textFormatter);

        Button runSimulationButton = new Button("Run simulation");
        runSimulationButton.setLayoutX(leftPaneLeftOffset);
        runSimulationButton.setLayoutY(170);
        runSimulationButton.setOnMouseClicked(mouseEvent -> {
            int simulationAmount = tryParseInt(simulationText.getText());

            if (simulationAmount < 1) {
                return;
            }

            AtomicReference<Integer> simulationCounter = new AtomicReference<>(simulationAmount);

            runSimulationButton.setDisable(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), actionEvent -> {
                updateColony(colony, antColonyCanvas);
                updateColonyStructure(colony, colonyInfoText);

                simulationCounter.set(simulationCounter.get() - 1);
                simulationText.setText(simulationCounter.get().toString());
            }));

            timeline.setCycleCount(simulationCounter.get());
            timeline.play();
            timeline.setOnFinished(actionEvent -> runSimulationButton.setDisable(false));
        });

        Pane leftMenuPane = new Pane();
        leftMenuPane.getChildren().addAll(colonyInfoText, updateButton, simulationText, runSimulationButton);
        leftMenuPane.setLayoutX(10);
        leftMenuPane.setLayoutY(10);

        Pane mainPanel = new Pane();
        mainPanel.getChildren().addAll(canvas, leftMenuPane);

        Scene scene = new Scene(mainPanel, (colonyWidth * cellSize) + COLONY_CANVAS_MARGIN, (colonyHeight * cellSize) + COLONY_CANVAS_MARGIN);
        stage.setTitle("Life of Ants - CodeCool");
        stage.setScene(scene);
        stage.show();
    }

    private void updateColony(Colony<Ant> colony, ColonyCanvas<Ant> antColonyCanvas) {
        colony.update();
        antColonyCanvas.draw();
    }

    private int tryParseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private void updateColonyStructure(Colony<Ant> colony, Text colonyInfoText) {
        Map<Class<? extends Animal>, Integer> colonyInfo = colony.getInfoAboutColonyStructure();

        StringBuilder stringBuilder = new StringBuilder();

        colonyInfo.forEach((k, v) -> stringBuilder.append(k.getSimpleName()).append(": ").append(v).append("\n"));

        colonyInfoText.setText(stringBuilder.toString());
    }

    public static void main(String[] args) {
        launch();
    }
}