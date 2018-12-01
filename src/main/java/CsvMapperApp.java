import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CsvMapperApp {

    public static void main(String[] args) {

        CsvFileParser parser = new CsvFileParser(askForFilePath(), 1);
        separateValidCSVFromInvalidCSV(parser);
        System.out.println(parser.getAllRecords());
        System.out.println(parser.getSuccessfulRecords());
        System.out.println(parser.getUnsuccessfulRecords());


    }

    private static File askForFilePath() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Please input path of csv file");
//        File file = new File(scanner.next());
//        while(!file.exists()) {
//            System.out.println("Please input path of csv file");
//            scanner = new Scanner(System.in);
//            file = new File(scanner.next());
//        }
        File file = new File("/Users/Joe/source/ms3Interview.csv");
        return file;
    }

    protected static void separateValidCSVFromInvalidCSV(CsvFileParser parser) {
        try {
            parser.validCsvFile();
        } catch(IOException e){
            e.getMessage();
        }
    }


}
