package com.codecool.ant_life.model.ants;

import com.codecool.ant_life.model.MoveDirection;
import com.codecool.ant_life.model.Position;
import javafx.scene.image.Image;
import lombok.NonNull;

import java.util.Objects;

public class Worker extends Ant {
    private static Image image;

    public Worker(@NonNull Position position) {
        super(position);
    }

    @Override
    public Image getImage(double size) {
        if (Objects.isNull(image)) {
            image = new Image(Worker.class.getResourceAsStream("/worker.png"), size, size, true, false);
        }

        return image;
    }

    @Override
    public Position getNextPosition() {
        MoveDirection randomDirection = MoveDirection.getRandomMove();
        return new Position(getPosition().getX() + randomDirection.getAxisX(), getPosition().getY() + randomDirection.getAxisY());
    }
}
