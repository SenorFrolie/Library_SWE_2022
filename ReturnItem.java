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



    public static void returnProcess(String ID) throws IOException, CsvException{
        //Display user items/ due dates/ fees etc (from ledger)
        int user_input;
        int itemReturning;
        Ledger ledger = new Ledger(TheSystem.ID);
        ledger.getLedger();
        
        do{
            System.out.println("\nWould you like to return a book or visual material?");
            System.out.println("1. Book");
            System.out.println("2. Visual Material");
            System.out.println("3. Logout");


            Scanner sc = new Scanner(System.in);

            user_input = sc.nextInt();

            if(user_input == 1){
                do{
                    System.out.println("\nWould you like to return or renew item?");
                    System.out.println("1. Return");
                    System.out.println("2. Renew");
        
        
                    Scanner ac = new Scanner(System.in);
        
                    itemReturning = ac.nextInt();
        
                    if(itemReturning == 1){
                        itemReturn(ID, user_input);
                    }
                    else if(itemReturning == 2){
                        renewItem(ID, user_input);
                    }
                }while(itemReturning != 3);
            }
            else if (user_input == 2){
                do{
                    System.out.println("\nWould you like to return or renew item?");
                    System.out.println("1. Return");
                    System.out.println("2. Renew");
        
        
                    Scanner ac = new Scanner(System.in);
        
                    itemReturning = ac.nextInt();
        
                    if(itemReturning == 1){
                       itemReturn(ID, user_input);
                    }
                    else if(itemReturning == 2){
                        //renew item from ledger
                    }
                }while(itemReturning != 3);
            }
        
        } while (user_input != 3);
        TheSystem.logOut();

    }




//item return function
//which will return item into system (writing back into books or visual csv files :) hopefully )
//calculate fees (ledger)
//shows reciept to user to show its been returned and fees if applicable (ledger)

public static void itemReturn(String ID, int type) throws IOException, CsvException{

    Ledger ledger = new Ledger(TheSystem.ID);
    ledger.getLedger();

    Scanner sc = new Scanner(System.in);
    String user_input;
    
    System.out.println("Please enter item ID");
    user_input = sc.nextLine();
    String nameOfItem = BookShelf.findBookByID(user_input);

    System.out.println("The item: " + nameOfItem);
    System.out.println("Has been returned. Fees: " + ledger.getCurrentFinesForUser(TheSystem.ID));
        
    TheSystem.logOut();
        
    }

    public static void renewItem(String ID, int type) throws IOException, CsvException{

        Ledger ledger = new Ledger(TheSystem.ID);
        ledger.getLedger();
    
        Scanner sc = new Scanner(System.in);
        String user_input;
        
        System.out.println("Please enter item ID");
        user_input = sc.nextLine();
        ledger.setLedger(TheSystem.ID, user_input);
            
        TheSystem.logOut();
            
        }
/* 
public static String availabality() throws IOException, CsvException{

    Scanner sc = new Scanner(System.in);
    
    Ledger ledger = new Ledger(TheSystem.ID);
    ledger.getLedger();
    
    //Check if available
    String user_input;
    int requestChoice;

    do{
        System.out.println("Please enter item ID");
        
        user_input = sc.nextLine();

        System.out.println(user_input);
        
        System.out.println(ledger.getCurrentCheckedItems());

        
        Boolean availabality = Ledger.getCurrentCheckedItemByID(user_input);
        if(availabality == true){
            return user_input;
        }
        else if(availabality.equals(false)){
            do{
                System.out.println("Item not found");
                System.out.println("1. Search for another item");
                System.out.println("2. Logout");
    
    
                Scanner ac = new Scanner(System.in);
    
                requestChoice = ac.nextInt();
    
                if(requestChoice == 1){
                    availabality();
                }
                else if (requestChoice == 2){
                    TheSystem.logOut();
                }
            }while (requestChoice != 3);
            
            
        }        
    }while(user_input != null);
    return null;
}
*/

}