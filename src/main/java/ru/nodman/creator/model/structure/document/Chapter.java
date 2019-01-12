package ru.nodman.creator.model.structure.document;

import java.util.LinkedList;
import java.util.List;

public class Chapter {
    private int sqlCode;
    private boolean isNumbered = false;
    private int rang;
    private String name;
    private List<Segment> segments;

    public Chapter(int sqlCode, boolean isNumbered, int rang, String name) {
        this.sqlCode = sqlCode;
        this.isNumbered = isNumbered;
        this.rang = rang;
        this.name = name;
        this.segments = new LinkedList<>();
    }

    public void addSegment(Segment segment) {
        segments.add(segment);
    }

    public int getRang() {
        return rang;
    }

    public boolean isNumbered() {
        return isNumbered;
    }

    public List<Part> getParts() {
        List<Part> parts = new LinkedList<>();
        for (Segment segment : segments) {
            List<Block> blocks = segment.getBlocks();
            for (Block block : blocks) {
                parts.addAll(block.getParts());
            }
        }
        return parts;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(sqlCode).append(") ").append(name);
        for (Segment segment : segments) {
            sb.append(" ").append(segment.getSqlCode());
        }
        return sb.toString();
    }
}
