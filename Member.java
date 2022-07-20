public class Member {
    
    private String id;
    private String name;
    private String phoneNum;
    private String[] tickets;
    private int overdue;
    private int finetotal;

    public Member (String id , String name, String phoneNum, String[] tickets , int overdue, int finetotal)
    {
        this.id=id;
        this.name=name;
        this.phoneNum=phoneNum;
        this.tickets=tickets;
        this.overdue=overdue;
        this.finetotal=finetotal;

    }

    public String getId (){
        return id ;
    }
    public String getName (){
        return name ;
    }
    public String getPhoneNum (){
        return phoneNum ;
    }
    public String[] getTickets (){
        return tickets ;
    }
    public int getOverdue (){
        return overdue ;
    }
    public int getFineTotal(){
        return finetotal;
    }

   
}

