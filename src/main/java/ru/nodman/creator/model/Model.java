package ru.nodman.creator.model;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.nodman.creator.model.structure.document.Chapter;

import java.util.HashMap;

public class Model {

    public void createFile(String fileName) {
        Linker linker = new Linker();
        HashMap<Integer, Chapter> text = linker.buildText();

        FileManager fileManager = new FileManager();

        XWPFDocument document = fileManager.getDocument(fileName);

        Formatter formatter = new Formatter(document);
        formatter.formatTextForWordDocument(text);

        fileManager.saveDocument();
    }
}
