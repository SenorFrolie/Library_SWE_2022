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
                
                /*String availabality = BookShelf.findID(itemidNum);
                if(availabality == "Available"){
                bookCheckOut(itemidNum);
                }
                else if(availabality != "Available"){
                    System.out.println("Book is not available");
                    //request option from ...
                }*/
            }
            if(user_input == 2){
                //renew item from ledger
                }
            
        
        } while (user_input != 3);
        

}}


//item return function
//which will return item into system (writing back into books or visual csv files :) hopefully )
//calculate fees (ledger)
//shows reciept to user to show its been returned and fees if applicable (ledger)
