import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class MemberPages {

    public void addMemeber(Member newMember, String name, String id, String phoneNum) {

        newMember.setID(id);
        newMember.setName(name);
        newMember.setphoneNum(phoneNum);
    }

    public static void main(String[] args) {

        MemberPages yellow = new MemberPages();
        Member newMember = new Member();
        yellow.addMemeber(newMember, "Kelly Marx", "51SD226", "512-999-9999");
    }
}