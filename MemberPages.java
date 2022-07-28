import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class MemberPages {
// Bellow is the memberBook which stores each memeber Object that is bieng worked on or edited in the system. 
    ArrayList<Member> memberBook = new ArrayList<Member>();

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
        Member current =findMember(memberBook, ID);

        current.setName(property);
        System.out.println(current.getName()+" Has been sucsessfully edited");

    }
  
    public int findMemberIndex (ArrayList<Member> list , String ID){
        int index= 0;
        for(int i = 0 ; i < list.size(); i++){
           System.out.println("finding...");
           System.out.println(list.get(i).getPhoneNum());
            if ( list.get(i).getId()==ID){ 
                index=i;  
                System.out.println("found");
                return index;         


            }
        

        }
    return index;
    }
    
    public Member findMember(ArrayList<Member> memeberList, String ID) {
        // Used to locate a Member object via ID parameter 
               int index = findMemberIndex(memeberList, ID);
        
                return memberBook.get(index);
            }

            public void readMemberList() throws IOException {
                FileReader file = new FileReader("MemberList.csv");
                BufferedReader reader = new BufferedReader(file);
                try {
                    reader.readLine();
                    String line = null;
                    while ((line = reader.readLine()) != null ){
                        String[] elements= line.split(",");
                        if (elements.length  < 5){
                            throw new RuntimeException ("Line too short");//handle missing entrys 
                        }
                        String Name = elements[0];
                        String ID = elements[1];
                        String phoneNum = elements[2];
                        String overdue= elements[3];
                        String fineTot= elements [4];
                        Member temp = new Member( Name , ID , phoneNum, overdue,fineTot);
                        memberBook.add((Member)temp);
                    }
                } catch (Exception e) {
                    System.out.println("FILE ERROR: "+e);
                } finally {
                    reader.close();
                }
                    //TODO: handle exception
            } 
            


    public static void main(String[] args) throws IOException {

        MemberPages yellow = new MemberPages();
        Member test = new Member ( "jhon doe", "001", "1111","0","0");
        Member test1 = new Member ( "paul doe", "002", "2222","0","0");
        Member test2 = new Member ( "jane doe", "003", "3333","0","0");
       // yellow.memberBook.add(test);
       // yellow.memberBook.add(test1);
       // yellow.memberBook.add(test2);
       // yellow.dumpMember(yellow.findMember(yellow.memberBook, "3333"));
        yellow.readMemberList();
        yellow.dumpMember(yellow.findMember(yellow.memberBook, "978DF12"));
        


    }
}