package ru.nodman.creator.model.structure.document;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Block {
    private int sqlCode;
    private List<Part> parts;

    public Block(int sqlCode) {
        this.sqlCode = sqlCode;
        this.parts = new LinkedList<>();
    }

    public void addPart(Part part) {
        parts.add(part);
    }

    public int getSqlCode() {
        return sqlCode;
    }

    public List<Part> getParts() {
        return parts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(sqlCode).append(")");
        for (Part part : parts) {
            sb.append(" ").append(part.getSqlCode());
        }
        return sb.toString();
    }
}
