package nl.bobbeldijk.day11.part1;

import nl.bobbeldijk.day11.BaseApplication;
import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.Answerable;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

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
}
