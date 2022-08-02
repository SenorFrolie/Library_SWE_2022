import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.opencsv.*;
//import com.opencsv.CSVReader;

public class Ledger {

    //private static HashMap<Integer,ArrayList<String>> transactions = new HashMap<Integer,ArrayList<String>>();
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
            return arr.indexOf(ID) > 0;
        }
        return false;
    }

    private String tsToDate(String ts) {
        if (ts.equals("")) {
            return "";
        }
        DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        long milliSeconds = Long.parseLong(ts.trim());
        Date date = new Date(milliSeconds);
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(date);
    }

    private String getReturnDate(String date, int lengthDays) {
        if (date.equals("")) {
            return "";
        }
        DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        long dateCheckedMilliSeconds = Long.parseLong(date.trim());
        int daysMilli = (int)TimeUnit.DAYS.toMillis(lengthDays);
        //long returnDateInMilliSeconds = dateCheckedMilliSeconds + lengthDays;
        //Date returnDate = new Date(dateCheckedMilliSeconds + days);
        System.out.println("lengthDays: "+lengthDays);
        System.out.println("daysMilli: "+daysMilli);
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
                int lengthDays = Integer.parseInt(data[4].trim());
                String fine = data[5];

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
                    bookCheckoutInfo.add(getReturnDate(dateChecked, lengthDays));
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
