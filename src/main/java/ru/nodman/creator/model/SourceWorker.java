package ru.nodman.creator.model;

import ru.nodman.creator.model.structure.document.InitialData;
import ru.nodman.creator.model.structure.document.Part;

import java.util.List;

class SourceWorker {
    //_Ин:1_
    //012345
    private static final String BEGGINING_OF_INITIAL_DATA_PLACE_PREFIX = "_И";
    private static final int INITIAL_DATA_NUMBER_POSITION = 4;
    private static final int LENGTH_OF_INITIAL_DATA_PLACE = 6;
    private static final int REGISTER_CASE_POSITION = 2;
    private static final char UPPER_CASE_REGISTER_CHAR = 'В';
    private static final char LOWER_CASE_REGISTER_CHAR = 'н';

    String insertInitialData(Part part) {
        System.out.println("вставляю и.д. в " + part.getText());

        StringBuilder sb = new StringBuilder(part.getText());
        List<InitialData> dataList = part.getInitialData();

        System.out.println("длина списка " + dataList.size());

        int endOfInitialDataPlace = 0;
        for (int i = 0; i < dataList.size(); ++i) {
            int beginOfInitialDataPlace = sb.indexOf(BEGGINING_OF_INITIAL_DATA_PLACE_PREFIX, endOfInitialDataPlace);
            int dataIndex = Character.getNumericValue(sb.charAt(beginOfInitialDataPlace +
                    INITIAL_DATA_NUMBER_POSITION));

            if (dataIndex < 0) {
                break;
            }

            InitialData data = dataList.get(dataIndex - 1);

            System.out.println("выбран вариант = " + data.getChosenVariant());
            StringBuilder sbNew = new StringBuilder(data.getChosenVariant());
            char registerChar = sb.charAt(beginOfInitialDataPlace + REGISTER_CASE_POSITION);

            System.out.println("буква регистра " + registerChar);

            if (registerChar == UPPER_CASE_REGISTER_CHAR) {
                sbNew.setCharAt(0, Character.toUpperCase(sbNew.charAt(0)));
                System.out.println("первая буква увеличена " + sbNew.toString());
            } else if (registerChar == LOWER_CASE_REGISTER_CHAR) {
                sbNew.setCharAt(0, Character.toLowerCase(sbNew.charAt(0)));
                System.out.println("первая буква уменьшена " + sbNew.toString());
            }

            endOfInitialDataPlace = beginOfInitialDataPlace + LENGTH_OF_INITIAL_DATA_PLACE;

            System.out.println("заменяю " + sb.toString().substring(beginOfInitialDataPlace, endOfInitialDataPlace));

            sb.replace(beginOfInitialDataPlace, endOfInitialDataPlace, sbNew.toString());
        }
        return sb.toString();
    }
}
