package nl.bobbeldijk.day7.part1;

import nl.bobbeldijk.day7.Bag;
import nl.bobbeldijk.day7.InputParser;
import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.Answerable;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.util.List;

public class Application implements Answerable<Integer> {
    public static final String WANTED_BAG = "shiny gold";

    public static void main(String[] args) throws AnswerNotFoundException {
        System.out.println(new Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY7)));
    }

    @Override
    public Integer calculateAnswer(List<String> input) throws AnswerNotFoundException {
        return (int) new InputParser().parseInput(input).values().stream()
                .filter(this::canContainGoldBag)
                .count();
    }

    private boolean canContainGoldBag(Bag bag) {
        return bag.getRules().parallelStream()
                .anyMatch(bagRule -> {
                    if (bagRule.getBag().getType().equals(WANTED_BAG)) {
                        return true;
                    }

                    return this.canContainGoldBag(bagRule.getBag());
                });
    }
}
