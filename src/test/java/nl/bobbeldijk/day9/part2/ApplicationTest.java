package nl.bobbeldijk.day9.part2;

import nl.bobbeldijk.day9.BaseApplication;
import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

class ApplicationTest {

    private BaseApplication baseApplication;

    @BeforeEach
    void setUp() {
        baseApplication = new Application(5);
    }

    @Test
    void calculateAnswerTest() throws AnswerNotFoundException {
        var input = InputReader.readStreamFromInputFile(InputFile.DAY9, true, "test").collect(Collectors.toList());

        Assertions.assertEquals(62, baseApplication.calculateAnswer(input));
    }

    @Test
    void calculateAnswerShouldNotFindAnswerTest() {
        var input = InputReader.readStreamFromInputFile(InputFile.DAY9, true, "test")
                .takeWhile(s -> !s.equals("127"))
                .collect(Collectors.toList());

        Assertions.assertThrows(AnswerNotFoundException.class, () -> baseApplication.calculateAnswer(input));
    }
}