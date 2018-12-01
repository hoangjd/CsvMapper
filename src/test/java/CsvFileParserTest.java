
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import org.apache.commons.io.*;
import static org.junit.Assert.assertEquals;

public class CsvFileParserTest {

    CsvMapperApp app = new CsvMapperApp();
    CurrentTime currentTime = new CurrentTime();
    CsvFileParser parser;
    File testCsvFile;

    String invalidString = "1a,,3a,4a,5a,6a,7a,8a,9a,10a\n" +
            "1b,2b,3b,4b,5b,6b,7b,8b,9b,,\n" +
            ",,2c,3c,4c,5c,6c,7c,8c,9c,10c\n";

    String validString = "a,b,c,d,e,f,g,h,i,j\n" +
            "1,2,3,4,5,6,7,8,9,\"10,\"\n" +
            "1d,2d,3d,4d,5d,6d,7d,8d,9d,10d\n";


    @Before
    public void before() throws Exception {
        parser = null;
        URL url = getClass().getResource("test.csv");
        URI path = url.toURI();
        testCsvFile = new File(path);
        if (testCsvFile.exists()) {
            parser = new CsvFileParser(testCsvFile, 0);
        }
    }

    @After
    public void after() {
        File badFile = new File(testCsvFile.getParent() + "/bad-data-<" + currentTime.getCurrentTime() + ">.csv");
        File validFile = new File(testCsvFile.getParent() + "/validCsv.csv");

        if (badFile.exists()) {
            badFile.delete();
        }
        if (validFile.exists()) {
            validFile.delete();
        }
    }

    @Test
    public void validCsvFileTestFileCreation() {
        app.separateValidCSVFromInvalidCSV(parser);
        File invalidFile = new File(testCsvFile.getParent() + "/bad-data-<" + currentTime.getCurrentTime() + ">.csv");
        File validFile = new File(testCsvFile.getParent() + "/validCsv.csv");
        assert(invalidFile.exists());
        assert(validFile.exists());

    }

    @Test
    public void validateFileContentsTest() {
        app.separateValidCSVFromInvalidCSV(parser);
        File invalidFile = new File(testCsvFile.getParent() + "/bad-data-<" + currentTime.getCurrentTime() + ">.csv");
        File validFile = new File(testCsvFile.getParent() + "/validCsv.csv");
        try {
            assertEquals("String and file contents do not match", FileUtils.readFileToString(validFile, "utf-8"), validString);
            assertEquals("String and file contents do not match", FileUtils.readFileToString(invalidFile, "utf-8"), invalidString);
        } catch (IOException e) {
            System.out.println("IOException hit in method validateFileContentsTest");
        }
    }

}
