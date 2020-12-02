package nl.bobbeldijk.day2.part1;

public class ParsedRule {
    private final int beginNumber;
    private final int endNumber;
    private final char letterToFind;
    private final String lettersToCheck;

    public ParsedRule(int beginNumber, int endNumber, char letterToFind, String lettersToCheck) {
        this.beginNumber = beginNumber;
        this.endNumber = endNumber;
        this.letterToFind = letterToFind;
        this.lettersToCheck = lettersToCheck;
    }

    public int getBeginNumber() {
        return beginNumber;
    }

    public int getEndNumber() {
        return endNumber;
    }

    public char getLetterToFind() {
        return letterToFind;
    }

    public String getLettersToCheck() {
        return lettersToCheck;
    }
}
