package com.codecool.ant_life.model;

import javafx.scene.image.Image;

public interface Animal {
    Position getPosition();
    void setPosition(Position position);
    Image getImage(double size);
    Position getNextPosition();
}
