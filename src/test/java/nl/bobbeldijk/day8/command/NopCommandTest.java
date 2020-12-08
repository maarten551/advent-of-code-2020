package nl.bobbeldijk.day8.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NopCommandTest {

    @Test
    void parseFromStringNeutralTest() {
        var command = NopCommand.parseFromString("nop +0");

        Assertions.assertTrue(command.isPresent());
        Assertions.assertEquals(NopCommand.class, command.get().getClass());
        Assertions.assertEquals(0, ((NopCommand) command.get()).getParameter());
    }

    @Test
    void parseFromStringPositiveTest() {
        var command = NopCommand.parseFromString("nop +20");

        Assertions.assertTrue(command.isPresent());
        Assertions.assertEquals(NopCommand.class, command.get().getClass());
        Assertions.assertEquals(20, ((NopCommand) command.get()).getParameter());
    }

    @Test
    void parseFromStringNegativeTest() {
        var command = NopCommand.parseFromString("nop -52");

        Assertions.assertTrue(command.isPresent());
        Assertions.assertEquals(NopCommand.class, command.get().getClass());
        Assertions.assertEquals(-52, ((NopCommand) command.get()).getParameter());
    }

    @Test
    void parseFromStringShouldNotParseTest() {
        var command = NopCommand.parseFromString("nop 52");

        Assertions.assertFalse(command.isPresent());
    }
}