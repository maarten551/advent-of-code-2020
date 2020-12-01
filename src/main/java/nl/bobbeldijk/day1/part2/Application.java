package nl.bobbeldijk.day1.part2;

import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

public class Application {

    public static final int WANTED_SUM = 2020;

    public static void main(String[] args) {
        var inputNumbers = InputReader.readStreamFromInputFile(InputFile.DAY1).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < inputNumbers.length; i++) {
            var firstNumber = inputNumbers[i];

            for (int j = i + 1; j < inputNumbers.length; j++) {
                var secondNumber = inputNumbers[j];

                for (int k = j + 1; k < inputNumbers.length; k++) {
                    var thirdNumber = inputNumbers[k];

                    if (firstNumber + secondNumber + thirdNumber == WANTED_SUM) {
                        System.out.printf("%d + %d + %d = %d %n%n", firstNumber, secondNumber, thirdNumber, WANTED_SUM);
                        System.out.printf("Answer: %d %n", firstNumber * secondNumber * thirdNumber);

                        return;
                    }
                }
            }
        }
    }
}
