import java.io.*;
import java.util.*; 

import com.opencsv.*;
//import com.opencsv.CSVReader;

public class Ledger {

    //private static HashMap<Integer,ArrayList<String>> transactions = new HashMap<Integer,ArrayList<String>>();
    ArrayList<String> checkedBooks = new ArrayList<>();

    public ArrayList<String> getLedger(int libID) throws IOException {
        FileReader file = new FileReader("Ledger.csv");
        BufferedReader reader = new BufferedReader(file);
        try {
            String line = null;
            while ((line = reader.readLine()) != null ){
                String[] data = line.split(",");

                if (data[0].equals(String.valueOf(libID))) {
                    String[] books = data[1].split(" ");
                    BookShelf bookShelf = new BookShelf();
                    for (String b: books) {
                        String bookDetail = bookShelf.findID(b);
                        checkedBooks.add(bookDetail);
                    }
                    //checkedBooks.add(data[1]);
                }
            }
        } catch (Exception e) {
            System.out.println("FILE ERROR: "+e);
        } finally {
            reader.close();
        }
        return checkedBooks;
    }

    public void setLedger(String libID, String bookIDs) throws IOException{
        String csv = "Ledger.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
        try {
            String data = libID+" "+bookIDs;
            String[] line = data.split(" ");
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
