package nl.bobbeldijk.day8.command;

import java.util.Optional;
import java.util.regex.Pattern;

public class NopCommand extends Command {
    public static final Pattern parsePattern = Pattern.compile("^nop ([+-])(\\d+)$");

    public static Optional<Command> parseFromString(String input) {
        var matcher = parsePattern.matcher(input);

        if (!matcher.matches()) {
            return Optional.empty();
        }

        if (matcher.group(1).equals("+")) {
            return Optional.of(new NopCommand(Integer.parseInt(matcher.group(2))));
        } else {
            return Optional.of(new NopCommand(-Integer.parseInt(matcher.group(2))));
        }
    }

    private final int parameter;

    public NopCommand(int parameter) {
        this.parameter = parameter;
    }

    @Override
    public void execute() {
        super.execute();
    }

    public int getParameter() {
        return parameter;
    }
}
