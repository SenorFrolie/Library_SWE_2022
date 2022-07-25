import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class MemberPages {

    ArrayList<Member> memeberBook = new ArrayList<Member>();

    public void addMemeber(Member newMember, String name, String id, String phoneNum) {

        newMember.setID(id);
        newMember.setName(name);
        newMember.setphoneNum(phoneNum);
    }

    public void dumpMember(Member currentMember) {

        System.out.println(currentMember.getName());
        System.out.println(currentMember.getId());
        System.out.println(currentMember.getPhoneNum());
        System.out.println(currentMember.getTickets());
        System.out.println(currentMember.getOverdue());
        System.out.println(currentMember.getFineTotal());

    }

    /*
     * public void readMembers (){
     * 
     * 
     * FileReader file = new FileReader("MemberList.csv");
     * BufferedReader reader = null;
     * String line ="";
     * try {
     * 
     * reader = new BufferedReader(file);
     * while ((line = reader.readLine()) !=null){
     * 
     * String [] row= line.split(",");
     * for(String index : row){
     * System.out.printf("%-10s", index);
     * }
     * }
     * System.out.println();
     * 
     * }
     * catch(Exception e){
     * e.printStackTrace();
     * 
     * }
     * finally{
     * try{
     * reader.close();
     * 
     * } catch(IOException e){
     * //TODO Auto - gen catch block
     * e.printStackTrace();
     * }
     * 
     * }
     * 
     * }
     * public void findMember(){
     * 
     * }
     */
    public Member findMember(ArrayList<Member> memeberList, String ID) {

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

    }
}