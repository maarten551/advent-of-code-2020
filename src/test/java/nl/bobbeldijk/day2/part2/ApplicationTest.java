package nl.bobbeldijk.day2.part2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {

    private Application application;

    @BeforeEach
    void setUp() {
        application = new Application();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1-3 a: abcde", "1-4 b: cdebb"})
    void calculateGoodAnswersTest(String input) {
        assertEquals(1, application.calculateAnswer(List.of(input)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1-3 b: cdefg", "2-9 c: ccccccccc", "1-3 b: cdebb", "1-4 b: cdbeb"})
    void calculateWrongAnswersTest(String input) {
        assertEquals(0, application.calculateAnswer(List.of(input)));
    }
}