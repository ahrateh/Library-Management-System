public class DVD extends Item {
    private double runTime;

    DVD(String title, ItemStatus status, double runTime) {
        super(title, status);
        this.runTime = runTime;
    }

    DVD(String title, int memberId, ItemStatus status, double runTime) {
        super(title, memberId, status);
        this.runTime = runTime;
    }

    public int getLoanPeriod(){
        return 3;
    }

    public double fine(int days){
        return days * 15;
    }

    public void display(){
        System.out.println("Category : DVD");
        System.out.println("ID : " + getId());
        System.out.println("Title : " + getTitle());
        System.out.println("Status : " + getStatus());
        System.out.println("Borrower ID : " + getMemberId());
        System.out.println("LoanPeriod : " + getLoanPeriod() + " days");
        System.out.println("Fine for 1 day : 15 EGP");
    }
}
