public class VisualMat {
    private String Title;
    private String Director;
    private String RefID;

    
    public VisualMat(String Title, String Director, String RefID){
            this.Title = Title;
            this.Director = Director;
            this.RefID = RefID;
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

}
