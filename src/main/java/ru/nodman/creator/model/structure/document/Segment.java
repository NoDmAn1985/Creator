package ru.nodman.creator.model.structure.document;

import java.util.LinkedList;
import java.util.List;

public class Segment {
    private int sqlCode;
    private List<Block> blocks;

    public Segment(int sqlCode) {
        this.sqlCode = sqlCode;
        this.blocks = new LinkedList<>();
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public int getSqlCode() {
        return sqlCode;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(sqlCode).append(")");
        for (Block block : blocks) {
            sb.append(" ").append(block.getSqlCode());
        }
        return sb.toString();
    }
}
