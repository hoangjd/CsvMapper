
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CsvFileParser {

    private File csvFile;
    private int successfulRecords = 0;
    private int unsuccessfulRecords = 0;
    private int allRecords = 0;
    private int offset;
    private CurrentTime currentTime = new CurrentTime();

    public CsvFileParser(File file, int offset) {
        this.csvFile = file;
        this.offset = offset;
    }

    //Lots going on here trying to reduce this to smaller methods will result in large method signatures which isn't much better
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
                if (offset <= 0) {
                    String[] record = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    if (!isEmptyString(record)) {
                        successfulRecords++;
                        validPrintWriter.println(line);
                    } else if (isEmptyString(record)) {
                        unsuccessfulRecords++;
                        invalidPrintWriter.println(line);
                    }
                    allRecords ++;
                }
                offset --;
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
        return validCsv;
    }

    private boolean isEmptyString(String[] record) {
        for(int i = 0; i < record.length; i++) {
            if (record[i].equals(""))
                return true;
        }
        return false;
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
