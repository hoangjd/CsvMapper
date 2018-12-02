package com.joe.csvMapper;

import java.io.*;

public class CsvFileParser {

    private File csvFile;
    private int successfulRecords = 0;
    private int unsuccessfulRecords = 0;
    private int allRecords = 0;
    private int offset;
    private CurrentTime currentTime = new CurrentTime();
    private SqliteDb database;

    public CsvFileParser(File file, int offset) {
        this.csvFile = file;
        this.offset = offset;
        database = new SqliteDb();
    }

    public File validCsvFile() throws IOException {
        //initialize necessary tools for writing to files
        File validCsv = new File(csvFile.getParent() + "/validCsv.csv");
        File invalidCsv = new File(csvFile.getParent() + "/bad-data-<" + currentTime.getCurrentTime() + ">.csv");
        BufferedReader br = null;
        PrintWriter validPrintWriter = null;
        PrintWriter invalidPrintWriter = null;

        //write to files
        try {
            br = new BufferedReader(new FileReader(csvFile.getPath()));
            validPrintWriter = new PrintWriter(new FileOutputStream(validCsv.getPath()), false);
            invalidPrintWriter = new PrintWriter(new FileOutputStream(invalidCsv.getPath()), false);
            String line = br.readLine();

            while(line != null) {
                writeToFiles(invalidPrintWriter, validPrintWriter, line);
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeFiles(br,invalidPrintWriter,validPrintWriter);
        }
        return validCsv;
    }

    private void writeToFiles(PrintWriter invalidPrintWriter, PrintWriter validPrintWriter, String line) {
        if (offset <= 0) {
            String[] record = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            if (isValidRecord(record) && record.length == 10) {
                successfulRecords++;
                validPrintWriter.println(line);
            } else if (!isValidRecord(record) || record.length != 10) {
                unsuccessfulRecords++;
                invalidPrintWriter.println(line);
            }
            allRecords ++;
        }
        offset --;
    }

    private void closeFiles(BufferedReader br, PrintWriter invalidPrintWriter, PrintWriter validPrintWriter) throws IOException{
        try {
            if (br != null) {
                br.close();
            }
            if (validPrintWriter != null) {
                validPrintWriter.close();
            }
            if (invalidPrintWriter != null) {
                invalidPrintWriter.close();
            }
        } catch (IOException e) {
            throw new IOException("Error while Closing Files");
        }
    }

    private boolean isValidRecord(String[] record) {
        if (record.length != 10) {
            return false;
        }
        for(int i = 0; i < record.length; i++) {
            if (record[i].equals(""))
                return false;
        }
        return true;
    }


    public int getAllRecords() {
        return allRecords;
    }

    public int getSuccessfulRecords() {
        return successfulRecords;
    }

    public int getUnsuccessfulRecords() {
        return unsuccessfulRecords;
    }

}
