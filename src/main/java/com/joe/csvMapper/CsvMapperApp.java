package com.joe.csvMapper;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.sql.SQLException;


public class CsvMapperApp {

    final static Logger logger = Logger.getLogger(CsvMapperApp.class);

    public static void main(String[] args) {

        CsvFileParser parser = initParser();
        separateValidCSVFromInvalidCSV(parser);
        logStatistics(parser);
        populateDatabase();
    }

    private static CsvFileParser initParser() {
        UserInput input = new UserInput();
        CsvFileParser parser = new CsvFileParser(input.askForFile(), input.askForOffset());
        return parser;
    }

    private static void populateDatabase() {
        SqliteDb db = null;
        try {
            db = new SqliteDb();
            AddRecords add = new AddRecords();
            add.addRecordsToTable();
        } finally {
            try {
                if (db != null)
                    db.closeConn();
            } catch (SQLException e) {
                logger.error("Error when trying to close database connection", e);
            }
        }
    }

    private static void logStatistics(CsvFileParser parser) {
        logger.info("All csv values: " + String.valueOf(parser.getAllRecords()));
        logger.info("Successful csv values: " + String.valueOf(parser.getSuccessfulRecords()));
        logger.info("Unsuccessful csv values: " + String.valueOf(parser.getUnsuccessfulRecords()));
    }

    public static void separateValidCSVFromInvalidCSV(CsvFileParser parser) {
        try {
            parser.createFileBasedOnCsvValidity();
        } catch(IOException e){
            e.getMessage();
        }
    }


}
