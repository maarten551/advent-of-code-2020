package nl.bobbeldijk.day5;

public class SeatParser {
    public Seat parseSeatInput(String input) {
        int row = generateRow(input);
        int column = generateColumn(input);
        int seatId = generateSeatId(row, column);

        return new Seat(seatId, row, column);
    }

    private int generateRow(String input) {
        return determineNumberByBinarySearch(input.replaceAll("[^FB]", ""), 'B');
    }

    private int generateColumn(String input) {
        return determineNumberByBinarySearch(input.replaceAll("[^LR]", ""), 'R');
    }

    private int generateSeatId(int row, int column) {
        return (row * 8) + column;
    }

    private int determineNumberByBinarySearch(String input, char secondHalfChar) {
        double result = 0;

        for (int i = 1; i <= input.length(); i++) {
            if (input.charAt(i - 1) == secondHalfChar) {
                result += Math.pow(2, input.length() - i);
            }
        }

        return (int) result;
    }
}
