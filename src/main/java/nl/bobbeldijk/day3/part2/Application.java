package nl.bobbeldijk.day3.part2;

import nl.bobbeldijk.day3.ApplicationBase;
import nl.bobbeldijk.day3.BottomReachedException;
import nl.bobbeldijk.day3.Hill;
import nl.bobbeldijk.day3.HillCoordinateItem;
import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.util.List;

public class Application extends ApplicationBase {
    public static void main(String[] args) throws AnswerNotFoundException {
        System.out.println((new Application()).calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY3)));
    }

    @Override
    public Integer calculateAnswer(List<String> input) throws AnswerNotFoundException {
        var hill = Hill.generate(input);
        var treeSum = 0;
        var movementPatterns = new int[][]{
                {1, 1},
                {1, 3},
                {1, 5},
                {1, 7},
                {2, 1},
        };

        for (int[] movementPattern : movementPatterns) {
            var treeCounter = 0;
            try {
                for (int y = 0, x = 0; true; y += movementPattern[0], x += movementPattern[1]) {
                    treeCounter += (hill.getItemOnCoordinates(y, x) == HillCoordinateItem.TREE) ? 1 : 0;
                }
            } catch (BottomReachedException e) {
                if (treeSum == 0) {
                    treeSum = treeCounter;
                } else {
                    treeSum *= treeCounter;
                }
            }
        }

        return treeSum;
    }
}
