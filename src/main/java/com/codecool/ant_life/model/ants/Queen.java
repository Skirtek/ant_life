package com.codecool.ant_life.model.ants;

import com.codecool.ant_life.model.Position;
import javafx.scene.image.Image;
import lombok.NonNull;

import java.util.Objects;
import java.util.Random;

public class Queen extends Ant {
    private final static int BAD_MOOD_MIN_TURNS = 50;
    private final static int BAD_MOOD_MAX_TURNS = 100;

    private int badMoodTurnsCounter;
    private static Image image;

    public Queen(@NonNull Position position) {
        super(position);
        badMoodTurnsCounter = new Random().nextInt(BAD_MOOD_MIN_TURNS, BAD_MOOD_MAX_TURNS + 1);
    }

    public void updateBadMoodTurns() {
        badMoodTurnsCounter -= 1;
    }

    public boolean isInMood() {
        return badMoodTurnsCounter < 1;
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
