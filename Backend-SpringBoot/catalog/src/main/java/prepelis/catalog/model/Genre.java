package prepelis.catalog.model;

public enum Genre {
    ACTION ("Action"),
    ADVENTURE ("Adventure"),
    COMEDY ("Comedy"),
    FANTASY ("Fantasy"),
    HORROR ("Horror"),
    ROMANCE ("Romance"),
    FICTION ("Science fiction"),
    DRAMA ("Drama"),
    THRILLER ("Thriller");

    private final String value;

    Genre(String value) {
        this.value = value;
    }

    public String getName() {
        return this.name();
    }

    public String getValue() {
        return value;
    }
}
