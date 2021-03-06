package ru.nodman.creator.model.structure.common;

public enum TypeOfQuery {
    INT(1), DOUBLE(2), STRING(3), BOOLEAN(4);
    private int type;

    TypeOfQuery(int type) {
        this.type = type;
    }

    public static TypeOfQuery getTypeOfQuery(int type) {
        switch (type) {
            case 1:
                return INT;
            case 2:
                return DOUBLE;
            case 4:
                return BOOLEAN;
            default:
                return STRING;
        }

    }

    public int getType() {
        return type;
    }
}
