package nl.bobbeldijk.day8.part1;

import nl.bobbeldijk.day8.VirtualContext;
import nl.bobbeldijk.day8.command.Command;
import nl.bobbeldijk.day8.command.JmpCommand;
import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.Answerable;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.util.List;
import java.util.stream.Collectors;

public class Application implements Answerable<Integer> {
    public static void main(String[] args) throws AnswerNotFoundException {
        System.out.println(new Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY8)));
    }

    @Override
    public Integer calculateAnswer(List<String> input) throws AnswerNotFoundException {
        var commands = input.stream().map(Command::parseInputToCommand).collect(Collectors.toList());

        while (VirtualContext.INSTRUCTION_POINTER < commands.size()) {
            var command = commands.get(VirtualContext.INSTRUCTION_POINTER).get();
            if (command.getAmountOfTimesExecuted() >= 1) {
                break;
            }

            command.execute();

            if (!command.getClass().equals(JmpCommand.class)) {
                VirtualContext.INSTRUCTION_POINTER++;
            }
        }

        return VirtualContext.ACC_REGISTER;
    }
}
