package nl.bobbeldijk.day4.part1;

import nl.bobbeldijk.day4.ApplicationBase;
import nl.bobbeldijk.day4.Field;
import nl.bobbeldijk.day4.FieldStatus;
import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.util.Arrays;
import java.util.List;

public class Application extends ApplicationBase {
    public static void main(String[] args) throws AnswerNotFoundException {
        var application = new Application();

        System.out.println(application.calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY4)));
    }

    @Override
    public Long calculateAnswer(List<String> input) throws AnswerNotFoundException {
        this.parseInput(input);

        return this.parsedPassports.stream()
                .filter(passport -> Arrays.stream(Field.values())
                        .filter(field -> FieldStatus.REQUIRED.equals(field.getFieldStatus()))
                        .allMatch(passport::containsKey))
                .count();
    }
}
