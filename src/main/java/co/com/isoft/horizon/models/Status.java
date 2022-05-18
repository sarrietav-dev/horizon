package co.com.isoft.horizon.models;

public enum Status {
    APPROVED(0),
    IN_PROGRESS(1),
    CLOSED(2);

    private final int hierarchy;

    Status(int hierarchy) {
        this.hierarchy = hierarchy;
    }

    public int getHierarchy() {
        return hierarchy;
    }
}
