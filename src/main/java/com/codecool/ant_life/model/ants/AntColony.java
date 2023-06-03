package com.codecool.ant_life.model.ants;

import com.codecool.ant_life.model.Animal;
import com.codecool.ant_life.model.Colony;
import com.codecool.ant_life.model.Position;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class AntColony implements Colony<Ant> {
    @Getter
    private final int width, height;

    @Getter
    private final List<Ant> colony = new ArrayList<>();

    // Drone.class: 20
    // Queen.class: 1
    // Soldier.class: 10
    // Worker.class: 70
    @Override
    public Map<Class<? extends Animal>, Integer> getInfoAboutColonyStructure() {
        Map<Class<? extends Animal>, Integer> result = new HashMap<>();

        result.put(Queen.class, 1);
        result.put(Drone.class, (int) colony.stream().filter(ant -> ant instanceof Drone).count());
        result.put(Worker.class, (int) colony.stream().filter(ant -> ant instanceof Worker).count());
        result.put(Soldier.class, (int) colony.stream().filter(ant -> ant instanceof Soldier).count());

        return result;
    }

    @Override
    public void addMember(Ant newMember) {
        colony.add(newMember);
    }

    @Override
    public void removeMember(Ant member) {
        colony.remove(member);
    }

    @Override
    public void update() {

    }

    @Override
    public void generateNewColony(Map<Class<? extends Animal>, Integer> colonyConfiguration) {
        colonyConfiguration.forEach((antTypeClass, antAmount) -> {
            if (antTypeClass.equals(Drone.class)) {
                // todo randomize positions
                IntStream.range(0, antAmount).forEach(ant -> colony.add(new Drone(new Position(1, 1))));
            } else if (antTypeClass.equals(Worker.class)) {
                IntStream.range(0, antAmount).forEach(ant -> colony.add(new Worker(new Position(1, 1))));
            } else if (antTypeClass.equals(Soldier.class)) {
                IntStream.range(0, antAmount).forEach(ant -> colony.add(new Soldier(new Position(1, 1))));
            } else {
                throw new IllegalArgumentException(String.format("Ant %s is not supported!", antTypeClass.getSimpleName()));
            }
        });

        // We can have only one queen in colony
        Queen queen = new Queen(new Position(width / 2, height / 2));
        colony.add(queen);
    }
}
