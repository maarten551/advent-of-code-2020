package nl.bobbeldijk.util;

public enum InputFile {
    DAY1("day-1"),
    DAY2("day-2");

    private final String filename;

    InputFile(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
