import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class MemberPages {
    // Bellow is the memberBook which stores each memeber Object that is bieng worked on or edited in the system. 
    ArrayList<Member> memberBook = new ArrayList<Member>();
    // Adds propertys to member objects
    public void addMemeber(Member newMember, String name, String id, String phoneNum) {

        // used to set the Basic Member propertys when creating a new member
        newMember.setID(id);
        newMember.setName(name);
        newMember.setphoneNum(phoneNum);
    }
    // Dumps the contents of a Member object to screen
    public void dumpMember(Member currentMember) {
// Dumps a Member object information to terminal 
        System.out.println(currentMember.getName());
        System.out.println(currentMember.getId());
        System.out.println(currentMember.getPhoneNum());
        System.out.println(currentMember.getOverdue());
        System.out.println(currentMember.getFineTotal());

    }
    // edits any property of a member with given property type and the replacement property 
    public void editMemeber (String property,String newProp, String ID){
        // Used to edit a member property via findMember method and ID
        Member current =findMember(memberBook, ID);

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


        }

    }
    // Finds a Member object index in the array list by property 
    public int findMemberIndex (ArrayList<Member> list , String ID){
        int index= 0;
        for(int i = 0 ; i < list.size(); i++){
    
            if ( list.get(i).getId().equals(ID)){ 
                index=i;  
                System.out.println("found");
                return index;         


            }
        

        }
    return index;
    }
    // Works with findMemebrIndex to return a member object from the Array List 
    public Member findMember(ArrayList<Member> memeberList, String ID) {
        // Used to locate a Member object via ID parameter 
               int index = findMemberIndex(memeberList, ID);
        
                return memberBook.get(index);
            }
    // Reads member information into Member objects which are stored in the Array list        
    public void readMemberList(){
                try {
                    FileReader file = new FileReader("MemberList.csv");
                    BufferedReader reader = new BufferedReader(file);
                    String info = reader.readLine();
                    while (info != null ){
                        String[] elements= info.split(",");
                        if (elements.length  < 5){
                            throw new RuntimeException ("Line too short");//handle missing entrys 
                        }
                        String Name = elements[0];
                        String ID = elements[2];
                        String phoneNum = elements[1];
                        String overdue= elements[3];
                        String fineTot= elements [4];
                        Member temp = new Member( Name , ID , phoneNum, overdue,fineTot);
    
                        memberBook.add((Member)temp);
            
                        info = reader.readLine(); 
                    }
                    file.close();
                } catch (Exception e) {
                    System.out.println("FILE ERROR: "+e);}
                    //TODO: handle exception
                }
    //Writes to the CSV File the contents of a member for storage       
    public void writeToMemberList(){
        

    }
    public static void main(String[] args) {

        MemberPages yellow = new MemberPages();
        yellow.readMemberList();
        //yellow.dumpMember(yellow.findMember(yellow.memberBook, "999SD55"));
        //System.out.println(yellow.memberBook.get(yellow.findMemberIndex(yellow.memberBook, "999SD55")).getOverdue());
        //yellow.editMemeber("fine total", "$0", "999SD55");
        //System.out.println(yellow.memberBook.get(yellow.findMemberIndex(yellow.memberBook, "999SD55")).getFineTotal());


    }
}