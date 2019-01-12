package ru.nodman.creator.model;

import ru.nodman.creator.model.structure.document.Chapter;
import ru.nodman.creator.model.structure.document.InitialData;

import java.util.HashMap;

public class Calculator {
//    HashMap<Integer, InitialData> data;
    private int tableNumber;
    private int chapterNumber;
    private int chapterSubNumber;
    private int lastTableNumber;

    Calculator() {
//        this.data = data;
        this.tableNumber = 1;
        this.chapterNumber = 0;
        this.chapterSubNumber = 0;
        lastTableNumber = tableNumber;
    }

    int getNewTableNumber() {
        int number = tableNumber;
        lastTableNumber = tableNumber;
        ++tableNumber;
        return number;
    }

    int getTableNumber() {
        return lastTableNumber;
    }

    String getPrefixForChapter(Chapter chapter) {
        if (!chapter.isNumbered()) {
            return "";
        }
        int rang = chapter.getRang();
        if (rang == 1) {
            ++chapterNumber;
            chapterSubNumber = 0;
            return chapterNumber + " ";
        } else if (rang == 2) {
            ++chapterSubNumber;
            return chapterNumber + "." + chapterSubNumber + " ";
        } else {
            chapterSubNumber = 0;
            return "";
        }
    }
}
