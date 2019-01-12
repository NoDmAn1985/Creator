package ru.nodman.creator.model.structure.technology;

public class Work {
    private String name;
    private Value value;
    private Value alternativeValue;

    public Work(String name, Value value, Value alternativeValue) {
        this.name = name;
        this.value = value;
        this.alternativeValue = alternativeValue;
    }

    public Work(String name, Value value) {
        this.name = name;
        this.value = value;
    }

    public Work(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void setAlternativeValue(Value alternativeValue) {
        this.alternativeValue = alternativeValue;
    }

    public String getName() {
        return name;
    }

    public Value getValue() {
        return value;
    }

    public Value getAlternativeValue() {
        return alternativeValue;
    }
}
