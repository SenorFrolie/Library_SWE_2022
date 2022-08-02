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


public class ReturnItem{
    public void returnProcess(){
        //Display user items/ due dates/ fees etc (from ledger)
        int user_input;
        
        do{
            System.out.println("\nWould you like to return or renew an item?");
            System.out.println("1. Return");
            System.out.println("2. Renew");


            Scanner sc = new Scanner(System.in);

            user_input = sc.nextInt();

            if(user_input == 1){
                System.out.println("Please enter item ID");
                Scanner itemIdInput = new Scanner(System.in);

                String itemidNum = itemIdInput.nextLine();
                //look for item in ledger since it should be with user
                //basically need to do this with ledger to see if user has book
                
                Boolean availabality = Ledger.getCurrentCheckedItemByID(itemidNum);
                if(availabality == true){
                bookReturn(itemidNum);
                }
                else if(availabality == false){
                    System.out.println("Item not found");
                    //request option from ...
                }
            }
            if(user_input == 2){
                //renew item from ledger
                }
            
        
        } while (user_input != 3);
        

    }

}


//item return function
//which will return item into system (writing back into books or visual csv files :) hopefully )
//calculate fees (ledger)
//shows reciept to user to show its been returned and fees if applicable (ledger)

public static void bookReturn(String bookId) throws IOException{

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
            String libID = TheSystem.ID;
            Ledger ledger = new Ledger(libID);
            if (ledger.setLedger(libID, bookId)){
                System.out.println("You have successfully checked out: " + bookId+". \nReturning to main menu.\n");
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