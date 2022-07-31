import java.io.*;
import java.util.Scanner;
import com.opencsv.CSVWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileWriter;


public class MemberPages {
    // Bellow is the memberBook which stores each memeber Object that is bieng worked on or edited in the system. 
    static ArrayList<Member> memberBook = new ArrayList<Member>();
    // Adds propertys to member objects
    public void addMemeber(Member newMember, String name, String id, String phoneNum, String address) {

        // used to set the Basic Member propertys when creating a new member
        newMember.setID(id);
        newMember.setName(name);
        newMember.setphoneNum(phoneNum);
        newMember.setAddress(address);
    }
    // Dumps the contents of a Member object to screen
    public void dumpMember(Member currentMember) {
// Dumps a Member object information to terminal 
        System.out.println("Member name : " + currentMember.getName());
        System.out.println("Library ID :" + currentMember.getId());
        System.out.println("Phone Number :" + currentMember.getPhoneNum());
        System.out.println("Address: " + currentMember.getAddress());
        System.out.println("Is a Child : " + isChild(currentMember.getId()));
        System.out.println("# of Overdue Items :" + currentMember.getOverdue());
        System.out.println("Ammount of Fines Accrued:$" + currentMember.getFineTotal());

    }
    // edits any property of a member with given property type and the replacement property 
    public void editMemeber (String property,String newProp, String ID){
        // Used to edit a member property via findMember method and ID
        Member current =findMember(ID);

        System.out.println(current.getName()+" Has been sucsessfully edited");

        switch (property){
            case "name":
            current.setName(newProp);
            break;

            case "phone number":
            current.setphoneNum(newProp);
            break;

            case "overdue":
            current.setOverdue(newProp);
            break;

            case "fine total":
            current.setFineTotal(newProp);
            break;

            case "address":
            current.setAddress(newProp);
            break;


        }

    }
    //Finds out if a member is a child or an Adult 
    public static boolean isChild(String ID){

    if ( ID.charAt(0) =='C')
    {
        return true;
    }
    else{
        return false;

    }
    }
    // Finds a Member object index in the array list by property 
    public static int findMemberIndex (String ID){
        int index= 0;
        for(int i = 0 ; i < memberBook.size(); i++){
    
            if ( memberBook.get(i).getId().equals(ID)){ 
                index=i;  
                return index;         


            }
        

        }
    return index;
    }
    // Works with findMemebrIndex to return a member object from the Array List 
    public static Member findMember(String ID) {
        // Used to locate a Member object via ID parameter 
               int index = findMemberIndex(ID);
        
                return memberBook.get(index);
            }
    // Reads member information into Member objects which are stored in the Array list        
    public void readMemberList() throws IOException {
                FileReader file = new FileReader("MemberList.csv");
                BufferedReader reader = new BufferedReader(file);
                try {
                    reader.readLine();
                    String line = null;
                    while ((line = reader.readLine()) != null ){
                        String[] elements= line.split(",");
                        if (elements.length  < 6){
                            throw new RuntimeException ("Line too short");//handle missing entrys 
                        }
                        String Name = elements[0];
                        String ID = elements[2];
                        String phoneNum = elements[3];
                        String address = elements[1];
                        String overdue= elements[4];
                        String fineTot= elements [5];
                        Member temp = new Member( Name , ID , phoneNum,address,overdue,fineTot);
    
                        memberBook.add((Member)temp);
                    }
                } catch (Exception e) {
                    System.out.println("FILE ERROR: "+e);
                } finally {
                    reader.close();
                }
                    //TODO: handle exception
                }

}