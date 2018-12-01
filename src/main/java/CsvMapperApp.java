import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CsvMapperApp {

    public static void main(String[] args) {

        CsvFileParser parser = initParser();
        separateValidCSVFromInvalidCSV(parser);
        System.out.println(parser.getAllRecords());
        System.out.println(parser.getSuccessfulRecords());
        System.out.println(parser.getUnsuccessfulRecords());

        SqliteDb db = new SqliteDb();
        ResultSet rs;
        try {
            rs = db.displayItems();
            System.out.println(rs.getString("a"));
        } catch (SQLException  | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    private static CsvFileParser initParser() {
        UserInput input = new UserInput();

//        File file = new File("/Users/Joe/source/ms3Interview.csv");
        CsvFileParser parser = new CsvFileParser(input.askForFile(), input.askForOffset());
        return parser;
    }

    protected static void separateValidCSVFromInvalidCSV(CsvFileParser parser) {
        try {
            parser.validCsvFile();
        } catch(IOException e){
            e.getMessage();
        }
    }


}
