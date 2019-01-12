package ru.nodman.creator.model.structure.technology;

public class Value {
    private String unit;
    private int value;
    private int decimalPlaces;

    Value(String unit, int value, int decimalPlaces) {
        this.unit = unit;
        this.value = value;
        this.decimalPlaces = decimalPlaces;
    }

    Value(String unit, int value) {
        this(unit, value, 0);
    }

    Value(int value) {
        this("", value, 0);
    }

    String getValue() {
        if (value != 0) {
            if (decimalPlaces > 0) {
                return String.valueOf(value / decimalPlaces) + "," +
                        value % ((int) Math.pow(10, decimalPlaces)) + "\u00A0" + unit;
            } else {
                return String.valueOf(value) + "\u00A0" + unit;
            }
        } else {
            return "";
        }
    }
}
