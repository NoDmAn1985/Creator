package ru.nodman.creator.model.structure.common;

public class Cell {
    private String text;
    private int integerNumber;
    private double decimalNumber;
    private TypeOfQuery type;

    public Cell(String text, int integerNumber, double decimalNumber, TypeOfQuery type) {
        this.text = text;
        this.integerNumber = integerNumber;
        this.decimalNumber = decimalNumber;
        this.type = type;
    }

    public Cell(String text, TypeOfQuery type) {
        this(text, -1, -1, type);
    }

    public Cell(int integerNumber, TypeOfQuery type) {
        this(null, integerNumber, -1, type);
    }

    public Cell(double decimalNumber, TypeOfQuery type) {
        this(null, -1, decimalNumber, type);
    }

    public String getText() {
        return text;
    }

    public int getIntegerNumber() {
        return integerNumber;
    }

    public double getDecimalNumber() {
        return decimalNumber;
    }

    public TypeOfQuery getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "text='" + text + '\'' +
                ", integerNumber=" + integerNumber +
                ", decimalNumber=" + decimalNumber +
                ", type=" + type +
                '}';
    }
}
