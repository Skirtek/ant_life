package com.codecool.ant_life.model.ants;

import com.codecool.ant_life.model.MoveDirection;
import com.codecool.ant_life.model.Position;
import com.codecool.ant_life.model.Vector;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import lombok.NonNull;

import java.util.Objects;
import java.util.Random;

public class Drone extends Ant {
    private static Image image;
    private final Queen queen;
    private final int width, height;

    // It's a tight coupling example, be aware in future cases
    public Drone(@NonNull Position position, Queen queen, int width, int height) {
        super(position);
        this.queen = queen;
        this.width = width;
        this.height = height;
    }

    @Override
    public Image getImage(double size) {
        if (Objects.isNull(image)) {
            image = new Image(Drone.class.getResourceAsStream("/drone.png"), size, size, true, false);
        }

        return image;
    }

    @Override
    public Position getNextPosition() {
        // We get position of queen and drone
        var queenPosition = queen.getPosition();
        Position dronePosition = this.getPosition();

        if (queenPosition.equals(dronePosition)) {
            return getPositionBasedOnQueenMood();
        }

        Vector vector = new Vector(dronePosition.getX() - queenPosition.getX(), dronePosition.getY() - queenPosition.getY());

        MoveDirection nextMove;

        // Do not forget that ... ? ... : ... is ternary operator
        if (vector.getY() == 0) {
            nextMove = vector.getX() > 0 ? MoveDirection.LEFT : MoveDirection.RIGHT;
        } else {
            nextMove = vector.getY() > 0 ? MoveDirection.UP : MoveDirection.DOWN;
        }

        return new Position(getPosition().getX() + nextMove.getAxisX(), getPosition().getY() + nextMove.getAxisY());
    }

    private Position getKickPosition() {
        Position nextPosition = null;

        MoveDirection nextMove = MoveDirection.getRandomMove();

        int randomX = new Random().nextInt(width);
        int randomY = new Random().nextInt(height);

        switch (nextMove) {
            case UP -> nextPosition = new Position(randomX, 0);
            case DOWN -> nextPosition = new Position(randomX, height);
            case LEFT -> nextPosition = new Position(0, randomY);
            case RIGHT -> nextPosition = new Position(width, randomY);
        }

        return nextPosition;
    }

    private Position getPositionBasedOnQueenMood() {
        // TODO Obsługa zatrzymania na 10 rund jeżeli królowa na nastrój, następnie odrzucenie
        // TODO Dla chętnych: rozszerzenia zadania o mechanizm narodzin -> po 10 rundach od końca rozmnażania rodzi się randomowa liczba nowych mrówek
        // ale nie może być to królowa
        if (queen.isInMood()) {
            System.out.println("HALLELUJAH");
            return this.getPosition();
        } else {
            System.out.println(":(");
            return getKickPosition();
        }
    }
}
