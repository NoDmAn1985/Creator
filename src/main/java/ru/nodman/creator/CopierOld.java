package ru.nodman.creator;

import org.apache.poi.xwpf.usermodel.*;
import java.io.*;
import java.util.List;

public class CopierOld {
    public static void main(String[] args) {
        try {
            InputStream inputStream = new FileInputStream("first.docx");
            XWPFDocument document = new XWPFDocument(inputStream);

            List<XWPFParagraph> paragraphs = document.getParagraphs();

//            XWPFDocument mainDocument = new XWPFDocument();
//            try {
//                mainDocument.createStyles().setStyles(document.getStyle());
//            } catch (XmlException e) {
//                e.printStackTrace();
//            }

            InputStream mainInputStream = new FileInputStream("main2.docx");
            XWPFDocument mainDocument = new XWPFDocument(mainInputStream);

            for (XWPFParagraph paragraph : paragraphs) {
                if (!paragraph.getParagraphText().isEmpty()) {
                    XWPFParagraph mainDocumentParagraph = mainDocument.createParagraph();
                    copyAllRunsToAnotherParagraph(paragraph, mainDocumentParagraph);
                    String styleId = paragraph.getStyleID();
                    System.out.println(styleId);
                    if (styleId != null) {
                        mainDocumentParagraph.setStyle(styleId);
                        System.out.println(mainDocumentParagraph.getStyle());
                    }
                    mainDocumentParagraph.setAlignment(paragraph.getAlignment());
                    mainDocumentParagraph.setFirstLineIndent(paragraph.getFirstLineIndent());
                }
            }

            FileOutputStream outputStream = new FileOutputStream(new File("main2.docx"));
            mainDocument.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyAllRunsToAnotherParagraph(XWPFParagraph paragraph, XWPFParagraph copyOfParagraph) {

        for (XWPFRun run : paragraph.getRuns()) {
            String textInRun = run.getText(0);
            if (textInRun == null || textInRun.isEmpty()) {
                continue;
            }

            XWPFRun copyOfParagraphRun = copyOfParagraph.createRun();

            copyOfParagraphRun.setText(textInRun);
            copyOfParagraphRun.setBold(run.isBold());
            copyOfParagraphRun.setItalic(run.isItalic());
            copyOfParagraphRun.setColor(run.getColor());
            copyOfParagraphRun.setUnderline(run.getUnderline());
            copyOfParagraphRun.setEmphasisMark(run.getEmphasisMark().toString());
            copyOfParagraphRun.setCapitalized(run.isCapitalized());
        }
    }
}
