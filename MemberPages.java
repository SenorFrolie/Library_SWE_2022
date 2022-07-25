import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class MemberPages {

    public void addMemeber(Member newMember, String name, String id, String phoneNum) {

        newMember.setID(id);
        newMember.setName(name);
        newMember.setphoneNum(phoneNum);
    }
    public void dumpMember(Member currentMember){
        System.out.println(currentMember.getName());
        System.out.println(currentMember.getId());
        System.out.println(currentMember.getPhoneNum());
        System.out.println(currentMember.getTickets());
        System.out.println(currentMember.getOverdue());
        System.out.println(currentMember.getFineTotal());

    }
    public static void main(String[] args) {

        MemberPages yellow = new MemberPages();
        Member newMember = new Member();
        yellow.addMemeber(newMember, "Kelly Marx", "51SD226", "512-999-9999");
        yellow.dumpMember(newMember);
    
    }
}