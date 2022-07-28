public class Checkout{
    private String UserName; //name of user or library id for user
    private String BookName; //name of the book being checked out
    private String VisualName; //name of the audio/video being checked out
    private String CheckoutDate; //date item is being checked out
    private String DueDate; //due date for the item


    public Checkout(String UserName, String BookName, String VisualName, String CheckoutDate, String DueDate){
        this.UserName = UserName;
        this.BookName = BookName;
        this.VisualName = VisualName;
        this.CheckoutDate = CheckoutDate;
        this.DueDate = DueDate;
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
    public String getCheckoutDate(){
        return CheckoutDate;
    }
    public String getDueDate(){
        return DueDate;
    }

}


