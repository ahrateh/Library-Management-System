public abstract class Item {
    private static int count = 2000;
    private final int id;
    private final String title;
    private ItemStatus status;
    private int memberId;
    private int numRenewalsUsed = 0;

    Item(String title, ItemStatus status) {
        this.title = title;
        this.status = status;
        this.id = ++count;
    }

    Item(String title, int memberId, ItemStatus status) {
        this.title = title;
        this.memberId = memberId;
        this.status = status;
        this.id = ++count;
    }

    public static int getCount(){
        return count;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getNumRenewalsUsed() {
        return numRenewalsUsed;
    }

    public void setItemStatus(ItemStatus status) {
        this.status = status;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setNumRenewalsUsed(int numRenewalsUsed) {
        this.numRenewalsUsed = numRenewalsUsed;
    }

    public abstract int getLoanPeriod();

    public abstract double fine(int days);

    public abstract void display();
}
