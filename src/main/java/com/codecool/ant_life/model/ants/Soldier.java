package com.codecool.ant_life.model.ants;

import com.codecool.ant_life.model.Position;
import javafx.scene.image.Image;
import lombok.NonNull;

public class Soldier extends Ant {
    public Soldier(@NonNull Position position) {
        super(position);
    }

    @Override
    public Image getImage() {
        return null;
    }
}
