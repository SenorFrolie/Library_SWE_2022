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
    // would you like to checkout book or visual material?
    public void startCheckOut(){
        
        //check if user has fees (ledger)
        //then while(fees == 0) continue checkout
        

        do{
            System.out.println("\nWould you like to checkout a book or visual material?");
            System.out.println("1. Book");
            System.out.println("2. Visual Material");

            Scanner sc = new Scanner(System.in);
            int user_input;

            user_input = sc.nextInt();

            if(user_input == 1){
                System.out.println("Please enter book ID");
                Scanner bookIdInput = new Scanner(System.in);

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
            if(user_input == 2){
                System.out.println("Please enter visual ID");
                Scanner visualIdInput = new Scanner(System.in);

                String bookidNum = visualIdInput.nextLine();
                String availabality = BookShelf.findID(bookidNum);
                if(availabality == "Available"){
                visualCheckOut(bookidNum);
                }
                else if(availabality != "Available"){
                    System.out.println("Visual Material is not available");
                    //request option from ...
                }
            }
        }
        

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



public void bookCheckOut(String bookId){

    Scanner sc = new Scanner(System.in);
    
    //Check if available

    do{
        System.out.println("\nWould you like to checkout: " + bookId);
        System.out.println("1. Checkout");
        System.out.println("2. Look for different option (back)");
        System.out.println("3. Log Out");

        int user_input = sc.nextInt();
        
        if(user_input == 1){
            //assign book to user with ledger
            //print to confirm
            //ask next action
        }
        else if(user_input == 2){
            startCheckOut();
        }
        else if(user_input == 3){
            String[] args;
            TheSystem.main(args);
        }
        else{
            System.out.println("please choose one of the options");
        }
    while(user_input != 0){

    }

}
}

public void visualCheckOut(String visualID){

    Scanner sc = new Scanner(System.in);
    
    //Check if available

    do{
        System.out.println("\nWould you like to checkout: " + visualID);
        System.out.println("1. Checkout");
        System.out.println("2. Look for different option (back)");
        System.out.println("3. Log Out");

        int user_input = sc.nextInt();
        
        if(user_input == 1){
            //assign visual to user with ledger
            //print to confirm
            //ask next action
        }
        else if(user_input == 2){
            startCheckOut();
        }
        else if(user_input == 3){
            String[] args;
            TheSystem.main(args);
        }
        else{
            System.out.println("please choose one of the options");
        }
    while(user_input != 0){

    }
}
}
}