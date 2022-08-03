public class VisualMat {
    private String Title;
    private String Director;
    private String RefID;
    private String Value;

    
    public VisualMat(String Title, String Director, String RefID,String Value){
            this.Title = Title;
            this.Director = Director;
            this.RefID = RefID;
            this.Value = Value;
    }

    public String getTitle(){
        return Title;
    }
    public String getDirector(){
        return Director;
    }
    public String getRefID(){
        return RefID;
    }
    public String getValue(){
        return Value;
    }

}
