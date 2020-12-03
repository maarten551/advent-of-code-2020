package nl.bobbeldijk.day3;

public enum HillCoordinateItem {
    NOTHING('.'),
    TREE('#');

    private final char mappedFrom;

    HillCoordinateItem(char mappedFrom) {
        this.mappedFrom = mappedFrom;
    }

    public char getMappedFrom() {
        return mappedFrom;
    }
}
