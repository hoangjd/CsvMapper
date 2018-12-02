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

        while(!file.exists() && !file.isDirectory()) {
            System.out.println("Please input path of csv file");
            scanner = new Scanner(System.in);
            file = new File(scanner.next());
        }
        this.file = file;
        return file;
    }

    public int askForOffset() {
        int offset = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input number of header rows to skip");

        if (scanner.hasNextInt()) {
            offset = scanner.nextInt();
            if (offset >= 0)
                return offset;
        }

        while(!scanner.hasNextInt() && scanner.nextInt() >= 0) {
            System.out.println("Not an int! Please input number of header rows to skip");
            scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                offset = scanner.nextInt();
                if (offset >= 0)
                    return offset;
            }
        }
        return offset;
    }

    public File getFile() {
        return file;
    }

}
