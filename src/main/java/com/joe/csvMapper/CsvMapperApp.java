package com.joe.csvMapper;
import org.apache.log4j.Logger;
import java.io.IOException;


public class CsvMapperApp {

    final static Logger logger = Logger.getLogger(CsvMapperApp.class);

    public static void main(String[] args) throws Exception {

        CsvFileParser parser = initParser();
        separateValidCSVFromInvalidCSV(parser);
        System.out.println(parser.getAllRecords());
        System.out.println(parser.getSuccessfulRecords());
        System.out.println(parser.getUnsuccessfulRecords());
        log(parser);
        SqliteDb db = null;
        try {
            db = new SqliteDb();
            AddRecords add = new AddRecords();
            add.addRecordsToTable();
        } catch (Exception e) {

        }finally {
            if (db != null)
                db.closeConn();
        }
    }

    private static CsvFileParser initParser() {
        UserInput input = new UserInput();
        CsvFileParser parser = new CsvFileParser(input.askForFile(), input.askForOffset());
        return parser;
    }

    private static void log(CsvFileParser parser) {
        logger.info("All csv values: " + String.valueOf(parser.getAllRecords()));
        logger.info("Successful csv values: " + String.valueOf(parser.getSuccessfulRecords()));
        logger.info("Unsuccessful csv values: " + String.valueOf(parser.getUnsuccessfulRecords()));
    }

    public static void separateValidCSVFromInvalidCSV(CsvFileParser parser) {
        try {
            parser.validCsvFile();
        } catch(IOException e){
            e.getMessage();
        }
    }


}
