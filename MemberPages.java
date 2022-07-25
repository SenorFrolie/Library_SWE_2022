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
        System.out.println(currentMember.getTickets());
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

   
    public static void main(String[] args) {

        MemberPages yellow = new MemberPages();
        Member newMember = new Member();
        Member newMember1 = new Member();
        Member newMember2 = new Member();
        yellow.addMemeber(newMember, "Kelly Marx", "513SD227", "512-999-9999");
        yellow.addMemeber(newMember1, "Marry Marx", "677SD226", "512-888-8888");
        yellow.addMemeber(newMember2, "Sally Davis", "222CD656", "819-667-6666");
    
        yellow.memeberBook.add(newMember);
        yellow.memeberBook.add(newMember1);
        yellow.memeberBook.add(newMember2);

     
        yellow.dumpMember(yellow.memeberBook.get(0));
        
        yellow.dumpMember(yellow.findMember(yellow.memeberBook, "222CD656"));

        yellow.editMemeber("Kevin Marx", "513SD227");
        yellow.dumpMember(yellow.findMember(yellow.memeberBook,"513SD227" ));

    }
}