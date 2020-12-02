package nl.bobbeldijk.day2.part2;

import nl.bobbeldijk.day2.part1.ParsedRule;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Application extends nl.bobbeldijk.day2.part1.Application {

    public static void main(String[] args) {
        System.out.println((new Application()).calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY2)));
    }

    @Override
    protected boolean checkParsedRule(ParsedRule parsedRule) {
        IntPredicate isLetterAtIndex = (int i) -> parsedRule.getLettersToCheck().charAt(i - 1) == parsedRule.getLetterToFind();

        return IntStream.of(parsedRule.getBeginNumber(), parsedRule.getEndNumber())
                .filter(isLetterAtIndex)
                .count() == 1;
    }
}
