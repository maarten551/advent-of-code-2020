package nl.bobbeldijk.day9;

import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.Answerable;

import java.util.List;
import java.util.stream.IntStream;

public class BaseApplication implements Answerable<Long> {
    protected final int amountOfPreambleNumbers;
    protected Long[] inputNumbers;

    public BaseApplication(int amountOfPreambleNumbers) {
        this.amountOfPreambleNumbers = amountOfPreambleNumbers;
    }

    @Override
    public Long calculateAnswer(List<String> input) throws AnswerNotFoundException {
        inputNumbers = input.stream().map(Long::parseLong).toArray(Long[]::new);

        for (int i = amountOfPreambleNumbers; i < inputNumbers.length; i++) {
            if (!hasSumWithinPreamble(i, inputNumbers)) {
                return inputNumbers[i];
            }
        }

        throw new AnswerNotFoundException("Could not find any faults in the numbers");
    }

    private boolean hasSumWithinPreamble(int currentIndex, Long[] inputNumbers) {
        return IntStream.range(currentIndex - amountOfPreambleNumbers, currentIndex)
                .parallel()
                .anyMatch(i1 ->
                        IntStream.range(i1 + 1, currentIndex).anyMatch(i2 ->
                                inputNumbers[i1] + inputNumbers[i2] == inputNumbers[currentIndex]
                        )
                );
    }
}
