package com.codecool.ant_life.model.ants;

import com.codecool.ant_life.model.Animal;
import com.codecool.ant_life.model.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
public abstract class Ant implements Animal {
    @Getter @Setter @NonNull
    private Position position;

    public abstract Position getNextPosition();
}
