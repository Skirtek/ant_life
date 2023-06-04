package com.codecool.ant_life.model.ants;

import com.codecool.ant_life.model.Position;
import javafx.scene.image.Image;
import lombok.NonNull;

import java.util.Objects;

public class Queen extends Ant {
    private static Image image;

    public Queen(@NonNull Position position) {
        super(position);
    }

    @Override
    public Image getImage(double size) {
        if (Objects.isNull(image)) {
            image = new Image(Queen.class.getResourceAsStream("/queen.png"), size, size, true, false);
        }

        return image;
    }

    @Override
    public Position getNextPosition() {
        throw new RuntimeException("Queen cannot move!");
    }
}
