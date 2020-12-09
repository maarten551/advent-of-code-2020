package nl.bobbeldijk.day9.part2;

import nl.bobbeldijk.day9.BaseApplication;
import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.util.List;
import java.util.stream.IntStream;

public class Application extends BaseApplication {
    public static void main(String[] args) throws AnswerNotFoundException {
        System.out.println(new Application(25).calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY9)));
    }

    public Application(int amountOfPreambleNumbers) {
        super(amountOfPreambleNumbers);
    }

    @Override
    public Long calculateAnswer(List<String> input) throws AnswerNotFoundException {
        var invalidNumber = super.calculateAnswer(input);

        return IntStream.range(0, inputNumbers.length).parallel()
                .mapToLong(i -> {
                    for (int j = i + 1; j < inputNumbers.length; j++) {
                        var sum = IntStream.rangeClosed(i, j)
                                .mapToLong(l -> inputNumbers[l])
                                .sum();

                        if (sum == invalidNumber) {
                            var min = IntStream.rangeClosed(i, j).mapToLong(l -> inputNumbers[l]).min();
                            var max = IntStream.rangeClosed(i, j).mapToLong(l -> inputNumbers[l]).max();

                            return min.getAsLong() + max.getAsLong();
                        }

                        if (sum > invalidNumber) {
                            return 0;
                        }
                    }

                    return 0;
                })
                .filter(l -> l != 0)
                .findAny()
                .orElseThrow(() -> new AnswerNotFoundException(String.format("Found the invalid number '%d', but could not find the sum", invalidNumber)));
    }
}
