import java.io.*;
//import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
//import java.util.StringTokenizer;


public class BookShelf {

    // for reading a CSV file that contains the information on the books
    static void book_Avaiable() throws IOException {
        // String book_path = "Books.csv";
        BufferedReader reader = null;
        String line = "null";
        Scanner sc = new Scanner(System.in);
        int user_input;

        System.out.println("--------------------------------------");
        System.out.println("| Welcome to our hottest Selection! | ");
        System.out.println("--------------------------------------");

        System.out.println("would you rather check out Book or Visual Audio");

        do {

            System.out.println("\r\n");
            System.out.println("1. Books ");
            System.out.println("2. Visual ");
            System.out.println("3. Return to Main Menu ");
            System.out.println("0. Kill Program ");

            user_input = sc.nextInt();

            if (user_input == 1) {
                System.out.println("--------------------------------------");
                System.out.println("|    You're in the book section        |");
                System.out.println("--------------------------------------");

                List<Books> dataList = new ArrayList<Books>();

                try {
                    FileReader fr = new FileReader("Books.csv");
                    BufferedReader br = new BufferedReader(fr);
                    String stringRead = br.readLine();

                    while (stringRead != null) {
                        String[] elements = stringRead.split(",");

                        if (elements.length < 4) {
                            throw new RuntimeException("line too short"); // handle missing entries
                        }
                        
                        String Title = elements[0];
                        String Author = elements[1];
                        String Id = elements[2];
                        String BOTY = elements[3];

                        Books temp = new Books(Title, Author, Id, BOTY);

                        dataList.add((Books) temp);

                        // read the next line
                        stringRead = br.readLine();

                    }
                } catch (Exception e) {

                }

               /* for (Books items : dataList) {
                    System.out.println(items.getAuthor());
               }
                */
               System.out.println("How would you like it shown to you? ");
               System.out.println("1: By Title");
               System.out.println("2: Author");
               System.out.println("3: Book of the Year");

               user_input = sc.nextInt();
               if(user_input == 1){
            System.out.println("---------------------------");
                for (Books items : dataList) {
                    System.out.println(items.getTitle());
                    
               }
            System.out.println("---------------------------");



             }else if(user_input == 2){
            System.out.println("---------------------------");
                for (Books items : dataList) {
                    System.out.println(items.getAuthor());
               }
            System.out.println("---------------------------");
             }
             else if(user_input == 3){
            System.out.println("---------------------------");
                for (Books items : dataList) {
                    if(items.getBOTY().equals("Y")){
                        System.out.println(items.getTitle() + " by "+ items.getAuthor());

                    }
                }
            System.out.println("---------------------------");


             }

               
            } else if (user_input == 2) {
                System.out.println("--------------------------------------");
                System.out.println("|    You're in Visual Audio section    | ");
                System.out.println("--------------------------------------");

                List<VisualMat> dataList_VS = new ArrayList<VisualMat>();

                try {
                    FileReader fr = new FileReader("Visual.csv");
                    BufferedReader br = new BufferedReader(fr);
                    String stringRead = br.readLine();

                    while (stringRead != null) {
                        String[] elements = stringRead.split(",");

                        if (elements.length < 3) {
                            throw new RuntimeException("line too short"); // handle missing entries
                        }

                        String Title = elements[0];
                        String Director = elements[1];
                        String Id = elements[2];

                        VisualMat temp_2 = new VisualMat(Title, Director, Id);

                        dataList_VS.add((VisualMat) temp_2);

                        // read the next line
                        stringRead = br.readLine();

                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }



                for (VisualMat items : dataList_VS) {
                    System.out.println(items.getDirector());
                }

            }else if(user_input == 3){
                return;
            }

        } while (user_input != 0);
        reader.close();
    }

}
