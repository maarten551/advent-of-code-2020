package nl.bobbeldijk.day7.part2;

import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.Answerable;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

class ApplicationTest {

    private Answerable<Integer> application;

    @BeforeEach
    void setUp() {
        application = new Application();
    }

    @Test
    void calculateAnswerPart1Test() throws AnswerNotFoundException {
        var input = InputReader.readStreamFromInputFile(InputFile.DAY7, true, "test").collect(Collectors.toList());

        Assertions.assertEquals(32, application.calculateAnswer(input));
    }

    @Test
    void calculateAnswerPart2Test() throws AnswerNotFoundException {
        var input = InputReader.readStreamFromInputFile(InputFile.DAY7, true, "test-part2").collect(Collectors.toList());

        Assertions.assertEquals(126, application.calculateAnswer(input));
    }
}