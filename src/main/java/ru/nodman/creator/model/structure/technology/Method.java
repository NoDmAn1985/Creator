package ru.nodman.creator.model.structure.technology;

import java.util.LinkedList;
import java.util.List;

public class Method {
    private String text;
    private List<Mechanism> mechanisms;

    public Method(String text, List<Mechanism> mechanisms) {
        this.text = text;
        this.mechanisms = new LinkedList<>(mechanisms);
    }

    public String getText() {
        return text;
    }

    public List<Mechanism> getMechanisms() {
        return mechanisms;
    }
}
