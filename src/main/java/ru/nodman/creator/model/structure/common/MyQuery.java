package ru.nodman.creator.model.structure.common;

import java.util.LinkedList;
import java.util.List;

public class MyQuery {
    private String tableName;
    private List<Cell> cells;

    public void setTableName(String tableName) {
        this.tableName = tableName;
        cells = new LinkedList<>();
    }

    public void setCell(String cellText, TypeOfQuery typeOfQuery) {
        if (cellText == null) {
            return;
        }
        cells.add(new Cell(cellText, typeOfQuery));
    }

    public String getTableName() {
        return tableName;
    }

    public List<Cell> getCells() {
        return cells;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyQuery query = (MyQuery) o;

        return tableName.equals(query.tableName);
    }

    @Override
    public int hashCode() {
        return tableName.hashCode();
    }

    @Override
    public String toString() {
        return tableName + " (записей: " + cells.size() + ')';
    }
}
