package ru.nodman.creator.model;

import ru.nodman.creator.model.structure.common.Cell;
import ru.nodman.creator.model.structure.common.MyQuery;
import ru.nodman.creator.model.structure.common.TypeOfQuery;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Holder {
    public HashMap<String, MyQuery> loadFromBase(MyQuery myQuery) {
        String tableName = myQuery.getTableName();
        String query = "SELECT * FROM " + tableName;
        HashMap<String, MyQuery> queries = new HashMap<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException cnfex) {
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }

        try {
            String msAccDB = "creator.accdb";
            String dbURL = "jdbc:ucanaccess://" + msAccDB;
            connection = DriverManager.getConnection(dbURL);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List <Cell> cells = myQuery.getCells();
            String record = null;
            while (resultSet.next()) {
                MyQuery newQuery = new MyQuery();
                for (Cell cell : cells) {
                    TypeOfQuery typeOfQuery = cell.getType();
                    String columnName = cell.getText();
                    switch (typeOfQuery) {
                        case INT:
                            if (record == null) {
                                break;
                            }
                            newQuery.setCell(record, TypeOfQuery.getTypeOfQuery(resultSet.getInt(columnName)));
                            break;
                        default:
                            record = resultSet.getString(columnName);
                            if (columnName.equals("Таблица")) {
                                newQuery.setTableName(record);
                            }
                            System.out.print(record);
                            break;
                    }
                    System.out.print("\t");
                }
                queries.put(newQuery.getTableName(), newQuery);
                System.out.println();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    assert resultSet != null;
                    resultSet.close();
                    statement.close();
                    connection.close();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return queries;
    }

    public List<List<Cell>> loadFromBaseVoid(MyQuery myQuery) {
        String tableName = myQuery.getTableName();
        String query = "SELECT * FROM " + tableName;

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException cnfex) {
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }

        List<List<Cell>> records = new LinkedList<>();
        try {
            String msAccDB = "creator.accdb";
            String dbURL = "jdbc:ucanaccess://" + msAccDB;
            connection = DriverManager.getConnection(dbURL);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Cell> cells = myQuery.getCells();

            while (resultSet.next()) {
                List<Cell> cellOfRecords = new LinkedList<>();
                boolean isLast = false;
                for (Cell cell : cells) {
                    if (isLast) {
                        break;
                    }
                    TypeOfQuery typeOfQuery = cell.getType();
                    String CellName = cell.getText();
                    switch (typeOfQuery) {
                        case INT:
                            int integerNumber = resultSet.getInt(CellName);
                            if (resultSet.wasNull()) {
                                isLast = true;
                                break;
                            }
                            cellOfRecords.add(new Cell(integerNumber, TypeOfQuery.INT));
                            break;
                        case DOUBLE:
                            double decimalNumber = resultSet.getDouble(CellName);
//                            if (resultSet.wasNull()) {
//                                isLast = true;
//                                break;
//                            }
                            cellOfRecords.add(new Cell(decimalNumber, TypeOfQuery.DOUBLE));
                            break;
                        case STRING:
                            String text = resultSet.getString(CellName);
//                            if (resultSet.wasNull()) {
//                                isLast = true;
//                                break;
//                            }
                            cellOfRecords.add(new Cell(text, TypeOfQuery.STRING));
                            break;
                    }
                }
                records.add(cellOfRecords);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    assert resultSet != null;
                    resultSet.close();
                    statement.close();
                    connection.close();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return records;
    }

}
