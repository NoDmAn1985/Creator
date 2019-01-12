package ru.nodman.creator.model;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

class FileManager {
    private XWPFDocument document;
    private String fileName;

    XWPFDocument getDocument(String fileName) {
        this.fileName = fileName;
        try (InputStream inputStream = new FileInputStream(fileName)) {
             document = new XWPFDocument(inputStream);
            return document;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    void saveDocument() {
        try (FileOutputStream outputStream = new FileOutputStream(new File(fileName))) {
            document.write(outputStream);
            System.out.println("спешно записан в файл");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
