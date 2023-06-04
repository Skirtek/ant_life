package com.codecool.ant_life.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public enum MoveDirection {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private int axisX;
    private int axisY;

    public static MoveDirection getRandomMove() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
