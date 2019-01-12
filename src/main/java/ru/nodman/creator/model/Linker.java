package ru.nodman.creator.model;

import ru.nodman.creator.model.structure.common.Cell;
import ru.nodman.creator.model.structure.common.MyQuery;
import ru.nodman.creator.model.structure.common.TypeOfQuery;
import ru.nodman.creator.model.structure.document.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Linker {
    private final static String FIRST_QUERY = "Запросы";
    private final static int FIRST_QUERY_COLUMN_COUNT = 26;
    private final static int MAX_COLUMN_COUNT = 12;
    private Holder holder;
    private HashMap<String, MyQuery> queries;
    private HashMap<Integer, InitialData> initialData;
    private HashMap<Integer, Part> parts;
    private HashMap<Integer, Block> blocks;
    private HashMap<Integer, Segment> segments;
    private HashMap<Integer, Chapter> text;

    HashMap<Integer, Chapter> buildText() {
        MyQuery firstQuery = new MyQuery();
        firstQuery.setTableName(FIRST_QUERY);
        firstQuery.setCell("Таблица", TypeOfQuery.STRING);
//        firstQuery.setCell("Количество столбцов", TypeOfQuery.INT);
        for (int i = 0; i < MAX_COLUMN_COUNT; i++) {
            firstQuery.setCell("С_" + (i + 1), TypeOfQuery.STRING);
            firstQuery.setCell("Тип С_" + (i + 1), TypeOfQuery.INT);
        }

        holder = new Holder();

        queries = holder.loadFromBase(firstQuery);
        for (Map.Entry<String, MyQuery> query : queries.entrySet()) {
            System.out.println(query);
        }

        createInitialData();
        createParts();
        createBlocks();
        createSegments();
        createText();

        System.out.println("ВСЁ");
        return text;
    }

    private void createInitialData() {
        initialData = new HashMap<>();
        List<List<Cell>> records = holder.loadFromBaseVoid(queries.get("Исходники"));
        for (List<Cell> cells : records) {
            int sqlCode = cells.get(0).getIntegerNumber();
            InitialData data = new InitialData(sqlCode, cells.get(1).getText());
            initialData.put(sqlCode, data);
            for (int i = 2; i < cells.size(); i++) {
                data.addVariant(cells.get(i).getText());
            }
        }

        for (InitialData data : initialData.values()) {
            System.out.println(data);
        }
        System.out.println(initialData.size());
    }

    private void createParts() {
        parts = new HashMap<>();
        List<List<Cell>> records = holder.loadFromBaseVoid(queries.get("Части"));
        for (List<Cell> cells : records) {
            int sqlCode = cells.get(0).getIntegerNumber();
            Part part = new Part(sqlCode, cells.get(1).getText());
            parts.put(sqlCode, part);

            for (int index = 3; index < cells.size(); index = index + 2) {
                int initialDataSQLCode = cells.get(index).getIntegerNumber();
                part.addInitialData(initialData.get(initialDataSQLCode));
            }
        }

        System.out.println("Части:");
        for (Part part : parts.values()) {
            System.out.println(part);
        }
        System.out.println(parts.size());
    }

    private void createBlocks() {
        blocks = new HashMap<>();
        List<List<Cell>> records = holder.loadFromBaseVoid(queries.get("Блоки"));
        for (List<Cell> cells : records) {
            int sqlCode = cells.get(0).getIntegerNumber();
            Block block = new Block(sqlCode);
            blocks.put(sqlCode, block);
            for (int i = 1; i < cells.size(); i++) {
                Part part = parts.get(cells.get(i).getIntegerNumber());
                block.addPart(part);
            }
        }
        System.out.println("Блоки:");
        for (Block block : blocks.values()) {
            System.out.println(block);
        }
        System.out.println(blocks.size());
    }

    private void createSegments() {
        segments = new HashMap<>();
        List<List<Cell>> records = holder.loadFromBaseVoid(queries.get("Сегменты"));
        for (List<Cell> cells : records) {
            int sqlCode = cells.get(0).getIntegerNumber();
            Segment segment = new Segment(sqlCode);
            segments.put(sqlCode, segment);
            for (int i = 1; i < cells.size(); i++) {
                Block block = blocks.get(cells.get(i).getIntegerNumber());
                segment.addBlock(block);
            }
        }
        System.out.println("Сегменты:");
        for (Segment segment : segments.values()) {
            System.out.println(segment);
        }
        System.out.println(segments.size());
    }

    private void createText() {
        text = new HashMap<>();
        List<List<Cell>> records = holder.loadFromBaseVoid(queries.get("ПОСПпл"));
        for (List<Cell> cells : records) {
            int sqlCode = cells.get(0).getIntegerNumber();
            boolean isNumbered = cells.get(1).getBooleanSign();
            int rang = cells.get(2).getIntegerNumber();
            String name = cells.get(3).getText();
            Chapter chapter = new Chapter(sqlCode, isNumbered, rang, name);
            text.put(sqlCode, chapter);
            for (int i = 4; i < cells.size(); i = i + 2) {
                System.out.println(cells.get(i).getIntegerNumber());
                Segment segment = segments.get(cells.get(i).getIntegerNumber());
                chapter.addSegment(segment);
            }
        }

        System.out.println("Текст:");
        for (Chapter chapter : text.values()) {
            System.out.println(chapter);
        }
        System.out.println(text.size());
    }

}
