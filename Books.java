
public class Books  {
    private String Title;
    private String Author;
    private String RefID;
    private String BOTY;
    private String Quantity;
    private String Value;

    
    public Books(String Title, String Author, String RefID,String BOTY, String Quantity,String Value){
            this.Title = Title;
            this.Author = Author;
            this.RefID = RefID;
            this.BOTY = BOTY;
            this.Quantity = Quantity;
            this.Value = Value;
    }
 
    public String getTitle(){
        return Title;
    }
    public String getAuthor(){
        return Author;
    }
    public String getRefID(){
        return RefID;
    }
    public String getBOTY(){
        return BOTY;
    }
    public String getQuantiy(){
        return Quantity;
    }
    public String getValue(){
        return Value;
    }
}

