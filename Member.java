public class Member {

    private String name;
    private String id;
    private String phoneNum;
    private String overdue;
    private String finetotal;

    public Member(String name , String phoneNum, String id, String overdue, String finetotal) {
        this.name = name;
        this.id = id;
        this.phoneNum = phoneNum;
        this.overdue = overdue;
        this.finetotal = finetotal;
    }
    

    

    public void setID(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setphoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setOverdue(String overdue) {
        this.overdue = overdue;
    }

    public void setFineTotal(String finetotal) {
        this.finetotal = finetotal;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getOverdue() {
        return overdue;
    }

    public String getFineTotal() {
        return finetotal;
    }

}
