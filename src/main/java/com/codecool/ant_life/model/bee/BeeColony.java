package com.codecool.ant_life.model.bee;

import com.codecool.ant_life.model.Animal;
import com.codecool.ant_life.model.Colony;

import java.util.List;
import java.util.Map;

public class BeeColony implements Colony<Bee> {
    @Override
    public List<Bee> getColony() {
        return null;
    }

    @Override
    public Map<Class<? extends Animal>, Integer> getInfoAboutColonyStructure() {
        return null;
    }

    @Override
    public void addMember(Bee newMember) {

    }

    @Override
    public void removeMember(Bee member) {

    }

    @Override
    public void update() {

    }

    @Override
    public void generateNewColony(Map<Class<? extends Animal>, Integer> colonyConfiguration) {

    }
}
