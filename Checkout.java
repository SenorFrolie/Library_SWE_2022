import java.io.*;
//import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import com.opencsv.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;


public class Checkout{
//verify user fees
public static boolean checkforFees(String ID) throws IOException{

    String libID = TheSystem.ID;
    Ledger ledger = new Ledger(libID);

    String userFees = MemberPages.findMember(TheSystem.ID).getFineTotal();
    String userOverDueFees = ledger.getCurrentFinesForUser(TheSystem.ID);

    if (userFees.equals("0") || userOverDueFees.equals("0")){
        return false;
    }
    else{

        System.out.println("Checkout Failed Outstanding Fees");
        return true;
    }

}


public static void startCheckOut() throws IOException, CsvException{
        
        int user_input;
        int requestChoice;
        checkforFees(TheSystem.ID);
      

        do{ 
            System.out.println("\nWould you like to checkout a book or visual material?");
            System.out.println("1. Book");
            System.out.println("2. Visual Material");

            Scanner sc = new Scanner(System.in);
           

            user_input = sc.nextInt();

            if(user_input == 1){
                System.out.println("Please enter book ID");
                try (Scanner bookIdInput = new Scanner(System.in)) {
                    String bookidNum = bookIdInput.nextLine();
                    String availabality = BookShelf.findID(bookidNum);
                    if(availabality == "Available"){
                    bookCheckOut(bookidNum);
                    }
                    else if(availabality != "Available"){
                        
                        do{
                            System.out.println("Book is not available");
                            System.out.println("1. Request Item");
                            System.out.println("2. Search for another item");

                            Scanner ac = new Scanner(System.in);
                
                            requestChoice = ac.nextInt();
                
                            if(requestChoice == 1){
                                BookShelf.requestNewBook();
                            }
                            else if (requestChoice == 2){
                                startCheckOut();
                            }
                        }while (requestChoice != 3);
                    }
                }
            }
            if(user_input == 2){
                System.out.println("Please enter visual ID");
                try (Scanner visualIdInput = new Scanner(System.in)) {
                    String visualidNum = visualIdInput.nextLine();
                    String availabality = BookShelf.findVMID(visualidNum);
                    if(availabality == "Available"){
                    visualCheckOut(visualidNum);
                    }
                    else if(availabality != "Available"){
                        do{
                            System.out.println("Book is not available");
                            System.out.println("1. Request Item");
                            System.out.println("2. Search for another item");
                
                            Scanner ac = new Scanner(System.in);
                
                            requestChoice = ac.nextInt();
                
                            if(requestChoice == 1){
                                BookShelf.requestNewBook();
                            }
                            else if (requestChoice == 2){
                                startCheckOut();
                            }
                        }while (requestChoice != 3);
                    }
                }
            }
        }while (user_input != 3);
    
    }



public static void bookCheckOut(String bookId) throws IOException, CsvException{

    Scanner sc = new Scanner(System.in);

    int user_input;
    do{
        System.out.println("\nWould you like to checkout: " + bookId);
        System.out.println("1. Checkout");
        System.out.println("2. Look for different option (back)");
        System.out.println("3. Log Out");
        user_input = sc.nextInt();

        
        if(user_input == 1){
            //assign book to user with ledger
            //print to confirm
            //ask next action
            String libID = TheSystem.ID;
            Ledger ledger = new Ledger(libID);
            if (ledger.setLedger(libID, bookId, 0)){
                System.out.println("\nReturning to main menu.\n");
                TheSystem.mainMenu();
            }
        }
        else if(user_input == 2){
            startCheckOut();
        }
        
        else if(user_input != 3 && user_input != 2 && user_input != 1){
            System.out.println("please choose one of the options");
        }
   

    } while(user_input != 3);
    TheSystem.logOut();
}

public static void visualCheckOut(String visualID) throws IOException, CsvException{

    Scanner sc = new Scanner(System.in);
    
    int user_input;
    do{
        System.out.println("\nWould you like to checkout: " + visualID);
        System.out.println("1. Checkout");
        System.out.println("2. Look for different option (back)");
        System.out.println("3. Log Out");

        user_input = sc.nextInt();
        
        if(user_input == 1){
            //assign visual to user with ledger
            //print to confirm
            //ask next action
            String libID = TheSystem.ID;
            Ledger ledger = new Ledger(libID);
            if (ledger.setLedger(libID, visualID, 1)){

                System.out.println("\nReturning to main menu.\n");
                TheSystem.mainMenu();
            }
        }
        else if(user_input == 2){
            startCheckOut();
        }
        
        else if(user_input != 3 && user_input != 2 && user_input != 1){
            System.out.println("please choose one of the options");
        }
   

    } while(user_input != 3);
    TheSystem.logOut();
    }




}