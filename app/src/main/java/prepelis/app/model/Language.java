package prepelis.app.model;

public enum Language {
    SPANISH ("Spanish"),
    ENGLISH ("English"),
    FRENCH ("French"),
    GERMAN ("German"),
    ITALIAN ("Italian"),
    CHINESE ("Chinese"),
    JAPANESE ("Japanese"),
    KOREAN ("Korean");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    public String getName() {
        return this.name();
    }

    public String getValue() {
        return value;
    }

}
