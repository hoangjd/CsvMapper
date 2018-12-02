package com.joe.csvMapper;

import java.sql.*;

public class SqliteDb {
    private static Connection conn;

    private void getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        initialize();
    }
    private void getNewConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:database.db");
    }

    private void initialize() throws SQLException, ClassNotFoundException {
            if(!isTable()) {
                System.out.println("Building sql table...");
                Statement createTableStatement = conn.createStatement();
                createTableStatement.execute("CREATE TABLE csvMapping ("
                                            + "a varchar(1000),"
                                            + "b varchar(1000),"
                                            + "c varchar(1000),"
                                            + "d varchar(1000),"
                                            + "e varchar(4000),"
                                            + "f varchar(1000),"
                                            + "g varchar(1000),"
                                            + "h varchar(1000),"
                                            + "i varchar(1000),"
                                            + "j varchar(1000));");
            } else {
                deleteTable();
                initialize();
            }
    }

    public void addrecord(String[] values) throws SQLException, ClassNotFoundException {
        if (conn == null) {
            getConnection();
        }

        if (values.length != 10) {
            System.out.println("Invalid Value");
            return;
        }

        PreparedStatement prep = conn.prepareStatement("INSERT INTO csvMapping values(?,?,?,?,?,?,?,?,?,?);");
        prep.setString(1,values[0]);
        prep.setString(2,values[1]);
        prep.setString(3,values[2]);
        prep.setString(4,values[3]);
        prep.setString(5,values[4]);
        prep.setString(6,values[5]);
        prep.setString(7,values[6]);
        prep.setString(8,values[7]);
        prep.setString(9,values[8]);
        prep.setString(10,values[9]);
        prep.execute();
        prep.close();

    }

    private boolean isTable() throws SQLException {
        boolean hasTable;
        Statement statement = conn.createStatement();
        ResultSet res = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='csvMapping'");

        if(!res.next())
            hasTable = false;
        else
            hasTable = true;

        res.close();
        statement.close();
        return hasTable;
    }

    public void deleteTable() throws SQLException, ClassNotFoundException {
            if (conn != null) {
                getNewConnection();
            }
            if (isTable()) {
                Statement deleteState = conn.createStatement();
                String sqlCommand = "DROP TABLE 'csvMapping' ";
                deleteState.executeUpdate(sqlCommand);
                deleteState.close();
            }
    }


    public void closeConn()throws SQLException{
        conn.close();
    }



}
