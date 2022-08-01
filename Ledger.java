import java.io.*;
import java.util.*; 

import com.opencsv.*;
//import com.opencsv.CSVReader;

public class Ledger {

    //private static HashMap<Integer,ArrayList<String>> transactions = new HashMap<Integer,ArrayList<String>>();
    ArrayList<String> checkedBooks = new ArrayList<>();
    int currentChecked;

    public ArrayList<String> getLedger(String libID) throws IOException {
        FileReader file = new FileReader("Ledger.csv");
        BufferedReader reader = new BufferedReader(file);
        currentChecked = 0; // reset per library ID
        try {
            String line = null;
            while ((line = reader.readLine()) != null ){
                String[] data = line.split(",");

                String bookID = data[1];
                String dateChecked = data[2];
                String dateReturned = data[3];
                String lengthDats = data[4];
                String fine = data[5];

                if (data[0].equals(String.valueOf(libID))) {
                    if (dateReturned.equals("0")) {
                        currentChecked++;
                    }
                    checkedBooks.add(bookID);
                }
            }
        } catch (Exception e) {
            System.out.println("FILE ERROR: "+e);
        } finally {
            reader.close();
        }
        return checkedBooks;
    }

    public int getCheckedOutBooks(String libID) throws IOException {
        getLedger(libID);
        return currentChecked;
    }

    public Boolean setLedger(String libID, String bookID) throws IOException{
        if (getCheckedOutBooks(libID) >= 5) {
            System.out.println("You have reached the limit. Please return a book/visual to checkout another item.\n");
            return false;
        }
        String csv = "Ledger.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
        try {
            String checkoutTimestamp = ""+System.currentTimeMillis();
            BookShelf shelf = new BookShelf();
            String lengthDays = shelf.isBestSeller(bookID) ? "14" : "21";
            writer.writeNext(new String[]{libID, bookID, checkoutTimestamp,"0",lengthDays,"0"});
            System.out.println("You have successfully checked out: " + bookID);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to checkout books: "+bookID);
        } finally {
            writer.close();
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        //Ledger ledger = new Ledger();
        //ledger.setLedger(1,1);
        //System.out.println("Where would you like to go? ");
    }
}
