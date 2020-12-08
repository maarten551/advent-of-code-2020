package nl.bobbeldijk.day8.part1;

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
    void calculateAnswerTest() throws AnswerNotFoundException {
        var input = InputReader.readStreamFromInputFile(InputFile.DAY8, true, "test").collect(Collectors.toList());

        Assertions.assertEquals(5, application.calculateAnswer(input));
    }
}