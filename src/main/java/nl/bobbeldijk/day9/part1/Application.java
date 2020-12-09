package nl.bobbeldijk.day9.part1;

import nl.bobbeldijk.day9.BaseApplication;
import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

public class Application extends BaseApplication {
    public static void main(String[] args) throws AnswerNotFoundException {
        System.out.println(new Application(25).calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY9)));
    }

    public Application(int amountOfPreambleNumbers) {
        super(amountOfPreambleNumbers);
    }
}
