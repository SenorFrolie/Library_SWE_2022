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
public static boolean checkforFees(String ID){

    String userFees = MemberPages.findMember(TheSystem.ID).getFineTotal();

    if (userFees.equals("0")){
        return false;
    }
    else{

        System.out.println("Checkout Failed Outstanding Fees");
        return true;
    }


}


    // would you like to checkout book or visual material?
public static  void startCheckOut(){
        
        //check if user has fees 
        //then while(fees == 0) continue checkout
        int user_input;
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
                        System.out.println("Book is not available");
                        //request option from ...
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
                        System.out.println("Visual Material is not available");
                        //request option from ...
                    }
                }
            }
        }while (user_input != 3);
        

    }

//book checkout process function
//which will check book availability and if not available request item
//if available get due date for book with book id from ledger
//assign book to user with ledger
//print reciept to user to show due date of book



//visual material checkout process function
//which will check availability and if not available request item
//if available get due date for visual mat with visual mat id from ledger
//assign visual mat to user with ledger
//print reciept to user to show due date of visual mat



public static void bookCheckOut(String bookId){

    Scanner sc = new Scanner(System.in);
    
    //Check if available
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
        }
        else if(user_input == 2){
            startCheckOut();
        }
        
        else{
            System.out.println("please choose one of the options");
        }
   

} while(user_input != 3);
}

public static void visualCheckOut(String visualID){

    Scanner sc = new Scanner(System.in);
    
    //Check if available
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
        }
        else if(user_input == 2){
            startCheckOut();
        }
       
        else{
            System.out.println("please choose one of the options");
        }
}while(user_input != 3);
}
}