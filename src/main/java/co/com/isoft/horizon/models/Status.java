package co.com.isoft.horizon.models;

/**
 * Represents the states that a PQRS can be.
 * The rule is that a PQRS cannot have a status that is lesser than the current status.
 * <p>
 * <br>
 * Every PQRS starts at PENDING. It can cycle between PENDING and REJECTED, because it can be edited afterwards.
 * <p>
 * <br>
 * Then it can be APPROVED by an admin, IN_PROGRESS when someone starts working on it,
 * and then CLOSED wherever it's solved or not.
 * <p>
 * <br>
 * This hierarchy is represented by an integer attribute.
 */
public enum Status {
    PENDING(0),
    REJECTED(0),
    APPROVED(1),
    IN_PROGRESS(2),
    CLOSED(3);

    private final int hierarchy;

    Status(int hierarchy) {
        this.hierarchy = hierarchy;
    }

    public int getHierarchy() {
        return hierarchy;
    }
}
