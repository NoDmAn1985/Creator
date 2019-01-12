package ru.nodman.creator;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.*;
import java.util.List;

public class Copier {
    public static void main(String[] args) {
        try {
            Logger.getRootLogger().setLevel(Level.ERROR);

            InputStream inputStream = new FileInputStream("first.docx");
            WordprocessingMLPackage document = WordprocessingMLPackage.load(inputStream);

            InputStream mainInputStream = new FileInputStream("main2.docx");
            WordprocessingMLPackage mainDocument = WordprocessingMLPackage.load(mainInputStream);

            List<Object> objects = document.getMainDocumentPart().getContent();
            for (Object o : objects) {
                mainDocument.getMainDocumentPart().getContent().add(o);
            }

            FileOutputStream outputStream = new FileOutputStream(new File("main2.docx"));
            mainDocument.save(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException | Docx4JException e) {
            e.printStackTrace();
        }
    }
}
