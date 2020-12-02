package nl.bobbeldijk.day1.part1;

import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.Answerable;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.util.List;

public class Application implements Answerable<Integer> {

    public static final int WANTED_SUM = 2020;

    public static void main(String[] args) throws AnswerNotFoundException {
        (new Application()).calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY1));
    }

    @Override
    public Integer calculateAnswer(List<String> input) throws AnswerNotFoundException {
        var inputNumbers = input.stream().mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < inputNumbers.length; i++) {
            var firstNumber = inputNumbers[i];

            for (int j = i + 1; j < inputNumbers.length; j++) {
                var secondNumber = inputNumbers[j];

                if (firstNumber + secondNumber == WANTED_SUM) {
                    System.out.printf("%d + %d = %d %n%n", firstNumber, secondNumber, WANTED_SUM);
                    System.out.printf("Answer: %d %n", firstNumber * secondNumber);

                    return firstNumber * secondNumber;
                }
            }
        }

        throw new AnswerNotFoundException(String.format("No 2 numbers found that are the sum of %d", WANTED_SUM));
    }
}
