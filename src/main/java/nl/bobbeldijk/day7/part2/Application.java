package nl.bobbeldijk.day7.part2;

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
        return countAmountOfBagsNeeded(new InputParser().parseInput(input).get(WANTED_BAG));
    }

    private int countAmountOfBagsNeeded(Bag bag) {
        return bag.getRules().stream()
                .mapToInt(rule -> {
                    var neededBagsInside = this.countAmountOfBagsNeeded(rule.getBag());

                    if (neededBagsInside == 0) {
                        return rule.getAmountOfBags();
                    }

                    return rule.getAmountOfBags() + (rule.getAmountOfBags() * neededBagsInside);
                })
                .sum();
    }
}
