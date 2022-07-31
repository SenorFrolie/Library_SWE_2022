import java.util.Scanner;
import java.util.*;
import java.io.*;

public class TheSystem {

    public static String ID; 
    public static boolean age;
    //Set MemberPages 


// Fetches Member Object form MemberPages 
    public static  Member getMember ( String ID)
    {
        return MemberPages.findMember(ID);
    }
// Displays Member Profile to Screen
    public static void seeProfile()
    {
        Member currentMember= getMember(ID);
        System.out.println("Welcome " + currentMember.getName());
        System.out.println("Library ID# :"+ currentMember.getId());
        System.out.println("Phone Number :"+ currentMember.getPhoneNum());
        System.out.println("You have " + currentMember.getOverdue()+" overdue items");
        System.out.println("You owe $"+currentMember.getFineTotal()+" in fines");
        System.out.println("Address : " + currentMember.getAddress());
    }
// Assigns due date based on item type
    public static int  assignDueDate (String itemType ) {
        int duedate=0;

        switch (itemType){

        case ("book"):

        duedate= 21;
        break;

        case ("BestBookVideo"):
        duedate = 14;
        break;
        }
        return duedate;

    }
// Checks out a book


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        BookShelf list = new BookShelf();
        BookShelf listV = new BookShelf();
        listV.readVideoMat();
        list.readBookList();
        MemberPages.readMemberList();

        //test area -----------------------------------------

        Checkout.startCheckOut();

        //--------------------------------------------
 
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

                
            } else if (user_input == 3) {

            } else if (user_input == 4) {

                System.out.println("What is your library ID? ");
                int libID = sc.nextInt();
                Ledger ledger = new Ledger();
                System.out.println("Checkout history: " + ledger.getLedger(libID));

            }

        } while (user_input != 5);
    }

}