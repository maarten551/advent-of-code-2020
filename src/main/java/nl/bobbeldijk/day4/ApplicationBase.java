package nl.bobbeldijk.day4;

import nl.bobbeldijk.util.Answerable;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

public abstract class ApplicationBase implements Answerable<Long> {
    protected List<EnumMap<Field, String>> parsedPassports;

    protected void parseInput(List<String> input) {
        parsedPassports = new ArrayList<>();
        Optional<EnumMap<Field, String>> currentPassport = Optional.empty();

        for (String line : input) {
            if (line.trim().length() == 0 && currentPassport.isPresent()) {
                parsedPassports.add(currentPassport.get());
                currentPassport = Optional.empty();

                continue;
            }

            if (currentPassport.isEmpty()) {
                currentPassport = Optional.of(new EnumMap<>(Field.class));
            }

            for (String unparsedField : line.split(" ")) {
                var fieldParts = unparsedField.split(":");

                currentPassport.get().put(Field.valueOf(fieldParts[0].toUpperCase()), fieldParts[1]);
            }
        }

        currentPassport.ifPresent(parsedPassports::add);
    }
}
