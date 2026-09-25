public class Magazine extends Item implements Renewable {
    private int issueNumber;

    Magazine(String title, ItemStatus status, int issueNumber) {
        super(title, status);
        this.issueNumber = issueNumber;
    }

    Magazine(String title, int memberId, ItemStatus status, int issueNumber) {
        super(title, memberId, status);
        this.issueNumber = issueNumber;
    }

    public int getLoanPeriod() {
        return 7;
    }

    public double fine(int days) {
        return Math.min((days * 3), 30);
    }

    public void display(){
        System.out.println("Category : Magazine");
        System.out.println("ID : " + getId());
        System.out.println("Title : " + getTitle());
        System.out.println("Status : " + getStatus());
        System.out.println("Borrower ID : " + getMemberId());
        System.out.println("LoanPeriod : " + getLoanPeriod() + " days");
        System.out.println("Fine for 1 day : 3 EGP");
    }

    public void renewLoan(){
        if(getNumRenewalsUsed() < getRenewalLimit()){
            setNumRenewalsUsed(getNumRenewalsUsed() + 1);
            System.out.println("Successfully Renew loan for item ID ( " + getId() + " ) - " + (getRenewalLimit() - getNumRenewalsUsed()) + " times left !");
        }
        else {
            System.out.println("ERROR ; Renew loan can not br renewed more than " + getRenewalLimit()  + " times");
        }
    }

    public int getRenewalLimit(){
        return 1;
    }
}
