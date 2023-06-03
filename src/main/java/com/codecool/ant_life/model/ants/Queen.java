package com.codecool.ant_life.model.ants;

import com.codecool.ant_life.model.Position;
import javafx.scene.image.Image;
import lombok.NonNull;

public class Queen extends Ant {
    public Queen(@NonNull Position position) {
        super(position);
    }

    @Override
    public Image getImage() {
        return null;
    }
}
