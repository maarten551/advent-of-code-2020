package nl.bobbeldijk.day8.part2;

import nl.bobbeldijk.day8.VirtualContext;
import nl.bobbeldijk.day8.command.AccCommand;
import nl.bobbeldijk.day8.command.Command;
import nl.bobbeldijk.day8.command.JmpCommand;
import nl.bobbeldijk.day8.command.NopCommand;
import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.Answerable;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Application implements Answerable<Integer> {
    public static void main(String[] args) throws AnswerNotFoundException {
        System.out.println(new Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY8)));
    }

    @Override
    public Integer calculateAnswer(List<String> input) throws AnswerNotFoundException {
        var commands = input.stream().map(Command::parseInputToCommand)
                .map(Optional::get)
                .collect(Collectors.toList());

        for (int i = 0; i < commands.size(); i++) {
            Command oldCommand = commands.get(i);
            if (oldCommand.getClass().equals(AccCommand.class)) {
                continue;
            }

            Command newCommand;

            if (commands.get(i).getClass().equals(NopCommand.class)) {
                newCommand = new JmpCommand(((NopCommand) oldCommand).getParameter());
            } else {
                newCommand = new NopCommand(((JmpCommand) oldCommand).getParameter());
            }

            resetVirtualContext();
            commands.set(i, newCommand);

            if (runsProgramSuccessfully(commands)) {
                return VirtualContext.ACC_REGISTER;
            }

            commands.set(i, oldCommand);
        }

        throw new AnswerNotFoundException("Could not find a program that runs successfully");
    }

    private boolean runsProgramSuccessfully(List<Command> commands) {
        commands.forEach(Command::resetAmountOfTimesExecuted);
        while (VirtualContext.INSTRUCTION_POINTER < commands.size()) {
            var command = commands.get(VirtualContext.INSTRUCTION_POINTER);
            if (command.getAmountOfTimesExecuted() >= 1) {
                return false;
            }

            command.execute();

            if (!command.getClass().equals(JmpCommand.class)) {
                VirtualContext.INSTRUCTION_POINTER++;
            }
        }

        return true;
    }

    private void resetVirtualContext() {
        VirtualContext.ACC_REGISTER = 0;
        VirtualContext.INSTRUCTION_POINTER = 0;
    }
}
