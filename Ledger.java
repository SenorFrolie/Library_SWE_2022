import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.opencsv.*;

public class Ledger {

    private static ArrayList<ArrayList<String>> checkedItems = new ArrayList<ArrayList<String>>();
    private static ArrayList<ArrayList<String>> currentCheckedItems = new ArrayList<ArrayList<String>>();
    private static String libID;

    public Ledger(String libID) {
        Ledger.libID = libID;
    }

    public ArrayList<ArrayList<String>> getCheckedItems() throws IOException {
        return checkedItems;
    }
    public ArrayList<ArrayList<String>> getCurrentCheckedItems() throws IOException {
        return currentCheckedItems;
    }
    public boolean getCurrentCheckedItemByID(String ID) throws IOException {
        for(ArrayList<String> arr : currentCheckedItems) {
            return arr.indexOf(ID) != -1;
        }
        return false;
    }

    private String tsToDate(String ts) {
        if (ts.equals("")) {
            return "";
        }
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        long milliSeconds = Long.parseLong(ts.trim());
        Date date = new Date(milliSeconds*1000);
        return formatter.format(date);
    }

    private String getReturnDate(String date, String lengthDays) {
        if (date.equals("")) {
            return "";
        }
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        long dateCheckedMilliSeconds = Long.parseLong(date.trim()+"000");
        int daysMilli = (int)TimeUnit.DAYS.toMillis(Long.parseLong(lengthDays));
        calendar.setTimeInMillis(dateCheckedMilliSeconds);
        calendar.add(Calendar.MILLISECOND, daysMilli);
        return formatter.format(calendar.getTimeInMillis());
    }

    public void getLedger() throws IOException {
        FileReader file = new FileReader("Ledger.csv");
        BufferedReader reader = new BufferedReader(file);
        try {
            String line = null;
            reader.readLine();//read to ignore header
            while ((line = reader.readLine()) != null ){
                String[] data = line.split(",");

                String itemID = data[1];
                String dateChecked = data[2];                
                String dateReturned = data[3];
                String dueDate = data[5];
                String fine = data[6];

                if (data[0].equals(String.valueOf(libID))) {
                    BookShelf shelf = new BookShelf();
                    String itemName = shelf.findBookByID(itemID);
                    if (itemName.equals("")) {
                        itemName = shelf.findVMByID(itemID);
                    }
                    ArrayList<String> bookCheckoutInfo = new ArrayList<>();
                    bookCheckoutInfo.add(itemName);
                    bookCheckoutInfo.add(tsToDate(dateChecked));
                    bookCheckoutInfo.add(tsToDate(dateReturned));
                    bookCheckoutInfo.add(tsToDate(dueDate));
                    bookCheckoutInfo.add(fine);
                    //check for currently checked out items
                    if (dateReturned.equals("")) {
                        currentCheckedItems.add(bookCheckoutInfo);
                    }
                    checkedItems.add(bookCheckoutInfo);
                }
            }
        } catch (Exception e) {
            System.out.println("FILE ERROR: "+e);
        } finally {
            reader.close();
        }
    }

    public Boolean setLedger(String libID, String bookID) throws IOException{
        if (getCurrentCheckedItems().size() >= 5) {
            System.out.println("You have reached the limit. Please return a book/visual to checkout another item.\n");
            return false;
        }
        String csv = "Ledger.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
        try {
            String checkoutTimestamp = ""+System.currentTimeMillis();
            BookShelf shelf = new BookShelf();
            String lengthDays = shelf.isBestSeller(bookID) ? "14" : "21";
            writer.writeNext(new String[]{libID, bookID, checkoutTimestamp,"0",lengthDays,getReturnDate(checkoutTimestamp,lengthDays),"0"});
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
