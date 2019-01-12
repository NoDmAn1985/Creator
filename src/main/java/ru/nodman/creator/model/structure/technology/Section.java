package ru.nodman.creator.model.structure.technology;

import java.util.LinkedList;
import java.util.List;

class Section {
    private Method method;
    private List<SubSection> subSections;

    Section(int countOfConditions) {
        subSections = new LinkedList<>();
        subSections.add(new SubSection(countOfConditions));
    }
}
