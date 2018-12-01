import java.io.FileNotFoundException;
import java.sql.*;

public class SqliteDb {
    private static Connection conn;
    private static boolean hasData = false;

    public ResultSet displayItems() throws SQLException, ClassNotFoundException {
        if (conn == null) {
            getConnection();
        }

        Statement state = conn.createStatement();
        ResultSet res = state.executeQuery("SELECT a FROM ourTable");
        return res;
    }

    private void getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        initialize();
    }

    private void initialize() throws SQLException, ClassNotFoundException {
        if (!hasData) {
            hasData = true;
            Statement state = conn.createStatement();
//            if(isTable()){
//                deleteTable();
//            }
//            ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='ourTable'");
            if(!isTable()) {
                System.out.println("Building sql table");
                Statement createTableStatement = conn.createStatement();
                createTableStatement.execute("CREATE TABLE ourTable ("
                                            + "a varchar(1000),"
                                            + "b varchar(1000),"
                                            + "c varchar(1000),"
                                            + "d varchar(1000),"
                                            + "e varchar(1000),"
                                            + "f varchar(1000),"
                                            + "g varchar(1000),"
                                            + "h varchar(1000),"
                                            + "i varchar(1000),"
                                            + "j varchar(1000));");
            }
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
        PreparedStatement prep = conn.prepareStatement("INSERT INTO ourTable values(?,?,?,?,?,?,?,?,?,?);");
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
    }

    private boolean isTable() throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet res = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='ourTable'");
        if(!res.next()) {
            return false;
        }
        return true;
    }

    public void deleteTable() throws SQLException {
        if(isTable()) {
            Statement deleteState = conn.createStatement();
            String sqlCommand = "DROP TABLE 'ourTable' ";
            deleteState.executeUpdate(sqlCommand);
        }
    }

    public void closeConn()throws SQLException{
        conn.close();
    }



}
