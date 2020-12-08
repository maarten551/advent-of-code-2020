package nl.bobbeldijk.day8.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JmpCommandTest {

    @Test
    void parseFromStringNeutralTest() {
        var command = JmpCommand.parseFromString("jmp +0");

        Assertions.assertTrue(command.isPresent());
        Assertions.assertEquals(JmpCommand.class, command.get().getClass());
        Assertions.assertEquals(0, ((JmpCommand) command.get()).getParameter());
    }

    @Test
    void parseFromStringPositiveTest() {
        var command = JmpCommand.parseFromString("jmp +20");

        Assertions.assertTrue(command.isPresent());
        Assertions.assertEquals(JmpCommand.class, command.get().getClass());
        Assertions.assertEquals(20, ((JmpCommand) command.get()).getParameter());
    }

    @Test
    void parseFromStringNegativeTest() {
        var command = JmpCommand.parseFromString("jmp -52");

        Assertions.assertTrue(command.isPresent());
        Assertions.assertEquals(JmpCommand.class, command.get().getClass());
        Assertions.assertEquals(-52, ((JmpCommand) command.get()).getParameter());
    }

    @Test
    void parseFromStringShouldNotParseTest() {
        var command = JmpCommand.parseFromString("jmp 52");

        Assertions.assertFalse(command.isPresent());
    }
}