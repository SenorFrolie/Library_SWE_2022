class Member {

    private String id;
    private String name;
    private String phoneNum;
    private String[] tickets;
    private int overdue;
    private int finetotal;

    public void setID(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setphoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setTickets(String[] tickets) {
        this.tickets = tickets;
    }

    public void setOverdue(int overdue) {
        this.overdue = overdue;
    }

    public void setFineTotal(int finetotal) {
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

    public String[] getTickets() {
        return tickets;
    }

    public int getOverdue() {
        return overdue;
    }

    public int getFineTotal() {
        return finetotal;
    }

}
