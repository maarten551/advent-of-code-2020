package nl.bobbeldijk.day8.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccCommandTest {

    @Test
    void parseFromStringNeutralTest() {
        var command = AccCommand.parseFromString("acc +0");

        Assertions.assertTrue(command.isPresent());
        Assertions.assertEquals(AccCommand.class, command.get().getClass());
        Assertions.assertEquals(0, ((AccCommand) command.get()).getParameter());
    }

    @Test
    void parseFromStringPositiveTest() {
        var command = AccCommand.parseFromString("acc +20");

        Assertions.assertTrue(command.isPresent());
        Assertions.assertEquals(AccCommand.class, command.get().getClass());
        Assertions.assertEquals(20, ((AccCommand) command.get()).getParameter());
    }

    @Test
    void parseFromStringNegativeTest() {
        var command = AccCommand.parseFromString("acc -52");

        Assertions.assertTrue(command.isPresent());
        Assertions.assertEquals(AccCommand.class, command.get().getClass());
        Assertions.assertEquals(-52, ((AccCommand) command.get()).getParameter());
    }

    @Test
    void parseFromStringShouldNotParseTest() {
        var command = AccCommand.parseFromString("acc 52");

        Assertions.assertFalse(command.isPresent());
    }
}