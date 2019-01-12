package ru.nodman.creator;

import ru.nodman.creator.model.Model;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        model.createFile("result.docx");
    }
}
