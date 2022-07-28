import java.io.*;
//import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;



public class BookShelf {
    static ArrayList<Books> dataList = new ArrayList<Books>();
    static ArrayList<VisualMat> dataList_VS = new ArrayList<VisualMat>();

       // for reading a CSV file that contains the information on the books
       public void readBookList() throws IOException {
        // Processing Books.CSV into objects 
        try {
            FileReader fr = new FileReader("Books.csv");
            BufferedReader br = new BufferedReader(fr);
            String stringRead = br.readLine();

            while (stringRead != null) {
                String[] elements = stringRead.split(",");

                if (elements.length < 6) {
                    throw new RuntimeException("line too short"); // handle missing entries
                }
                
                String Title = elements[0];
                String Author = elements[1];
                String Id = elements[2];
                String BOTY = elements[3];
                String Quantity = elements[4];
                String Value = elements[5];

                Books temp = new Books(Title, Author, Id, BOTY,Quantity,Value);

                dataList.add((Books) temp);
                // read the next line
                stringRead = br.readLine();

            }
            br.close();

        } catch (Exception e) {

            System.out.println("ERROR: Invalid CSV file read. . .");
            
        }



    }

    // Print the Menu 
    public static void ShelfMainMenu() throws IOException{
        Scanner sc = new Scanner(System.in);


        System.out.println("--------------------------------------");
        System.out.println("| Welcome to our hottest Selection! | ");
        System.out.println("--------------------------------------");
        System.out.println("would you rather check out Book or Visual Audio");

        //do {
                System.out.println("\r\n");
                System.out.println("1. Books ");
                System.out.println("2. Visual ");
                System.out.println("3. Return to Main Menu ");
                System.out.println("0. Kill Program ");

                int user_input = sc.nextInt();
                int Filter_input = sc.nextInt();


            if (user_input == 1) {
                System.out.println("--------------------------------------");
                System.out.println("|           book section              |");
                System.out.println("--------------------------------------"); 

                for(Books book:dataList){
                    System.out.println(book.getTitle() + " by " + book.getAuthor());
                }
            }
            else if(user_input == 2){
                System.out.println("--------------------------------------");
                System.out.println("|       Visual Audio section         | ");
                System.out.println("--------------------------------------");
                for(VisualMat book :dataList_VS){
                    System.out.println(book.getTitle() + " by " + book.getDirector());
                }
                
            }

        //} while (user_input != 0);
    }

    

    public void readVideoMat(){
   

            // Procesing Visual.csv into objects 

            try {
                FileReader frV = new FileReader("Visual.csv");
                BufferedReader brV = new BufferedReader(frV);
                String stringReadV = brV.readLine();

                while (stringReadV != null) {
                    String[] elementsV = stringReadV.split(",");

                    if (elementsV.length < 3) {
                        throw new RuntimeException("line too short"); // handle missing entries
                    }

                    String Title_V = elementsV[0];
                    String Director = elementsV[1];
                    String Id_V = elementsV[2];

                    VisualMat temp_2 = new VisualMat(Title_V, Director, Id_V);

                    dataList_VS.add((VisualMat) temp_2);

                    // read the next line
                    stringReadV = brV.readLine();

                    }
            } catch (Exception e) {
                System.out.println("ERROR: Invalid CSV file read. . .");
            }
    }

    public void FilterBooks(int filter){
        if(filter == 1){
            System.out.println("---------------------------");
                
                for (Books items : dataList) {
                    System.out.println(items.getTitle());
                }
            System.out.println("---------------------------");

            }else if(filter == 2){
            System.out.println("---------------------------");
                for (Books items : dataList) {
                    System.out.println(items.getAuthor());
                }
            System.out.println("---------------------------");
            }
            else if(filter == 3){
            System.out.println("---------------------------");
                for (Books items : dataList) {
                    if(items.getBOTY().equals("Y")){
                        System.out.println(items.getTitle() + " by "+ items.getAuthor());
                    }
                }
            System.out.println("---------------------------");
            }
        }

        public String findID(String ID){

            for(int i = 0; i < dataList.size();i++){
                if(dataList.get(i).getRefID().equals(ID)){

                    return dataList.get(i).getTitle() + " " + ID;
                }
            }

            return "not Found";
        }


        public String SendBook(String BookTitle){
            // for(Books items : dataList){
            //System.out.println(Title);
            for(int i = 0; i < dataList.size();i++){
                 //System.out.println(dataList.get(i).getTitle());
                if(dataList.get(i).getTitle().equals(BookTitle)){
                    return BookTitle;
                }
            }
            return "Not Found";  
        }


        public boolean isBestSeller(String BookTitle){

            for(int i = 0; i < dataList.size();i++){
                //System.out.println(dataList.get(i).getTitle());
               if( dataList.get(i).getTitle().equals(BookTitle) && dataList.get(i).getBOTY().equals("Y")){
                   return true;
               }
           }
           return false;  
        }



public static void main(String[] args) throws IOException {


    BookShelf green = new BookShelf();

    green.readBookList();
    System.out.println(green.SendBook("Game of thrones"));
    System.out.println(green.isBestSeller("Harry potter"));
    System.out.println(green.findID("10"));


}


}

