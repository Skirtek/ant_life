package com.codecool.ant_life.model;

import java.util.List;
import java.util.Map;

public interface Colony<T extends Animal> {
    List<T> getColony();

    Map<Class<? extends Animal>, Integer> getInfoAboutColonyStructure();

    void addMember(T newMember);

    void removeMember(T member);

    void update();

    // Drone: 20
    // Queen: 1
    // Soldier: 40
    // Worker: 70

    // Klucz: wartość
    // Klucz: wartość...
    void generateNewColony(Map<Class<? extends Animal>, Integer> colonyConfiguration);

    default int getWidth() {
        return 40;
    }

    default int getHeight() {
        return 20;
    }
}
