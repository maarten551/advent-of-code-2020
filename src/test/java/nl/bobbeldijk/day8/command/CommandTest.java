package nl.bobbeldijk.day8.command;

import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Collectors;

class CommandTest {
    @Test
    void commandTestFileTest() {
        var parsedCommands = InputReader.readStreamFromInputFile(InputFile.DAY8, true, "test")
                .map(Command::parseInputToCommand)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        Assertions.assertEquals(9, parsedCommands.size());
        Assertions.assertEquals(1, parsedCommands.stream().filter(c -> NopCommand.class.equals(c.getClass())).count());
        Assertions.assertEquals(3, parsedCommands.stream().filter(c -> JmpCommand.class.equals(c.getClass())).count());
        Assertions.assertEquals(5, parsedCommands.stream().filter(c -> AccCommand.class.equals(c.getClass())).count());
    }
}