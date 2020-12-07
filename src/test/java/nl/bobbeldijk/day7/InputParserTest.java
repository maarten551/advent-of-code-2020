package nl.bobbeldijk.day7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class InputParserTest {

    private InputParser inputParser;

    @BeforeEach
    void setUp() {
        inputParser = new InputParser();
    }

    @Test
    void parseInputTest1() {
        List<String> input = Collections.singletonList("light red bags contain 1 bright white bag, 2 muted yellow bags.");

        var result = inputParser.parseInput(input);

        Assertions.assertEquals(3, result.size());

        Assertions.assertTrue(result.containsKey("light red"));
        Assertions.assertEquals("light red", result.get("light red").getType());
        Assertions.assertEquals(2, result.get("light red").getRules().size());
        Assertions.assertEquals(1, result.get("light red").getRules().get(0).getAmountOfBags());
        Assertions.assertEquals("bright white", result.get("light red").getRules().get(0).getBag().getType());
        Assertions.assertEquals(result.get("bright white"), result.get("light red").getRules().get(0).getBag());
        
        Assertions.assertEquals(2, result.get("light red").getRules().get(1).getAmountOfBags());
        Assertions.assertEquals("muted yellow", result.get("light red").getRules().get(1).getBag().getType());
        Assertions.assertEquals(result.get("muted yellow"), result.get("light red").getRules().get(1).getBag());

        Assertions.assertTrue(result.containsKey("bright white"));
        Assertions.assertEquals("bright white", result.get("bright white").getType());
        Assertions.assertEquals(0, result.get("bright white").getRules().size());

        Assertions.assertTrue(result.containsKey("muted yellow"));
        Assertions.assertEquals("muted yellow", result.get("muted yellow").getType());
        Assertions.assertEquals(0, result.get("muted yellow").getRules().size());
    }
}