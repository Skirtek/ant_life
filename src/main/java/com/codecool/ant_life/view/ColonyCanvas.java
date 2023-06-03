package com.codecool.ant_life.view;

import com.codecool.ant_life.model.Animal;
import com.codecool.ant_life.model.Colony;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;

public class ColonyCanvas<T extends Animal> {
    private final static double CANVAS_MARGIN = 5.5;

    @Getter
    private final int cellSize;

    private final GraphicsContext graphicsContext;

    @Getter
    private final Colony<T> colony;

    @Getter
    private final Canvas canvas;

    public ColonyCanvas(int cellSize, Colony<T> colony) {
        this.cellSize = cellSize;
        this.colony = colony;

        Canvas canvas = new Canvas();
        canvas.setHeight(colony.getHeight() * cellSize + CANVAS_MARGIN);
        canvas.setWidth(colony.getWidth() * cellSize + CANVAS_MARGIN);

        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    public void draw() {
        clearCanvas();
        drawLines();
        drawColony();
    }

    private void clearCanvas() {
        // todo czyścić canvas cały na początku
    }

    private void drawLines() {
        // todo narysować linie siatki
    }

    private void drawColony() {
        // todo narysować kolonie czyli umieścić poszczególnych członków (mrówki 🐜) w poszczególnych komórkach
    }
}
