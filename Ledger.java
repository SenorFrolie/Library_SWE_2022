import java.io.IOException;
import java.util.*; 
import java.io.FileWriter;

import com.opencsv.CSVWriter;

public class Ledger {

    //private static HashMap<Integer,ArrayList<String>> transactions = new HashMap<Integer,ArrayList<String>>();
    ArrayList<String> userInfo = new ArrayList<>();

    /*public static ArrayList<String> getLedger(Integer libID) {
        return transactions.get(libID);
    }*/

    public void setLedger(String libID, String bookIDs) throws IOException{
        String csv = "Ledger.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        try {
            writer.writeNext(new String[]{libID, bookIDs});
            System.out.println("You have successfully checked out: " + bookIDs);
        } catch (Exception e) {
            System.out.println("Failed to checkout books: "+bookIDs);
        } finally {
            writer.close();
        }
        //ArrayList<String> userInfo = new ArrayList<>();
        //transactions.put(libID, userInfo);
    }
    public static void main(String[] args) throws IOException {
        //Ledger ledger = new Ledger();
        //ledger.setLedger(1,1);
        //System.out.println("Where would you like to go? ");
    }
}
