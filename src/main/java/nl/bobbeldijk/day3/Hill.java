package nl.bobbeldijk.day3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Hill {
    private List<List<HillCoordinateItem>> coordinates;

    public static Hill generate(List<String> input) {
        var hill = new Hill();

        hill.coordinates = input.stream()
                .map(String::chars)
                .map(a -> a.mapToObj(coordinate ->
                                Arrays.stream(HillCoordinateItem.values())
                                        .filter(h -> h.getMappedFrom() == coordinate)
                                        .findFirst()
                                        .get()
                        ).collect(Collectors.toList())
                ).collect(Collectors.toList());

        return hill;
    }

    private Hill() {
    }

    public HillCoordinateItem getItemOnCoordinates(int y, int x) throws BottomReachedException {
        if (y >= coordinates.size()) {
            throw new BottomReachedException();
        }

        return coordinates.get(y).get(x % coordinates.get(y).size());
    }
}
