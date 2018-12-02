package com.joe.csvMapper;

import java.io.*;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class AddRecords {
    UserInput input;
    SqliteDb database;

    private static Logger logger = Logger.getLogger(AddRecords.class);

    public AddRecords() {
        input = new UserInput();
        database = new SqliteDb();
    }

    public File addRecordsToTable() {

        File csvFile = input.getFile();
        File validCsv = new File(csvFile.getParent() + "/validCsv.csv");
        BufferedReader br = null;

        //write to database
        try {
            br = new BufferedReader(new FileReader(validCsv.getPath()));
            String line = br.readLine();
            while(line != null) {
                writeToDatabase(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            logger.error("Error occurred while reading file in class AddRecords: ", e);
        } finally {
            closeFile(br);
        }

        return validCsv;
    }

    private void writeToDatabase(String line) {
        String[] record = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        try {
            database.addrecord(record);
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Error occurred during adding record to db: ", e);
        }
    }

    private void closeFile(BufferedReader br) {
        try {
            if (br != null) {
                br.close();
            }
        } catch (IOException e) {
            logger.error("Error occurred while closing file in class AddRecords: ", e);
        }
    }
}
