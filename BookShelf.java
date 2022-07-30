import java.io.*;
//import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;


public class BookShelf {
    static ArrayList<Books> dataList = new ArrayList<Books>();
    static ArrayList<VisualMat> dataList_VS = new ArrayList<VisualMat>();

       // for reading a CSV file that contains the information on the books
    public void readBookList() throws IOException {
        FileReader fr = new FileReader("Books.csv");
        BufferedReader br = new BufferedReader(fr);
        // Processing Books.CSV into objects 
        try {
            br.readLine(); // read first line to avoid displaying file header
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] elements = line.split(",");

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

            }
            br.close();

        } catch (Exception e) {
            System.out.println("ERROR: Invalid books CSV file read. . .");
        } finally {
            br.close();
        }
    }

    
    // Procesing Visual.csv into objects 
    public void readVideoMat(){
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
                brV.close();
                }
        } catch (Exception e) {
            System.out.println("ERROR: Invalid Visual read CSV file read. . .");
        }
}


    // Print the Menu 
    public static void ShelfMainMenu() throws IOException{
        Scanner sc = new Scanner(System.in);
       // BookShelf books = new BookShelf();

        System.out.println("--------------------------------------");
        System.out.println("| Welcome to our hottest Selection! | ");
        System.out.println("--------------------------------------");
        System.out.println("would you rather check out Book or Visual Audio");

        //do {
                System.out.println("\r\n");
                System.out.println("1. Books ");
                System.out.println("2. Visual ");
                System.out.println("3. Return to Main Menu ");
                System.out.println("4. Request new book ");
                System.out.println("0. Kill Program ");

                int user_input = sc.nextInt();

            if (user_input == 1) {
                System.out.println("--------------------------------------");
                System.out.println("|           book section              |");
                System.out.println("--------------------------------------"); 

                for(Books book:dataList){
                    System.out.println(book.getTitle() + " by " + book.getAuthor());
                }
                FilterBooks();
                //This is where checkouts goes
             
            }
            else if(user_input == 2){
                System.out.println("--------------------------------------");
                System.out.println("|       Visual Audio section         | ");
                System.out.println("--------------------------------------");
                for(VisualMat book :dataList_VS){
                    System.out.println(book.getTitle() + " by " + book.getDirector());
                }
                
            }
            else if(user_input == 3){
                sc.close();
                return;

            }
            else if(user_input == 4){
                requestNewBook();
            }
            sc.close();

        //} while (user_input != 0);
    }

    

    static public void FilterBooks(){
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------");
        System.out.println();
        System.out.println("----------------------------------------------");


        System.out.println("Would you like to filter the selection: 1/0");
        int Answer = sc.nextInt();
        if(Answer == 0){
            sc.close();
            return;
        }
        else{
            System.out.println("Choose selection");
            System.out.println("1: By Title ");
            System.out.println("2: By Author ");
            System.out.println("3: By Best Seller ");

        int filter =sc.nextInt();

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
        sc.close();
    }

    
        public static String findID(String ID){
            for(int i = 0; i < dataList.size();i++){
               if(dataList.get(i).getRefID().equals(ID)){
                   String part = dataList.get(i).getRefID();
                  // System.out.println(part);
                   String []parts = part.split("\\.");
                   String part1 = parts[0];
                   System.out.println(part1);

                   if(part1.equals("RM"))
                   return "Can't checkout reference material";
                   
               }

                if(dataList.get(i).getRefID().equals(ID)){
                    return dataList.get(i).getTitle() + " " + dataList.get(i).getRefID();
                }
            }
            return "not Found";
        }

            
        public static String findVMID(String ID){
            for(int i = 0; i < dataList_VS.size();i++){
               if(dataList_VS.get(i).getRefID().equals(ID)){
                   String part = dataList_VS.get(i).getRefID();
                  // System.out.println(part);
                   String []parts = part.split("\\.");
                   String part1 = parts[0];
                   System.out.println(part1);

                   if(part1.equals("RM"))
                   return "Can't checkout reference material";
                   
               }

                if(dataList_VS.get(i).getRefID().equals(ID)){
                    return dataList_VS.get(i).getTitle() + " " + dataList_VS.get(i).getRefID();
                }
            }
            return "not Found";
        }




        public String SendBook(String BookTitle)throws IOException, CsvException{
            // for(Books items : dataList){
            //System.out.println(Title);
            for(int i = 0; i < dataList.size();i++){
                 //System.out.println(dataList.get(i).getTitle());
                if(dataList.get(i).getTitle().equals(BookTitle)){

                    int j = Integer.parseInt(dataList.get(i).getQuantiy());
                    j = j - 1;
                    String new_Quant_value = Integer.toString(j);
                    editQuantity(new_Quant_value, dataList.get(i).getRefID());
                    
                   // updateCSV("Books.csv", new_Quant_value, old_Quant_value ,BookTitle);

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

        public static void requestNewBook() throws IOException{
            System.out.println("---------------------------------------");
            System.out.println("Is this a Book or Visual Material? ");
            System.out.println("1: Book ");
            System.out.println("2: Visual Material ");
            System.out.println("---------------------------------------");

            Scanner sc = new Scanner(System.in);
            int Answer = sc.nextInt();
            if(Answer == 1){
                String booksrc = "Books.csv";
                System.out.println("Please enter the necessary infomation. ");
                System.out.println("For example: Title, Author ");

                System.out.print("Title and Author: " );
                String Title = sc.next();
                String requested = Title + " (requested)";
                CSVWriter writer = new CSVWriter(new FileWriter(booksrc, true));
               
                String [] record = requested.split(",");
                writer.writeNext(record);

                writer.close();
            }
            else{
                String Visualsrc = "Visual.csv";
                System.out.println("Please enter the necessary infomation. ");
                System.out.println("---------------------------------------");

                System.out.println("Title and Director: " );
                String Title = sc.next();
                String requested = Title + " (requested)";


                CSVWriter writer = new CSVWriter(new FileWriter(Visualsrc, true));
                String [] record = requested.split(",");
                writer.writeNext(record);
                writer.close();
                sc.close();
            }
        }



    public void editQuantity(String property, String ID){
        // Used to edit a member property via findMember method and ID
        Books current = findMember(ID);
        current.setQuantiy(property);
        System.out.println(current.getTitle()+" Has been sucsessfully edited");


    }

    public Books findMember(String ID) {
        // Used to locate a Member object via ID parameter 
               int index = findMemberIndex(ID);
                return dataList.get(index);
            }

    public int findMemberIndex (String ID){
            int index= 0;
            for(int i = 0 ; i < dataList.size(); i++){
               System.out.println("finding...");
                if ( dataList.get(i).getRefID()==ID){ 
                    index=i;  
                    System.out.println("found");
                    return index;         
                }      
            }
        return index;
        }
        


/*
        public static void updateCSV(String fileToUpdate, String new_Quant_value, String old_Quant_value ,String BookTitle) throws IOException, CsvException {

                    File inputFile = new File("Books.csv");

                    // Read existing file
                    CSVReader reader = new CSVReader(new FileReader(fileToUpdate));
                    List<String[]> csvBody = reader.readAll();
                    // get CSV row column and replace with by using row and column

                    for(int i = 0;i < dataList.size();i++){
                        for(int j = 0; j < dataList.size();j++){
                            if(dataList.get(i).getTitle().equals(BookTitle) && dataList.get(j).getQuantiy().equals(old_Quant_value)){
                                csvBody.get(i)[j] = new_Quant_value;
                                reader.close();
                            } 
                        }
                    }

            
                    // Write to CSV file which is open
                    CSVWriter writer = new CSVWriter(new FileWriter(inputFile));
                    writer.writeAll(csvBody);
                    writer.flush();
                    writer.close();
        }
*/


public static void main(String[] args) throws IOException, CsvException {
    BookShelf green = new BookShelf();

    green.readBookList();
    //Test for sending a book if we have it in Books.csv
    System.out.println(green.SendBook("Harry potter"));

    // Test if a book is a best seller, should either return Y for best seller and false otherwise
    System.out.println(green.isBestSeller("Harry potter"));
    
    //Testing for a Reference Material, Should return "Can't checkout reference material"
    System.out.println(BookShelf.findID("RM.15"));

    }

}

