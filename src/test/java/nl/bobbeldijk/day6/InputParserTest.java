package nl.bobbeldijk.day6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class InputParserTest {

    private InputParser inputParser;

    @BeforeEach
    void setUp() {
        inputParser = new InputParser();
    }

    @Test
    void singlePersonInGroupTest() {
        var groups = inputParser.parseInput(Collections.singletonList("abc"));

        Assertions.assertEquals(1, groups.size());
        Assertions.assertEquals(1, groups.get(0).passengers.size());
        Assertions.assertArrayEquals(new char[]{'a', 'b', 'c'}, groups.get(0).passengers.get(0).answeredYesToQuestions);
    }

    @Test
    void multiplePersonInGroupTest1() {
        var groups = inputParser.parseInput(Arrays.asList("a", "b", "c"));

        Assertions.assertEquals(1, groups.size());
        Assertions.assertEquals(3, groups.get(0).passengers.size());
        Assertions.assertArrayEquals(new char[]{'a'}, groups.get(0).passengers.get(0).answeredYesToQuestions);
        Assertions.assertArrayEquals(new char[]{'b'}, groups.get(0).passengers.get(1).answeredYesToQuestions);
        Assertions.assertArrayEquals(new char[]{'c'}, groups.get(0).passengers.get(2).answeredYesToQuestions);
    }

    @Test
    void multiplePersonInGroupTest2() {
        var groups = inputParser.parseInput(Arrays.asList("ab", "ac"));

        Assertions.assertEquals(1, groups.size());
        Assertions.assertEquals(2, groups.get(0).passengers.size());
        Assertions.assertArrayEquals(new char[]{'a', 'b'}, groups.get(0).passengers.get(0).answeredYesToQuestions);
        Assertions.assertArrayEquals(new char[]{'a', 'c'}, groups.get(0).passengers.get(1).answeredYesToQuestions);
    }

    @Test
    void multiplePersonsInMultipleGroupsTest2() {
        var groups = inputParser.parseInput(Arrays.asList("ab", "bc", "", "cd", "gh", "po"));

        Assertions.assertEquals(2, groups.size());
        Assertions.assertEquals(2, groups.get(0).passengers.size());
        Assertions.assertEquals(3, groups.get(1).passengers.size());
        Assertions.assertArrayEquals(new char[]{'a', 'b'}, groups.get(0).passengers.get(0).answeredYesToQuestions);
        Assertions.assertArrayEquals(new char[]{'b', 'c'}, groups.get(0).passengers.get(1).answeredYesToQuestions);

        Assertions.assertArrayEquals(new char[]{'c', 'd'}, groups.get(1).passengers.get(0).answeredYesToQuestions);
        Assertions.assertArrayEquals(new char[]{'g', 'h'}, groups.get(1).passengers.get(1).answeredYesToQuestions);
        Assertions.assertArrayEquals(new char[]{'p', 'o'}, groups.get(1).passengers.get(2).answeredYesToQuestions);
    }
}