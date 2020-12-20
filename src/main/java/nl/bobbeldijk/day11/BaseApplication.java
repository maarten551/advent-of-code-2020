package nl.bobbeldijk.day11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BaseApplication {
    protected static final int[][] relativePositionsToCheck = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1},
    };

    protected CoordinateState[][] seatMap;
    private boolean changeFound = false;


    public BaseApplication(List<String> input) {
        this.seatMap = parseInput(input);
    }

    public CoordinateState[][] runTillRound(int maxRound) {
        for (int i = 0; i < maxRound; i++) {
            seatMap = IntStream.range(0, seatMap.length)
                    .parallel()
                    .mapToObj(y -> IntStream.range(0, seatMap[y].length)
                            .mapToObj(x -> determineSeatState(y, x))
                            .toArray(CoordinateState[]::new)
                    )
                    .toArray(CoordinateState[][]::new);
        }

        return seatMap;
    }

    public long runTillThereIsNoChange() {
        do {
            changeFound = false;

            seatMap = IntStream.range(0, seatMap.length)
                    .parallel()
                    .mapToObj(y -> IntStream.range(0, seatMap[y].length)
                            .mapToObj(x -> {
                                final var coordinateState = determineSeatState(y, x);
                                if (!changeFound) {
                                    changeFound = seatMap[y][x] != coordinateState;
                                }

                                return coordinateState;
                            })
                            .toArray(CoordinateState[]::new)
                    )
                    .toArray(CoordinateState[][]::new);

        } while (changeFound);

        return Arrays.stream(seatMap)
                .flatMap(Arrays::stream)
                .filter(CoordinateState.SEATED::equals)
                .count();
    }

    protected long countAmountOfSeatedCharsAroundSeat(int y, int x) {
        return Arrays.stream(relativePositionsToCheck)
                .filter(c -> y + c[0] >= 0)
                .filter(c -> x + c[1] >= 0)
                .filter(c -> y + c[0] < seatMap.length)
                .filter(c -> x + c[1] < seatMap[y].length)
                .filter(c -> seatMap[y + c[0]][x + c[1]] == CoordinateState.SEATED)
                .count();
    }

    /**
     * - If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
     * - If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
     * - Otherwise, the seat's state does not change.
     */
    protected CoordinateState determineSeatState(int y, int x) {
        if (seatMap[y][x] == CoordinateState.FLOOR) {
            return seatMap[y][x];
        }

        switch ((int) countAmountOfSeatedCharsAroundSeat(y, x)) {
            case 0:
                return CoordinateState.SEATED;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return CoordinateState.EMPTY;
            default:
                return seatMap[y][x];
        }
    }

    private CoordinateState[][] parseInput(List<String> input) {
        return input.stream()
                .map(inputLine -> inputLine.chars()
                        .mapToObj(CoordinateState::mapCharToState)
                        .toArray(CoordinateState[]::new))
                .toArray(CoordinateState[][]::new);
    }
}
