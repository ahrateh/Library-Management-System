public class Book extends Item implements Renewable{
    private String author;
    private int pageCount;

    Book(String title, ItemStatus status, String author, int pageCount){
        super(title, status);
        this.author = author;
        this.pageCount = pageCount;
    }

    Book(String title,int memberId ,ItemStatus status, String author, int pageCount){
        super(title, memberId, status);
        this.author = author;
        this.pageCount = pageCount;
    }

    public int getLoanPeriod(){
        return 14;
    }

    public double fine(int days) {
        return days * 5;
    }

    public void display(){
        System.out.println("Category : Book");
        System.out.println("ID : " + getId());
        System.out.println("Title : " + getTitle());
        System.out.println("Status : " + getStatus());
        System.out.println("Borrower ID : " + getMemberId());
        System.out.println("LoanPeriod : " + getLoanPeriod() + " days");
        System.out.println("Fine for 1 day : 5 EGP");
    }

    public void renewLoan(){
        if(getNumRenewalsUsed() < getRenewalLimit()){
            setNumRenewalsUsed(getNumRenewalsUsed() + 1);
            System.out.println("Successfully Renew loan for item ID ( " + getId() + " ) - " + (getRenewalLimit() - getNumRenewalsUsed()) + " times left!");
        }
        else {
            System.out.println("ERROR ; Renew loan can not br renewed more than " + getRenewalLimit() + " times");
        }
    }

    public int getRenewalLimit(){
        return 2;
    }
}
