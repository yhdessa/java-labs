package edu.course.lab05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

class SampleFileLoaderTest {

    @TempDir
    Path tempDir;

    private final SampleFileLoader loader = new SampleFileLoader();

    private Path writeFile(String... lines) throws IOException {
        Path file = tempDir.resolve("samples.csv");
        Files.write(file, List.of(lines), StandardCharsets.UTF_8);
        return file;
    }

    @Test
    void loadsValidFile() throws IOException {
        Path file = writeFile(
                SampleFileLoader.HEADER,
                "S-101;cat;camera-1;128;READY",
                "S-102;dog;camera-2;64;RAW");

        LoadResult result = loader.load(file);

        assertTrue(result.errors().isEmpty());
        assertEquals(2, result.samples().size());

        DataSample first = result.samples().get(0);
        assertEquals("S-101", first.getId());
        assertEquals("cat", first.getLabel());
        assertEquals("camera-1", first.getSource());
        assertEquals(SampleStatus.READY, first.getStatus());
        assertEquals(128, first.getFeatures().length);
    }

    @Test
    void keepsCyrillicAndUnicodeText() throws IOException {
        Path file = writeFile(
                SampleFileLoader.HEADER,
                "S-201;кот;камера-№1;16;PROCESSED");

        LoadResult result = loader.load(file);

        assertTrue(result.errors().isEmpty());
        DataSample sample = result.samples().get(0);
        assertEquals("кот", sample.getLabel());
        assertEquals("камера-№1", sample.getSource());
    }

    @Test
    void blankFieldBecomesValidationError() throws IOException {
        Path file = writeFile(
                SampleFileLoader.HEADER,
                "S-301;;camera-1;32;READY");

        LoadResult result = loader.load(file);

        assertTrue(result.samples().isEmpty());
        assertEquals(1, result.errors().size());

        ValidationError error = result.errors().get(0);
        assertEquals(2, error.lineNumber());
        assertEquals("label", error.field());
        assertFalse(error.message().isBlank());
    }

    @Test
    void invalidNumberBecomesValidationError() throws IOException {
        Path file = writeFile(
                SampleFileLoader.HEADER,
                "S-401;cat;camera-1;abc;READY");

        LoadResult result = loader.load(file);

        assertTrue(result.samples().isEmpty());
        assertEquals(1, result.errors().size());
        assertEquals(2, result.errors().get(0).lineNumber());
        assertEquals("featureCount", result.errors().get(0).field());
    }

    @Test
    void unknownStatusBecomesValidationError() throws IOException {
        Path file = writeFile(
                SampleFileLoader.HEADER,
                "S-501;cat;camera-1;32;DONE");

        LoadResult result = loader.load(file);

        assertTrue(result.samples().isEmpty());
        assertEquals(1, result.errors().size());
        assertEquals("status", result.errors().get(0).field());
    }

    @Test
    void wrongColumnCountBecomesValidationError() throws IOException {
        Path file = writeFile(
                SampleFileLoader.HEADER,
                "S-601;cat;camera-1");

        LoadResult result = loader.load(file);

        assertTrue(result.samples().isEmpty());
        assertEquals(1, result.errors().size());
        assertEquals(2, result.errors().get(0).lineNumber());
    }

    @Test
    void keepsValidRowsAndCollectsErrorsTogether() throws IOException {
        Path file = writeFile(
                SampleFileLoader.HEADER,
                "S-701;cat;camera-1;32;READY",
                "S-702;dog;camera-2;oops;READY",
                "S-703;bird;camera-3;8;PROCESSED",
                "S-704;fish;camera-4;8;UNKNOWN",
                "");

        LoadResult result = loader.load(file);

        assertEquals(2, result.samples().size());
        assertEquals("S-701", result.samples().get(0).getId());
        assertEquals("S-703", result.samples().get(1).getId());

        assertEquals(2, result.errors().size());
        assertEquals(3, result.errors().get(0).lineNumber());
        assertEquals(5, result.errors().get(1).lineNumber());
    }

    @Test
    void missingFileThrowsDataLoadingExceptionWithCause() {
        Path missing = tempDir.resolve("does-not-exist.csv");

        DataLoadingException exception =
                assertThrows(DataLoadingException.class, () -> loader.load(missing));

        assertInstanceOf(NoSuchFileException.class, exception.getCause());
        assertTrue(exception.getMessage().contains("does-not-exist.csv"));
    }

    @Test
    void wrongHeaderThrowsDataLoadingException() throws IOException {
        Path file = writeFile(
                "id;label;featureCount;status",
                "S-801;cat;32;READY");

        assertThrows(DataLoadingException.class, () -> loader.load(file));
    }

    @Test
    void emptyFileThrowsDataLoadingException() throws IOException {
        Path file = writeFile();

        assertThrows(DataLoadingException.class, () -> loader.load(file));
    }
}
