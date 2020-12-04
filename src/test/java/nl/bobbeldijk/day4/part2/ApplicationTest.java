package nl.bobbeldijk.day4.part2;

import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {
    @Test
    void examplePositiveTest() throws AnswerNotFoundException {
        var testInput = InputReader.readStreamFromInputFile(InputFile.DAY4, true, "test-valid")
                .collect(Collectors.toList());

        var application = new Application();

        assertEquals(4, application.calculateAnswer(testInput));
    }

    @Test
    void exampleNegativeTest() throws AnswerNotFoundException {
        var testInput = InputReader.readStreamFromInputFile(InputFile.DAY4, true, "test-invalid")
                .collect(Collectors.toList());

        var application = new Application();

        assertEquals(0, application.calculateAnswer(testInput));
    }
}