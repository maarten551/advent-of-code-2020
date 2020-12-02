package nl.bobbeldijk.day1.part1;

import nl.bobbeldijk.util.AnswerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {

    public static final List<String> INPUT = List.of("1721", "979", "366", "299", "675", "1456");

    private Application application;

    @BeforeEach
    void setUp() {
        application = new Application();
    }

    @Test
    void calculateAnswerTest() throws AnswerNotFoundException {
        assertEquals(514579, application.calculateAnswer(INPUT));
    }
}