package com.codecool.ant_life;

import com.codecool.ant_life.model.Animal;
import com.codecool.ant_life.model.Colony;
import com.codecool.ant_life.model.ants.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lombok.Data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // create a colony
        int colonyWidth = 80;
        int colonyHeight = 40;

        Colony<Ant> colony = new AntColony(colonyWidth, colonyHeight);

        Map<Class<? extends Animal>, Integer> colonyConfiguration = new HashMap<>();
        colonyConfiguration.put(Worker.class, 40);
        colonyConfiguration.put(Soldier.class, 10);
        colonyConfiguration.put(Drone.class, 20);

        colony.generateNewColony(colonyConfiguration);

        Map<Class<? extends Animal>, Integer> generatedColonyStructure = colony.getInfoAboutColonyStructure();

        generatedColonyStructure.forEach((antClass, amount) -> {
            System.out.printf("%s %d%n", antClass.getSimpleName(), amount);
        });

        // create view
        Canvas canvas = new Canvas();
        canvas.setWidth(300);
        canvas.setHeight(400);
        canvas.setLayoutX(10);
        canvas.setLayoutY(10);

        Pane mainPanel = new Pane();
        mainPanel.getChildren().addAll(canvas);

        Scene scene = new Scene(mainPanel, 800, 600);
        stage.setTitle("Life of Ants - CodeCool");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}