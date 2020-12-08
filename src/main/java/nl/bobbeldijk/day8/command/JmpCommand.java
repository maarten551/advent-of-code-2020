package nl.bobbeldijk.day8.command;

import nl.bobbeldijk.day8.VirtualContext;

import java.util.Optional;
import java.util.regex.Pattern;

public class JmpCommand extends Command {
    public static final Pattern parsePattern = Pattern.compile("^jmp ([+-])(\\d+)$");

    public static Optional<Command> parseFromString(String input) {
        var matcher = parsePattern.matcher(input);

        if (!matcher.matches()) {
            return Optional.empty();
        }

        if (matcher.group(1).equals("+")) {
            return Optional.of(new JmpCommand(Integer.parseInt(matcher.group(2))));
        } else {
            return Optional.of(new JmpCommand(-Integer.parseInt(matcher.group(2))));
        }
    }

    private final int parameter;

    public JmpCommand(int parameter) {
        this.parameter = parameter;
    }

    @Override
    public void execute() {
        super.execute();

        VirtualContext.INSTRUCTION_POINTER += parameter;
    }

    public int getParameter() {
        return parameter;
    }
}
