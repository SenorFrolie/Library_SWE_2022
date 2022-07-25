import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class MemberPages {
// Bellow is the memberBook which stores each memeber Object that is bieng worked on or edited in the system. 
    ArrayList<Member> memeberBook = new ArrayList<Member>();

    public void addMemeber(Member newMember, String name, String id, String phoneNum) {

        // used to set the Basic Member propertys when creating a new member
        newMember.setID(id);
        newMember.setName(name);
        newMember.setphoneNum(phoneNum);
    }

    public void dumpMember(Member currentMember) {
// Dumps a Member object information to terminal 
        System.out.println(currentMember.getName());
        System.out.println(currentMember.getId());
        System.out.println(currentMember.getPhoneNum());
        System.out.println(currentMember.getOverdue());
        System.out.println(currentMember.getFineTotal());

    }

    public void editMemeber (String property, String ID){
        // Used to edit a member property via findMember method and ID
        Member current =findMember(memeberBook, ID);

        current.setName(property);
        System.out.println(current.getName()+" Has been sucsessfully edited");

    }
   
    public Member findMember(ArrayList<Member> memeberList, String ID) {
// Used to locate a Member object via ID parameter 
        Member foundMember = new Member();
        for (Member member : memeberList) {
            if (member.getId().equals(ID)) {

                return member;

            }

        }

        return foundMember;
    }

   public void readMemberList(){
    BufferedReader reader = null;
    try {
        FileReader file = new FileReader("MemberList.csv");
        String info = reader.readLine();
        while (info != null ){
            String[] elements= info.split(",");
            if (elements.length  < 5){
                throw new RuntimeException ("Line too short");//handle missing entrys 
            }
            String Name = elements[0];
            String ID = elements[1];
            String phoneNum = elements[2];
            String overdue= elements[3];
            String fineTot= elements [4];
            Member temp = new Member( Name , ID , phoneNum, overdue,fineTot);
            memeberBook.add((Member)temp);
            info= reader.readLine(); 
        }
    } catch (Exception e) {
    
        //TODO: handle exception
    }

   }
    public static void main(String[] args) {

        MemberPages yellow = new MemberPages();
        yellow.readMemberList();
        Member test = new Member ( "jhon doe", "000", "0","0","0");
        yellow.memeberBook.add(test);
        yellow.dumpMember(yellow.findMember(yellow.memeberBook,"978DF12"));

    }
}