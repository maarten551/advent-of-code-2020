package nl.bobbeldijk.day6;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InputParser {
    public List<PassengerGroup> parseInput(List<String> input) {
        List<PassengerGroup> groups = new ArrayList<>();

        Optional<PassengerGroup> currentGroup = Optional.empty();

        for (String line : input) {
            if (line.trim().length() == 0 && currentGroup.isPresent()) {
                groups.add(currentGroup.get());
                currentGroup = Optional.empty();

                continue;
            }

            if (currentGroup.isEmpty()) {
                currentGroup = Optional.of(new PassengerGroup());
            }

            currentGroup.get().passengers.add(new Passenger(line.toCharArray()));
        }

        currentGroup.ifPresent(groups::add);

        return groups;
    }
}
