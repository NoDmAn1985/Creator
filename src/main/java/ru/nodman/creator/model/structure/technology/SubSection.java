package ru.nodman.creator.model.structure.technology;

import java.util.LinkedList;
import java.util.List;

public class SubSection {
    private Value value;
    private List<SpecialCondition> specialConditions;

    public SubSection(int countOfConditions) {
        value = new Value(0);
        specialConditions = new LinkedList<>();
        for (int i = 0; i < countOfConditions - 1; i++) {
            specialConditions.add(new SpecialCondition(""));
        }
    }
}
