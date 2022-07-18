import java.util.Scanner;
import java.util.*;
import java.io.*;

public class TheSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Where would you like to go? ");
        int user_input;

        do {

            System.out.println("\r\n");
            System.out.println("1. Browse ");
            System.out.println("2. Checkout ");
            System.out.println("3. Return ");
            System.out.println("4. Exit. ");

            user_input = sc.nextInt();

            System.out.print(user_input);

        } while (user_input != 4);
    }

}