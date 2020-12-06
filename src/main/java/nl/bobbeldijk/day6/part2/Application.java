package nl.bobbeldijk.day6.part2;

import nl.bobbeldijk.day6.InputParser;
import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.Answerable;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.util.List;

public class Application implements Answerable<Integer> {
    public static void main(String[] args) throws AnswerNotFoundException {
        System.out.println(new Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY6)));
    }

    @Override
    public Integer calculateAnswer(List<String> input) throws AnswerNotFoundException {
        var groups = (new InputParser()).parseInput(input);

        return (int) groups.stream()
                .mapToLong(group -> new String(group.passengers.get(0).answeredYesToQuestions).chars()
                        .filter(c -> group.passengers.stream()
                                .allMatch(p -> new String(p.answeredYesToQuestions).chars().anyMatch(c2 -> c == c2))
                        )
                        .count()
                )
                .sum();
    }
}
