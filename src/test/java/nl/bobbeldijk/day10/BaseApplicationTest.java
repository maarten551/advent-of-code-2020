package nl.bobbeldijk.day10;

import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

class BaseApplicationTest {
    @Test
    void calculateJoltageDifferencesExample1() {
        var input = InputReader.readStreamFromInputFile(InputFile.DAY10, true, "test1").collect(Collectors.toList());
        var baseApplication = new BaseApplication(input);
        var result = baseApplication.calculateJoltageDifferences();

        Assertions.assertEquals(3, result.size());

        Assertions.assertTrue(result.containsKey(1));
        Assertions.assertEquals(7, result.get(1));

        Assertions.assertTrue(result.containsKey(2));
        Assertions.assertEquals(0, result.get(2));

        Assertions.assertTrue(result.containsKey(3));
        Assertions.assertEquals(5, result.get(3));
    }

    @Test
    void calculateJoltageDifferencesExample2() {
        var input = InputReader.readStreamFromInputFile(InputFile.DAY10, true, "test2").collect(Collectors.toList());
        var baseApplication = new BaseApplication(input);
        var result = baseApplication.calculateJoltageDifferences();

        Assertions.assertEquals(3, result.size());

        Assertions.assertTrue(result.containsKey(1));
        Assertions.assertEquals(22, result.get(1));

        Assertions.assertTrue(result.containsKey(2));
        Assertions.assertEquals(0, result.get(2));

        Assertions.assertTrue(result.containsKey(3));
        Assertions.assertEquals(10, result.get(3));
    }

    @Test
    void calculateAmountOfPossibleConfigurationsExample1() {
        var input = InputReader.readStreamFromInputFile(InputFile.DAY10, true, "test1").collect(Collectors.toList());
        var baseApplication = new BaseApplication(input);

        Assertions.assertEquals(8, baseApplication.calculateAmountOfPossibleConfigurationsRecursive());
        Assertions.assertEquals(8, baseApplication.calculateAmountOfPossibleConfigurationsFast());
    }

    @Test
    void calculateAmountOfPossibleConfigurationsExample2() {
        var input = InputReader.readStreamFromInputFile(InputFile.DAY10, true, "test2").collect(Collectors.toList());
        var baseApplication = new BaseApplication(input);

        Assertions.assertEquals(19208, baseApplication.calculateAmountOfPossibleConfigurationsRecursive());
        Assertions.assertEquals(19208, baseApplication.calculateAmountOfPossibleConfigurationsFast());
    }

    @Test
    void breakIntoSegmentsTest() {
        var input = InputReader.readStreamFromInputFile(InputFile.DAY10, true, "test1").collect(Collectors.toList());
        var baseApplication = new BaseApplication(input);

        var result = baseApplication.breakIntoSegments();

        Assertions.assertEquals(5, result.size());
        Assertions.assertEquals(1, result.get(0).size());
        Assertions.assertEquals(4, result.get(1).size());
        Assertions.assertEquals(3, result.get(2).size());
        Assertions.assertEquals(2, result.get(3).size());
        Assertions.assertEquals(1, result.get(4).size());
    }
}