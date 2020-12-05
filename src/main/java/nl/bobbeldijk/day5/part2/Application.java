package nl.bobbeldijk.day5.part2;

import nl.bobbeldijk.day5.Seat;
import nl.bobbeldijk.day5.SeatParser;
import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.Answerable;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Application implements Answerable<Integer> {
    public static void main(String[] args) throws AnswerNotFoundException {
        var application = new Application();

        System.out.println(application.calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY5)));
    }

    @Override
    public Integer calculateAnswer(List<String> input) throws AnswerNotFoundException {
        var seatParser = new SeatParser();
        var seatsInUse = input.stream()
                .map(seatParser::parseSeatInput)
                .map(Seat::getId)
                .collect(Collectors.toList());

        return IntStream.rangeClosed(127, (1024 - 128))
                .filter(i -> !seatsInUse.contains(i))
                .filter(i -> seatsInUse.contains(i + 1) && seatsInUse.contains(i - 1))
                .findFirst()
                .orElseThrow(() -> new AnswerNotFoundException("Could not find answer"));
    }
}
