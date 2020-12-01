package nl.bobbeldijk.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputReader {
    public static Stream<String> readStreamFromInputFile(InputFile inputFile) {
        var fileLocation = String.format("%s/input.txt", inputFile.getFilename());

        try (var inputStream = InputReader.class.getClassLoader().getResourceAsStream(fileLocation)) {
            if (inputStream == null) {
                throw new IOException(String.format("File '%s' not found", fileLocation));
            }

            try (var bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                // Create a new list, otherwise the reader is already closed when the stream is used
                return bufferedReader.lines().collect(Collectors.toList()).stream();
            }
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public static String readStringFromInputFile(InputFile inputFile) {
        return readStreamFromInputFile(inputFile).collect(Collectors.joining("\r\n"));
    }
}
