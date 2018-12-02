package com.joe.csvMapper;

import java.io.File;
import java.util.Scanner;

public class UserInput {

    private static File file;

    public UserInput(){
    }

    public File askForFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input path of csv file");
        File file = new File(scanner.next());

        while(!file.exists() || file.isDirectory()) {
            System.out.println("Not a valid file. Please input path of csv file");
            scanner = new Scanner(System.in);
            file = new File(scanner.next());
        }
        this.file = file;
        return file;
    }

    public int askForOffset() {
        int offset = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input number of header rows to skip");

        if (scanner.hasNextInt()) {
            offset = scanner.nextInt();
            if (offset >= 0)
                return offset;
            offset = -1;
        }

        while(offset == -1) {
            System.out.println("Not a valid input. Please input number of header rows to skip");
            offset = checkForValidOffset(scanner);
            scanner = new Scanner(System.in);
            if (offset != -1)
                return offset;
        }
        return offset;
    }

    private int checkForValidOffset(Scanner scanner) {
        int offset = -1;
        if (scanner.hasNextInt()) {
            offset = scanner.nextInt();
            if (offset < 0) {
                offset = -1;
            }
        }
        return offset;
    }

    public File getFile() {
        return file;
    }

}
