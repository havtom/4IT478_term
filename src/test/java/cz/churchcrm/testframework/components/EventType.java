package cz.churchcrm.testframework.components;

public enum EventType {
    CHURCH_SERVICE("Church Service", 1),
    SUNDAY_SCHOOL("Sunday School", 2),
    AAA("AAAAAAAAAAAAAAAA", 3);

    public final String type;
    public final int nr;

    private EventType(String type, int nr) {
        this.type = type;
        this.nr = nr;
    }

}
