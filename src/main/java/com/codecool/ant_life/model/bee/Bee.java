package com.codecool.ant_life.model.bee;

import com.codecool.ant_life.model.Animal;
import com.codecool.ant_life.model.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
public abstract class Bee implements Animal {
    @Getter
    @Setter
    @NonNull
    private Position position;
}
