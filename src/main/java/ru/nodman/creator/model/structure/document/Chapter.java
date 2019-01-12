package ru.nodman.creator.model.structure.document;

import java.util.LinkedList;
import java.util.List;

public class Chapter {
    private int sqlCode;
    private int rang;
    private static int number = -1;
    private static int subNumber = 1;
    private int chapterNumber;
    private int chapterSubNumber;
    private String name;
    private String fullName;
    private List<Segment> segments;

    public Chapter(int sqlCode, int rang, String name) {
        this.sqlCode = sqlCode;
        this.rang = rang;
        if (rang == 1) {
            ++number;
            subNumber = 0;
        } else if (rang == 2) {
            ++subNumber;
            this.chapterSubNumber = subNumber;
        } else {
            this.chapterSubNumber = 0;
        }
        this.chapterNumber = number;
        this.name = name;
        String prefix = (this.chapterNumber == 0 ? "" : this.chapterNumber) +
                (chapterSubNumber == 0 ? "" : "." + this.chapterSubNumber);
        this.fullName = prefix + (prefix.length() > 0 ? " " : "") + this.name;
        this.segments = new LinkedList<>();
    }

    public void addSegment(Segment segment) {
        segments.add(segment);
    }

    public int getRang() {
        return rang;
    }

    public String getFullName() {
        return fullName;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(sqlCode).append(") ").append(fullName);
        for (Segment segment : segments) {
            System.out.print(">>> " + segment + " = ");
            System.out.println(segment.getSqlCode());
            sb.append(" ").append(segment.getSqlCode());
        }
        return sb.toString();
    }
}
