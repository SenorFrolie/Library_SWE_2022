import java.io.IOException;
import java.util.*; 

public class Ledger {

    private static HashMap<Integer,ArrayList<String>> transactions = new HashMap<Integer,ArrayList<String>>();

    public static ArrayList<String> getLedger(Integer libID) {
        return transactions.get(libID);
    }

    public static void setLedger(String name, String address, String phoneNum, Integer libID, String tickets) {
        ArrayList<String> userInfo = new ArrayList<>();
        userInfo.add(name);
        userInfo.add(address);
        userInfo.add(phoneNum);
        userInfo.add(tickets);
        transactions.put(libID, userInfo);
    }
    //public static void main(String[] args) throws IOException {
    //    System.out.println("Where would you like to go? ");
    //}
}
