import java.io.*;
import java.util.Scanner;

public class BookShelf {

    // for reading a CSV file that contains the information on the books
    static void book_Avaiable() throws IOException {
        String book_path = "Books.csv";
        BufferedReader reader = null;
        String line = "null";

        Scanner sc = new Scanner(System.in);
        int user_input;

        System.out.println("--------------------------------------");
        System.out.println("| Welcome to our hottest Selection! | ");
        System.out.println("--------------------------------------");

        System.out.println("would you rather check out Book or Visual Audio");

        do {

            System.out.println("\r\n");
            System.out.println("1. Books ");
            System.out.println("2. Visual ");
            System.out.println("0. Exit ");

            user_input = sc.nextInt();

            if (user_input == 1) {
                System.out.println("--------------------------------------");
                System.out.println("|    You're in the book section        |");
                System.out.println("--------------------------------------");
                 
                try {
                    reader = new BufferedReader(new FileReader(book_path));
                    while ((line = reader.readLine()) != null) {

                        String[] Book = line.split(",");

                        for (String index : Book) {
                            System.out.printf("%-20s", index);

                        }
                        System.out.println();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    reader.close();
                }

            } else if (user_input == 2) {
                System.out.println("--------------------------------------");
                System.out.println("|    You're in Visual Audio section    | ");
                System.out.println("--------------------------------------");

            }

        } while (user_input != 0);
        reader.close();
    }

}
