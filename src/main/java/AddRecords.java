import java.io.*;
import java.sql.SQLException;

public class AddRecords {
    UserInput input;
    SqliteDb database;

    public AddRecords() {
        input = new UserInput();
        database = new SqliteDb();
    }
    public File addRecordsToTable() throws IOException, SQLException {
        File csvFile = input.getFile();
        //initialize necessary tools for writing to files
        File validCsv = new File(csvFile.getParent() + "/validCsv.csv");
        BufferedReader br = null;

        //write to files
        try {
            br = new BufferedReader(new FileReader(validCsv.getPath()));
            String line = br.readLine();
            while(line != null) {
                String[] record = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                try {
                    database.addrecord(record);
                } catch (Exception e) {
                    System.out.println("Error occured during adding record");
                }
                line = br.readLine();
            }

        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                throw new IOException("Error while Closing Reader");
            }
        }
        return validCsv;
    }
}
