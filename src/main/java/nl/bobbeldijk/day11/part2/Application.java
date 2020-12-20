package nl.bobbeldijk.day11.part2;

import nl.bobbeldijk.day11.BaseApplication;
import nl.bobbeldijk.day11.CoordinateState;
import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.Answerable;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Application extends BaseApplication implements Answerable<Long> {
    public static void main(String[] args) throws AnswerNotFoundException {
        Instant starts = Instant.now();

        System.out.println(new Application(InputReader.readListFromInputFile(InputFile.DAY11)).calculateAnswer(null));

        System.out.println(Duration.between(starts, Instant.now()));
    }

    public Application(List<String> input) {
        super(input);
    }

    @Override
    public Long calculateAnswer(List<String> input) throws AnswerNotFoundException {
        return this.runTillThereIsNoChange();
    }

    @Override
    protected long countAmountOfSeatedCharsAroundSeat(int y, int x) {
        return Arrays.stream(relativePositionsToCheck)
                .flatMap(oc ->
                        IntStream.range(1, 95)
                                .mapToObj(i -> new int[]{oc[0] * i, oc[1] * i})
                                .filter(c -> y + c[0] >= 0)
                                .filter(c -> x + c[1] >= 0)
                                .filter(c -> y + c[0] < seatMap.length)
                                .filter(c -> x + c[1] < seatMap[y].length)
                                .filter(c -> seatMap[y + c[0]][x + c[1]] != CoordinateState.FLOOR)
                                .findFirst()
                                .stream()
                )
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
            case 5:
            case 6:
            case 7:
            case 8:
                return CoordinateState.EMPTY;
            default:
                return seatMap[y][x];
        }
    }
}
