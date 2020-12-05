package nl.bobbeldijk.day5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeatParserTest {

    private SeatParser seatParser;

    @BeforeEach
    void setUp() {
        seatParser = new SeatParser();
    }

    @ParameterizedTest
    @CsvSource(value = {"70:BFFFBBFRRR", "14:FFFBBBFRRR", "102:BBFFBBFRLL"}, delimiter = ':')
    void parseSeatInputRowTest(int expectedAnswer, String input) {
        assertEquals(expectedAnswer, seatParser.parseSeatInput(input).getRow());
    }

    @ParameterizedTest
    @CsvSource(value = {"7:BFFFBBFRRR", "7:FFFBBBFRRR", "4:BBFFBBFRLL"}, delimiter = ':')
    void parseSeatInputColumnTest(int expectedAnswer, String input) {
        assertEquals(expectedAnswer, seatParser.parseSeatInput(input).getColumn());
    }

    @ParameterizedTest
    @CsvSource(value = {"567:BFFFBBFRRR", "119:FFFBBBFRRR", "820:BBFFBBFRLL"}, delimiter = ':')
    void parseSeatInputSeatIdTest(int expectedAnswer, String input) {
        assertEquals(expectedAnswer, seatParser.parseSeatInput(input).getId());
    }
}