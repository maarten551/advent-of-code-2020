package nl.bobbeldijk.day11;

import java.util.Arrays;

public enum CoordinateState {
    FLOOR('.'),
    EMPTY('L'),
    SEATED('#');

    public static CoordinateState mapCharToState(int charPointer) {
        return Arrays.stream(CoordinateState.values())
                .filter(s -> s.mappedChar == (char) charPointer)
                .findFirst()
                .orElseThrow(() -> new Error(String.format("failed to parse %c", (char) charPointer)));
    }

    private final char mappedChar;

    CoordinateState(char c) {
        mappedChar = c;
    }
}
