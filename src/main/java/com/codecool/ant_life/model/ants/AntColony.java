package com.codecool.ant_life.model.ants;

import com.codecool.ant_life.model.Animal;
import com.codecool.ant_life.model.Colony;
import com.codecool.ant_life.model.Position;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
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
        colony.stream().filter(ant -> !(ant instanceof Queen)).forEach(ant -> {
            boolean isMoveFound = false;

            do {
                Position nextPosition = ant.getNextPosition();

                if (isMoveInsideTheColony(nextPosition)) {
                    ant.setPosition(nextPosition);
                    isMoveFound = true;
                }
            } while (!isMoveFound);
        });
    }

    @Override
    public void generateNewColony(Map<Class<? extends Animal>, Integer> colonyConfiguration) {
        colonyConfiguration.forEach((antTypeClass, antAmount) -> {
            if (antTypeClass.equals(Drone.class)) {
                IntStream.range(0, antAmount).forEach(ant -> colony.add(new Drone(generateRandomAntPosition())));
            } else if (antTypeClass.equals(Worker.class)) {
                IntStream.range(0, antAmount).forEach(ant -> colony.add(new Worker(generateRandomAntPosition())));
            } else if (antTypeClass.equals(Soldier.class)) {
                IntStream.range(0, antAmount).forEach(ant -> colony.add(new Soldier(generateRandomAntPosition())));
            } else {
                throw new IllegalArgumentException(String.format("Ant %s is not supported!", antTypeClass.getSimpleName()));
            }
        });

        // We can have only one queen in colony
        Queen queen = new Queen(new Position(width / 2, height / 2));
        colony.add(queen);
    }

    private Position generateRandomAntPosition() {
        Random random = new Random();

        int x = random.nextInt(width);
        int y = random.nextInt(height);

        return new Position(x, y);
    }

    private boolean isMoveInsideTheColony(Position positionToCheck) {
        return positionToCheck.getX() >= 0 && positionToCheck.getX() < width
                && positionToCheck.getY() >= 0 && positionToCheck.getY() < height;
    }
}
