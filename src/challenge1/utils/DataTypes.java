package challenge1.utils;

public enum DataTypes {
    HTML("text/html"),
    JAVASCRIPT("text/javascript"),
    JPEG("image/jpeg"),
    JPG("image/jpg"),
    PNG("image/png"),
    CSS("text/css");

    public final String value;

    private DataTypes(String value) {
        this.value = value;
    }
}
