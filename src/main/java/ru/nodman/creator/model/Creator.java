package ru.nodman.creator.model;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import ru.nodman.creator.model.structure.document.Chapter;
import ru.nodman.creator.model.structure.document.Part;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class Creator {
    private static final String HEAD_FIRST = "1";
    private static final String HEAD_SECOND = "2";
    private static final String HEAD_THIRD = "3";
    private static final String MAIN_TEXT = "-1";
    private SourceWorker sourceWorker;

    public Creator() {
        sourceWorker = new SourceWorker();
    }

    public void create(String fileName) {
        Linker linker = new Linker();
        HashMap<Integer, Chapter> text = linker.buildText();

        try (InputStream inputStream = new FileInputStream(fileName);
             XWPFDocument document = new XWPFDocument(inputStream)) {

//            List<XWPFParagraph> paragraphs = document.getParagraphs();
//            for (XWPFParagraph paragraph : paragraphs) {
//                if(paragraph.getText().equals("nodman")) {
//                    mainTextStyleId = paragraph.getStyleID();
//                }
//            }
//
            for (Chapter chapter : text.values()) {
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
                headRun.setText(chapter.getFullName());

                for (Part part : chapter.getParts()) {
                    String string = sourceWorker.insertInitialData(part);
                    if (string.contains("_ПУСТО_")) {
                        continue;
                    }

                    XWPFParagraph textParagraph = document.createParagraph();
                    textParagraph.setStyle(MAIN_TEXT);
                    XWPFRun textRun = textParagraph.createRun();
                    textRun.setText(string);
                }
            }


            FileOutputStream outputStream = new FileOutputStream(new File(fileName));
            document.write(outputStream);
            System.out.println("спешно записан в файл");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
