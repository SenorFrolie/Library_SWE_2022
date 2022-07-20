
public class Books  {
    private String Title;
    private String Author;
    private String RefID;
    private String BOTY;

    
    public Books(String Title, String Author, String RefID,String BOTY){
            this.Title = Title;
            this.Author = Author;
            this.RefID = RefID;
            this.BOTY = BOTY;
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
}

