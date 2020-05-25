package cz.churchcrm.testframework.components;

public class GridRow {
    private String date;
    private String comment;
    private String type;
    private String id;

    public void shouldContain(String id) {
        // TODO: Implementation

        throw new RuntimeException("Record: " + id + "not found");
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
