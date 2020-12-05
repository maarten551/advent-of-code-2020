package nl.bobbeldijk.day5.part1;

import nl.bobbeldijk.day5.Seat;
import nl.bobbeldijk.day5.SeatParser;
import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.Answerable;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.util.List;

public class Application implements Answerable<Integer> {
    public static void main(String[] args) throws AnswerNotFoundException {
        var application = new Application();

        System.out.println(application.calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY5)));
    }

    @Override
    public Integer calculateAnswer(List<String> input) throws AnswerNotFoundException {
        var seatParser = new SeatParser();

        return input.stream()
                .map(seatParser::parseSeatInput)
                .mapToInt(Seat::getId)
                .max()
                .getAsInt();
    }
}
