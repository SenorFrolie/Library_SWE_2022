import java.util.Scanner;
import java.util.*;
import java.io.*;

public class TheSystem {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        BookShelf list = new BookShelf();
        BookShelf listV = new BookShelf();
        listV.readVideoMat();
        list.readBookList();
 
        System.out.println("Where would you like to go? ");
        int user_input;

        System.out.println("-------------------------------------------------");
        System.out.println("|            Welcome to our Library              |");
        System.out.println("|               Menu Application                 |");
        System.out.println("-------------------------------------------------");

        do {
            System.out.println("\r\n");
            System.out.println("1. Browse ");
            System.out.println("2. Checkout ");
            System.out.println("3. Return ");
            System.out.println("4. Checkout History ");
            System.out.println("5. Exit. ");
            System.out.println("-----------------");

            user_input = sc.nextInt();

            if (user_input == 1) {
                BookShelf.ShelfMainMenu();
                
            } else if (user_input == 2) {

                Ledger ledger = new Ledger();
                Scanner co_scanner = new Scanner(System.in).useDelimiter(" ");
                String prompt 
                = "Enter your library ID followed by book ID(s)"
                +"\nExample: \n1\n1 5 7";
                System.out.println(prompt);
                if (co_scanner.hasNextLine()) {
                    String libID = co_scanner.nextLine();
                    String bookIDs = co_scanner.nextLine();
                    System.out.println("bookIDs" + bookIDs);
                    ledger.setLedger(libID, bookIDs);
                }
                //co_scanner.close();

                
            } else if (user_input == 3) {

            } else if (user_input == 4) {

                System.out.println("What is your library ID? ");
                Integer libID = sc.nextInt();
                //Ledger.getLedger(libID);

            }

        } while (user_input != 5);
    }

}