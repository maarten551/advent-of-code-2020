package nl.bobbeldijk.day8.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class Command {
    private static final List<Function<String, Optional<Command>>> PARSEABLE_COMMANDS = new ArrayList<>();

    static {
        PARSEABLE_COMMANDS.add(AccCommand::parseFromString);
        PARSEABLE_COMMANDS.add(JmpCommand::parseFromString);
        PARSEABLE_COMMANDS.add(NopCommand::parseFromString);
    }

    private int amountOfTimesExecuted = 0;

    public static Optional<Command> parseInputToCommand(String input) {
        return PARSEABLE_COMMANDS.stream()
                .map(c -> c.apply(input))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    public void execute() {
        amountOfTimesExecuted++;
    }

    public void resetAmountOfTimesExecuted() {
        amountOfTimesExecuted = 0;
    }

    public int getAmountOfTimesExecuted() {
        return amountOfTimesExecuted;
    }
}
