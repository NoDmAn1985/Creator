package ru.nodman.creator.model.structure.document;

import java.util.LinkedList;
import java.util.List;

public class Part {
    private int sqlCode;
    private String text;
    private StringBuilder textAlternative;
    private List<InitialData> initialData;
    private List<Rule> rules;

    public Part(int sqlCode, String text) {
        this.sqlCode = sqlCode;
        this.text = text;
        this.initialData = new LinkedList<>();
    }

    public int getSqlCode() {
        return sqlCode;
    }

    public String getText() {
        return text;
    }

    public void addInitialData(InitialData date) {
        initialData.add(date);
    }

    @Override
    public String toString() {
        return sqlCode + ") " + text;
    }

    public List<InitialData> getInitialData() {
        return initialData;
    }
}
