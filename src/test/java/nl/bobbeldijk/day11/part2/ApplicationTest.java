package nl.bobbeldijk.day11.part2;

import nl.bobbeldijk.day11.CoordinateState;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Collectors;

class ApplicationTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void runTillRoundTest(int round) {
        var input = InputReader.readStreamFromInputFile(InputFile.DAY11, true, "test").collect(Collectors.toList());
        var baseApplication = new Application(input);

        Assertions.assertArrayEquals(loadExpectedResult(round), baseApplication.runTillRound(round));
    }

    @Test
    void runTillNoChangeFoundTest() {
        var input = InputReader.readStreamFromInputFile(InputFile.DAY11, true, "test").collect(Collectors.toList());
        var baseApplication = new Application(input);

        Assertions.assertEquals(26, baseApplication.runTillThereIsNoChange());
    }

    private CoordinateState[][] loadExpectedResult(int round) {
        var filePrefix = String.format("test-result-part2-round%d", round);

        var input = InputReader.readStreamFromInputFile(InputFile.DAY11, true, filePrefix).collect(Collectors.toList());

        return new Application(input).runTillRound(0);
    }
}