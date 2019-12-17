import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Proj5_Driver {

    public static void main(String[] args) {

        // Input Files
        readFile();



    }

    public static void readFile() {

        // Read File Name
//        System.out.println("The parameter file of : ");
//        Scanner sc = new Scanner(System.in);
//        String fileName = "src" + sc.nextLine();
        String fileName = "src/TestFile.txt";

        // Scan the file
        System.out.println(fileName);
        File myFile = new File (fileName);
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(myFile);
        }
        catch (FileNotFoundException e1) {
            System.out.println("\n[ERROR]: Cannot find the file.");
        }

        while (fileInput.hasNextLine()) {
            // Read the next line in the file
            String buffline = fileInput.nextLine();

            // Split the line into an array
            String[] data_line = buffline.split("[\\W\\_]{2,}|[\\W&&[^'-]]+|\\_{2,}");

            printArray(data_line);

        }



    }

    public static void printArray(String[] a) {
        if (a.length == 0) {
            System.out.println("[ EMPTY ]");
            return;
        }
        System.out.print("[ ");
        for (int i = 0; i < a.length-1; i++) {
            System.out.print("\"" + a[i]+"\", ");
        }
        System.out.println("\""+ a[a.length-1] + "\" ]");
    }


}
