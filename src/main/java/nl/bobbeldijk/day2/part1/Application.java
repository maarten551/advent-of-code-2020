package nl.bobbeldijk.day2.part1;

import nl.bobbeldijk.util.Answerable;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.util.List;
import java.util.regex.Pattern;

public class Application implements Answerable<Integer> {

    private static final Pattern checkRegexPattern = Pattern.compile("^(\\d+)-(\\d+) ([a-z]): ([a-z]+)$");

    public static void main(String[] args) {
        System.out.println((new Application()).calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY2)));
    }

    @Override
    public Integer calculateAnswer(List<String> input) {
        return (int) input.parallelStream()
                .map(this::parseInputRule)
                .filter(this::checkParsedRule)
                .count();
    }

    private ParsedRule parseInputRule(String inputRule) {
        var matcher = checkRegexPattern.matcher(inputRule);

        if (!matcher.matches()) {
            throw new Error(String.format("Failed to parse inputRule '%s'", inputRule));
        }

        return new ParsedRule(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                matcher.group(3).charAt(0),
                matcher.group(4));
    }

    protected boolean checkParsedRule(ParsedRule parsedRule) {
        var count = parsedRule.getLettersToCheck().chars().filter(c -> c == parsedRule.getLetterToFind()).count();

        return count >= parsedRule.getBeginNumber() && count <= parsedRule.getEndNumber();
    }
}
