package ru.nodman.creator;

import ru.nodman.creator.model.Creator;

public class Main {
    public static void main(String[] args) {
        Creator creator = new Creator();
        creator.create("result.docx");
    }
}
