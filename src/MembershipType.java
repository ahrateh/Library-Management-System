public enum MembershipType {
    BASIC(0),
    SILVER(0.25),
    GOLD(0.5);

    private double waiver;

    MembershipType(double waiver) {
        this.waiver = waiver;
    }

    public double getWaiver(){
        return waiver;
    }
}
