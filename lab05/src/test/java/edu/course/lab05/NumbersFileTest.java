package edu.course.lab05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

class NumbersFileTest {

    @TempDir
    Path tempDir;

    private Path writeNumbers() throws IOException {
        Path file = tempDir.resolve("numbers.txt");
        Files.write(file, List.of("10", "bad", "20"), StandardCharsets.UTF_8);
        return file;
    }

    @Test
    void readsFileContentInUtf8() throws IOException {
        Path file = writeNumbers();

        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);

        assertEquals(List.of("10", "bad", "20"), lines);
        assertEquals(3, lines.size());
    }

    @Test
    void convertsLinesToIntegersWithLoop() throws IOException {
        Path file = tempDir.resolve("only-numbers.txt");
        Files.write(file, List.of("10", "20", "30"), StandardCharsets.UTF_8);

        List<Integer> numbers = new ArrayList<>();
        for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
            numbers.add(Integer.parseInt(line));
        }

        assertEquals(List.of(10, 20, 30), numbers);
    }

    @Test
    void badLineThrowsNumberFormatException() throws IOException {
        Path file = writeNumbers();
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);

        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            for (String line : lines) {
                Integer.parseInt(line);
            }
        });

        assertTrue(exception.getMessage().contains("bad"));
    }
}
