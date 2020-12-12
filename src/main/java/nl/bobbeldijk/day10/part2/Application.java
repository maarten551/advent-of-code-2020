package nl.bobbeldijk.day10.part2;

import nl.bobbeldijk.day10.BaseApplication;
import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.Answerable;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.util.List;

public class Application extends BaseApplication implements Answerable<Long> {
    public static void main(String[] args) throws AnswerNotFoundException {
        System.out.println(new Application(InputReader.readListFromInputFile(InputFile.DAY10)).calculateAnswer(null));
    }

    public Application(List<String> input) {
        super(input);
    }

    @Override
    public Long calculateAnswer(List<String> input) throws AnswerNotFoundException {
        return calculateAmountOfPossibleConfigurationsFast();
    }
}
