package ru.nodman.creator.model;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import ru.nodman.creator.model.structure.document.Chapter;
import ru.nodman.creator.model.structure.document.Part;

import java.util.HashMap;

class Formatter {
    private static final String HEAD_FIRST = "1";
    private static final String HEAD_SECOND = "2";
    private static final String HEAD_THIRD = "3";
    private static final String MAIN_TEXT = "-1";
    private XWPFDocument document;
    private SourceWorker sourceWorker;
    private Calculator calculator;

    Formatter(XWPFDocument document) {
        this.document = document;
        sourceWorker = new SourceWorker();
        calculator = new Calculator();
    }

    void formatTextForWordDocument(HashMap<Integer, Chapter> text) {
        for (Chapter chapter : text.values()) {
            formatChapter(chapter);
            for (Part part : chapter.getParts()) {
                String string = sourceWorker.insertInitialData(part);
                formatPart(string);
            }
        }
    }

    private void formatPart(String string) {
        if (string.equals("_ПУСТО_")) {
            return;
        }
        XWPFParagraph textParagraph = document.createParagraph();
        textParagraph.setStyle(MAIN_TEXT);
        if (!Character.isLetter(string.charAt(0))) {
            textParagraph.setFirstLineIndent(0);
        }
        XWPFRun textRun = textParagraph.createRun();
        textRun.setText(string);
    }

    private void formatChapter(Chapter chapter) {
        XWPFParagraph headParagraph = document.createParagraph();
        int rang = chapter.getRang();
        String styleName;
        switch (rang) {
            case 1:
                styleName = HEAD_FIRST;
                break;
            case 2:
                styleName = HEAD_SECOND;
                break;
            default:
                styleName = HEAD_THIRD;
                break;
        }

        headParagraph.setStyle(styleName);
        XWPFRun headRun = headParagraph.createRun();
        String prefixForChapter = calculator.getPrefixForChapter(chapter);
        headRun.setText(prefixForChapter + chapter.getName());
    }
}
