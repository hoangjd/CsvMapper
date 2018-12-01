import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {

    private static File file;

    public UserInput(){

    }

    public File askForFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input path of csv file");
        File file = new File(scanner.next());

        while(!file.exists()) {
            System.out.println("Please input path of csv file");
            scanner = new Scanner(System.in);
            this.file = new File(scanner.next());
        }
        return file;
    }

    public int askForOffset() {
        int offset = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input number of header rows to skip");

        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }

        while(!scanner.hasNextInt()) {
            System.out.println("Not an int! Please input number of header rows to skip");
            scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                offset = scanner.nextInt();
            }
        }
        return offset;
    }

    public File getFile() {
        return this.file;
    }

}
