package com.codecool.ant_life.model.ants;

import com.codecool.ant_life.model.Position;
import javafx.scene.image.Image;
import lombok.NonNull;

public class Worker extends Ant {
    public Worker(@NonNull Position position) {
        super(position);
    }

    @Override
    public Image getImage() {
        return null;
    }
}
