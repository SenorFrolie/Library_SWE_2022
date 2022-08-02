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

    // log in
    public static Boolean logIn(String libID) throws IOException {
        Member member = MemberPages.findMember(libID);

        if (member.getId().equals(libID)) {
            ID = libID;
            return true;
        } else {
            return false;
        }
    }

    // log out
    public static void logOut() throws IOException{
        ID = null;
        System.out.print("You have successfully logged out... \nReturning to main menu.");
        mainMenu();
    }

    private static void silverPlatterPrinter(ArrayList<ArrayList<String>> aRay, String label) {
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String CYAN = "\u001B[36m";
        System.out.println("===================================");
        System.out.println("\t"+CYAN+label+RESET);
        System.out.println("===================================");
        for(ArrayList<String> arr : aRay) {
            String item = arr.get(0);
            String checkoutDate = arr.get(1);
            String dateReturned = (!arr.get(2).equals("")) ? arr.get(2) : "Currently checked out";
            String fine = arr.get(4);
            System.out.println("Item: "+item);
            System.out.println(GREEN+"Checkout Date: "+RESET+checkoutDate);
            System.out.println(RED+"Due Date: "+RESET+arr.get(3));
            System.out.println("Date Returned: "+dateReturned);
            System.out.println("Fine: $"+fine);
            System.out.println("\n---------\n");
        }
    }

    public static void mainMenu() throws IOException{
        Scanner sc = new Scanner(System.in);
        BookShelf list = new BookShelf();
        //BookShelf listV = new BookShelf();
        //listV.readVideoMat();
        list.readBookList();
        MemberPages.readMemberList();

        //test area -----------------------------------------

        //Checkout.startCheckOut();

        //--------------------------------------------
 
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

                //Ledger ledger = new Ledger();
                Scanner co_scanner = new Scanner(System.in).useDelimiter(" ");
                System.out.println("Please provide your library ID to log in or press 2 to return to the main menu: ");
                String ID_input = sc.next();
                do {
                    if (logIn(ID_input)) {
                        Checkout.startCheckOut();
                    } else {
                        System.out.println("\nInvalid library ID. Returning to main menu.");
                        break;
                    }
                } while (!ID_input.equals("2"));

                
            } else if (user_input == 3) {

                //Ledger ledger = new Ledger();
                Scanner co_scanner = new Scanner(System.in).useDelimiter(" ");
                System.out.println("Please provide your library ID to log in or press 2 to return to the main menu: ");
                String ID_input = sc.next();
                do {
                    if (logIn(ID_input)) {
                        ReturnItem.returnProcess(ID_input);
                    } else {
                        System.out.println("\nInvalid library ID. Returning to main menu.");
                        break;
                    }
                } while (!ID_input.equals("2"));

            } else if (user_input == 4) {

                System.out.println("What is your library ID? ");
                String libID = sc.next();
                Ledger ledger = new Ledger(libID);
                ledger.getLedger();
                silverPlatterPrinter(ledger.getCheckedItems(), "Checkout History");
                silverPlatterPrinter(ledger.getCurrentCheckedItems(), "Currently Checked out");

            }

        } while (user_input != 5);

    }

    public static void main(String[] args) throws IOException {

        mainMenu();
    }

}