package com.codecool.ant_life.model.ants;

import com.codecool.ant_life.model.MoveDirection;
import com.codecool.ant_life.model.Position;
import javafx.scene.image.Image;
import lombok.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Soldier extends Ant {
    private static Image image;

    private int currentMoveIndex = 0;

    private final List<MoveDirection> directions = Arrays.asList(MoveDirection.UP, MoveDirection.RIGHT, MoveDirection.DOWN, MoveDirection.LEFT);

    public Soldier(@NonNull Position position) {
        super(position);
    }

    @Override
    public Image getImage(double size) {
        if (Objects.isNull(image)) {
            image = new Image(Soldier.class.getResourceAsStream("/soldier.png"), size, size, true, false);
        }

        return image;
    }

    @Override
    public Position getNextPosition() {
        MoveDirection nextMove = directions.get(currentMoveIndex++);

        if (currentMoveIndex >= directions.size()) {
            currentMoveIndex = 0;
        }

        return new Position(getPosition().getX() + nextMove.getAxisX(), getPosition().getY() + nextMove.getAxisY());
    }
}
