import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.lang.model.util.ElementScanner14;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

public class Ledger {

    private static ArrayList<ArrayList<String>> checkedItems = new ArrayList<ArrayList<String>>();
    private static ArrayList<ArrayList<String>> currentCheckedItems = new ArrayList<ArrayList<String>>();
    private static String libID;
    private static int DATE_CHECKOUT_COLUMN = 2;
    private static int DATE_RETURNED_COLUMN = 3;
    private static int LENGTH_DAYS_COLUMN = 4;
    private static int FINES_COLUMN = 6;

    public Ledger(String libID) {
        Ledger.libID = libID;
    }

    public ArrayList<ArrayList<String>> getCheckedItems() throws IOException {
        return checkedItems;
    }
    public ArrayList<ArrayList<String>> getCurrentCheckedItems() throws IOException {
        return currentCheckedItems;
    }
    public static boolean getCurrentCheckedItemByID(String ID) throws IOException {
        for(ArrayList<String> arr : currentCheckedItems) {
            return arr.indexOf(ID) != -1;
        }
        return false;
    }

    public String getCurrentFinesForUser(String ID) throws IOException {
        for(ArrayList<String> arr : checkedItems) {
            return arr.get(4);
        }
        return null;
    }

    public boolean returnItem(String libID, String itemID) throws IOException, CsvException {
        int row = getItemCheckoutRow(libID, itemID, false);
        CSVReader reader = new CSVReader(new FileReader("Ledger.csv"));
        List<String[]> csvBody = reader.readAll();        
        
        int dateReturnedTimeStamp = Integer.parseInt(csvBody.get(row)[DATE_RETURNED_COLUMN]);
        int dateCheckoutTimeStamp = Integer.parseInt(csvBody.get(row)[DATE_CHECKOUT_COLUMN]);
        csvBody.get(row)[DATE_RETURNED_COLUMN] = ""+System.currentTimeMillis()/1000;

        int diffDays = (dateReturnedTimeStamp - dateCheckoutTimeStamp) / 86400;

        int lengthDays = Integer.parseInt(csvBody.get(row)[LENGTH_DAYS_COLUMN]);
        int daysLate = diffDays - lengthDays;

        // if days late, record fine
        if (daysLate > 0) {
            csvBody.get(row)[FINES_COLUMN] = String.format("%.2f",daysLate * .10);
        }

        reader.close();

        CSVWriter writer = new CSVWriter(new FileWriter("Ledger.csv"),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

        writer.writeAll(csvBody);
        writer.flush();
        writer.close();

        return true;
    }

    private static int getItemCheckoutRow(String libID, String itemID, Boolean entries) throws IOException, CsvException {
        int row = 1;
        int entriesFound = 0;
        FileReader file = new FileReader("Ledger.csv");
        BufferedReader reader = new BufferedReader(file);
        try {
            String line = null;
            reader.readLine();//read to ignore header
            while ((line = reader.readLine()) != null ){
                String[] data = line.split(",");

                String fileLibID = data[0];
                String fileItemID = data[1];  

                if (fileLibID.equals(libID) && fileItemID.equals(itemID)) {
                    if (entries) {
                        entriesFound++;
                    } else {
                        return row;
                    }
                } else {
                    row++;
                }
            }
            return entriesFound;
        } catch (Exception e) {
            System.out.println("FILE ERROR: "+e);
        } finally {
            reader.close();
        }
        return 0;
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
            return "0";
        }
        Calendar calendar = Calendar.getInstance();
        long dateCheckedSeconds = Long.parseLong(date.trim());
        int daysMilli = (int)TimeUnit.DAYS.toMillis(Long.parseLong(lengthDays));
        calendar.setTimeInMillis(dateCheckedSeconds);
        calendar.add(Calendar.MILLISECOND, daysMilli);
        return ""+calendar.getTimeInMillis();
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
                    if (dateReturned.equals("0")) {
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

    public Boolean setLedger(String libID, String itemID) throws IOException{
        if (getCurrentCheckedItems().size() >= 5) {
            System.out.println("You have reached the limit. Please return a book/visual to checkout another item.\n");
            return false;
        }
        String csv = "Ledger.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
        try {
            String checkoutTimestamp = ""+System.currentTimeMillis();
            BookShelf shelf = new BookShelf();
            String lengthDays = shelf.isBestSeller(itemID) ? "14" : "21";
            if (getItemCheckoutRow(libID, itemID, true) < 2) {
                writer.writeNext(new String[]{libID, itemID, checkoutTimestamp,"0",lengthDays,getReturnDate(checkoutTimestamp,lengthDays),"0"});
                System.out.println("You have successfully checked out: " + itemID + "  " + BookShelf.findBookByID(itemID));
                System.out.println("This item is due by " + tsToDate(getReturnDate(checkoutTimestamp,lengthDays)));
                return true;
            } else {
                System.out.println("You have checked out this item twice. You must return it by:\n" + tsToDate(getReturnDate(checkoutTimestamp,lengthDays)));
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to checkout book: "+itemID);
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
