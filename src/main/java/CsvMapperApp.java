import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;


public class CsvMapperApp {

    final static Logger logger = Logger.getLogger("CsvMapperApp.class");

    public static void main(String[] args) throws Exception {

        CsvFileParser parser = initParser();
        separateValidCSVFromInvalidCSV(parser);
        System.out.println(parser.getAllRecords());
        System.out.println(parser.getSuccessfulRecords());
        System.out.println(parser.getUnsuccessfulRecords());

        SqliteDb db = new SqliteDb();
        AddRecords add = new AddRecords();
        add.addRecordsToTable();
        db.closeConn();


    }


    private static CsvFileParser initParser() {
        UserInput input = new UserInput();

//        File file = new File("/Users/Joe/source/ms3Interview.csv");
        CsvFileParser parser = new CsvFileParser(input.askForFile(), input.askForOffset());
//        CsvFileParser parser = new CsvFileParser(new File ("/Users/Joe/source/ms3Interview.csv"), 0);
        return parser;
    }

    private static void log(CsvFileParser parser) {
        logger.info("All csv values:" + String.valueOf(parser.getAllRecords()));
        logger.info("Successful csv values:" + String.valueOf(parser.getSuccessfulRecords()));
        logger.info("Unsuccessful csv values:" + String.valueOf(parser.getUnsuccessfulRecords()));
    }

    protected static void separateValidCSVFromInvalidCSV(CsvFileParser parser) {
        try {
            parser.validCsvFile();
        } catch(IOException e){
            e.getMessage();
        }
    }


}
