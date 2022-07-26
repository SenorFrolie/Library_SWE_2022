public class Return{
    private String UserName; //name of user or library id for user
    private String BookName; //name of the book being returned
    private String VisualName; //name of the audio/video being returned
    private String ReturnDate; //date item is being returned
    private double OverdueFine; //fine amount


    public Return(String UserName, String BookName, String VisualName, String ReturnDate, double OverdueFine){
        this.UserName = UserName;
        this.BookName = BookName;
        this.VisualName = VisualName;
        this.ReturnDate = ReturnDate;
        this.OverdueFine = OverdueFine;
    }

    public String getUserName(){
        return UserName;
    }
    public String getBookName(){
        return BookName;
    }
    public String getVisualName(){
        return VisualName;
    }
    public String getReturnDate(){
        return ReturnDate;
    }
    public double getOverdueFine(){
        return OverdueFine;
    }
}