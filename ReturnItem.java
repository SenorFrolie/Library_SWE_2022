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
    public static void returnProcess(String ID){
        //Display user items/ due dates/ fees etc (from ledger)
        int user_input;
        int itemReturning;
        
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
                        //renew item from ledger
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
        

    }




//item return function
//which will return item into system (writing back into books or visual csv files :) hopefully )
//calculate fees (ledger)
//shows reciept to user to show its been returned and fees if applicable (ledger)

public static void itemReturn(String ID, int type) throws IOException{

    Scanner sc = new Scanner(System.in);

    String userID = ID;
    int itemType = type;

    if(itemType == 1){
        String idOfBook = availabality();

        String nameOfBook = BookShelf.findBookByID(idOfBook);
        int bookfees;
        //return book to list
        //Write back to book list function
        String bookSrc = "returnedMaterial.csv";


        String requested = "User: " + userID + ",  has returned: " + nameOfBook;
        CSVWriter writer = new CSVWriter(new FileWriter(bookSrc, true), ',',
                                     CSVWriter.NO_QUOTE_CHARACTER,
                                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                     CSVWriter.DEFAULT_LINE_END);
       
        String [] record = requested.split(",");
        writer.writeNext(record);
        writer.close();
        //sc.close();

        //Reciept for user to see
        System.out.println("The book: " + nameOfBook);
        System.out.println("Has been returned. Fees: " + bookfees);
        
        TheSystem.logOut();
        
    }
    else if(itemType == 2){
        String idOfVisual = availabality();

        String nameOfVisual = BookShelf.findVMByID(idOfVisual);
        int visualFees;
        //return book to list
        //Write back to book list function
        String visualSrc = "returnedMaterial.csv";


        String requested = "User: " + userID + ",  has returned: " + nameOfVisual;
        CSVWriter writer = new CSVWriter(new FileWriter(visualSrc, true), ',',
                                     CSVWriter.NO_QUOTE_CHARACTER,
                                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                     CSVWriter.DEFAULT_LINE_END);
       
        String [] record = requested.split(",");
        writer.writeNext(record);
        writer.close();
        //sc.close();



        System.out.println("The item: " + nameOfVisual);
        System.out.println("Has been returned. Fees: " + visualFees);
        
        TheSystem.logOut();
        
    }

}

public static String availabality() throws IOException{

    Scanner sc = new Scanner(System.in);
    
    //Check if available
    String user_input;
    int requestChoice;

    do{
        System.out.println("Please enter item ID");
        
        user_input = sc.nextLine();


        Boolean availabality = Ledger.getCurrentCheckedItemByID(user_input);
        if(availabality == true){
            return user_input;
        }
        else if(availabality == false){
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



}