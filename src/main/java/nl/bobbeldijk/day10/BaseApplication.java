package nl.bobbeldijk.day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class BaseApplication {
    protected int[] inputNumbers;

    public BaseApplication(List<String> input) {
        this.inputNumbers = input.stream()
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
    }

    public Map<Integer, Integer> calculateJoltageDifferences() {
        var differences = new HashMap<>(Map.of(1, 0, 2, 0, 3, 1));

        int previousNumber = 0;
        for (int inputNumber : inputNumbers) {
            var difference = inputNumber - previousNumber;

            if (difference > 3) {
                throw new Error(String.format("Difference between %d and %d is larger than 3", previousNumber, inputNumber));
            }

            differences.computeIfPresent(difference, (i1, i2) -> ++i2);
            previousNumber = inputNumber;
        }

        return differences;
    }

    public Long calculateAmountOfPossibleConfigurationsFast() {
        var segments = breakIntoSegments();
        segments.get(0).add(0, 0);

        long[] segmentTotal = new long[segments.size()];

        for (int i = 0; i < segmentTotal.length; i++) {
            if (segments.get(i).size() == 1) {
                segmentTotal[i] = 1L;
                continue;
            }
            var array = segments.get(i).stream().mapToInt(Integer::intValue).toArray();

            segmentTotal[i] = calculateAmountOfPossibleConfigurationsRecursive(array);
        }

        return LongStream.of(segmentTotal).reduce(1, (left, right) -> left * right);
    }

    public List<List<Integer>> breakIntoSegments() {
        var segments = new ArrayList<List<Integer>>();

        List<Integer> currentSegment = new ArrayList<>();
        currentSegment.add(inputNumbers[0]);
        for (int i = 1; i < inputNumbers.length; i++) {
            if (inputNumbers[i] - inputNumbers[i - 1] >= 3) {
                segments.add(currentSegment);

                currentSegment = new ArrayList<>();
                currentSegment.add(inputNumbers[i]);

                continue;
            }

            currentSegment.add(inputNumbers[i]);
        }

        segments.add(currentSegment);

        return segments;
    }

    /**
     * Old solution, very slow, but still cool
     */
    public Long calculateAmountOfPossibleConfigurationsRecursive() {
        return recursiveCalculateAmountOfPossibleConfigurations(inputNumbers, (byte) -1, (byte) (inputNumbers.length - 1))
                .count();
    }

    private Long calculateAmountOfPossibleConfigurationsRecursive(int[] array) {
        return recursiveCalculateAmountOfPossibleConfigurations(array, (byte) 0, (byte) (array.length - 1))
                .count();
    }

    public IntStream recursiveCalculateAmountOfPossibleConfigurations(byte beginIndex, byte endIndex) {
        return recursiveCalculateAmountOfPossibleConfigurations(inputNumbers, beginIndex, endIndex);
    }

    /**
     * Old solution, but refactored to also work with segmented arrays
     */
    private IntStream recursiveCalculateAmountOfPossibleConfigurations(int[] array, byte beginIndex, byte endIndex) {
        if (array.length == 1) {
            return IntStream.empty();
        }

        if (beginIndex == endIndex) {
            return IntStream.of(1);
        }

        final int beginNumber = (beginIndex < 0) ? 0 : array[beginIndex];

        return IntStream.rangeClosed(1, 3)
                .parallel()
                .filter(i -> beginIndex + i <= endIndex)
                .filter(i -> array[beginIndex + i] - beginNumber <= 3)
                .flatMap(i -> recursiveCalculateAmountOfPossibleConfigurations(array, (byte) (beginIndex + i), endIndex));
    }
}
