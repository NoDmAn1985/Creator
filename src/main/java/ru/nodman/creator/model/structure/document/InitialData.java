package ru.nodman.creator.model.structure.document;

import java.util.LinkedList;
import java.util.List;

public class InitialData {
    private int sqlCode;
    private String name;
    private int chosenVariantNumber = 1;
//    private int chosenVariantNumber = -1;
    private List<String> textVariants;

    public InitialData(int sqlCode, String name) {
        this.sqlCode = sqlCode;
        this.name = name;
        textVariants = new LinkedList<>();
    }

    public void addVariant(String text) {
        textVariants.add(text);
    }

    public boolean isOtherVariant() {
        return textVariants.size() > 1;
    }

    public int getSqlCode() {
        return sqlCode;
    }

    public String getName() {
        return name;
    }

    public List<String> getTextVariants() {
        return textVariants;
    }

    public String getChosenVariant() {
        return textVariants.get(chosenVariantNumber - 1);
    }

    public void setChosenVariantNumber(int chosenVariantNumber) {
        this.chosenVariantNumber = chosenVariantNumber;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder()
                .append(sqlCode).append(") ").append(name).append(": ");
        for (String textVariant : textVariants) {
            sb.append(textVariant).append(", ");
        }
        return sb.toString();
    }
}
