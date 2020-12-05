package nl.bobbeldijk.day5.part1;

import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {

    private Application application;

    @BeforeEach
    void setUp() {
        application = new Application();
    }

    @Test
    void calculateAnswer() throws AnswerNotFoundException {
        var input = InputReader.readStreamFromInputFile(InputFile.DAY5, true, "test").collect(Collectors.toList());

        assertEquals(820, application.calculateAnswer(input));
    }
}