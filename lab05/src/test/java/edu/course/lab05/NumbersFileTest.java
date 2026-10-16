package edu.course.lab05;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

class NumbersFileTest {

    @TempDir
    Path tempDir;

    @Test
    void placeholder() throws Exception {
        // TODO: создать numbers.txt ("10", "bad", "20") через Files.write с UTF_8,
        // прочитать Files.readAllLines, преобразовать циклом,
        // "bad" -> NumberFormatException отдельным тестом
        Path file = tempDir.resolve("numbers.txt");
        Files.write(file, List.of("10", "bad", "20"), StandardCharsets.UTF_8);
        assertTrue(Files.exists(file));
    }
}
