public class Loan{
    private String UserName; //name of user or library id for user
    private String ItemValue; //name of the book being checked out
    private String DueDate; 
    private String TodaysDate; 
    private double OverdueFine; 


public Loan(String UserName, String ItemValue, String DueDate, String TodaysDate, double OverdueFine){
    this.UserName = UserName;
    this.ItemValue = ItemValue;
    this.DueDate = DueDate;
    this.TodaysDate = TodaysDate;
    this.OverdueFine = OverdueFine;
}


public String getUserName(){
    return UserName;
}
public String getItemValue(){
    return ItemValue;
}
public String getDueDate(){
    return DueDate;
}
public String getTodaysDate(){
    return TodaysDate;
}
public double getOverdueFine(){
    return OverdueFine;
}

}
/* 
public class Loan{
    public void calculateFine(String UserName, String ItemValue, String DueDate, String TodaysDate, double OverdueFine){
        if(DueDate > TodaysDate){
            if(OverdueFine < ItemValue){
                OverdueFine += 0.1;
            }
            else{
                OverdueFine = ItemValue;
            }
        }
        else{
            OverdueFine = OverdueFine;
        }
} 
} */