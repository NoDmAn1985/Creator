package ru.nodman.creator.model.structure.technology;

import java.util.ArrayList;
import java.util.List;

public class Technology {
    private Work work;
    private List<Section> sections;

    public Technology(String workName, int countOfConditions) {
        work = new Work(workName);
        sections = new ArrayList<>();
        sections.add(new Section(countOfConditions));
    }

    public void setWorkValue(String unit, int value, int decimalPlaces) {
        work.setValue(new Value(unit, value, decimalPlaces));
    }
}
