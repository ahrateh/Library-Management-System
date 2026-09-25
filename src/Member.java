public class Member {
    private final int id;
    private String name;
    private final MembershipType membershipType;
    private double balanceOwed;
    private int numOfItems = 0;

    Member(String name, int id, MembershipType membershipType) {
        this.name = name;
        this.id = id;
        this.membershipType = membershipType;
        this.balanceOwed = 0.0;
    }
    Member(String name, int id, MembershipType membershipType, double balanceOwed) {
        this(name , id, membershipType);
        this.balanceOwed = balanceOwed;
    }

    public void setBalanceOwed(double balanceOwed) {
        this.balanceOwed = balanceOwed;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public double getBalanceOwed() {
        return balanceOwed;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void display() {
        System.out.println("Name : " + name);
        System.out.println("ID : " + id);
        System.out.println("Membership Type : " + membershipType);
        System.out.println("Balance Owed : " + balanceOwed);
        System.out.println("Number of Items : " + numOfItems);
    }


}
