package edu.course.lab05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class SampleFileLoader {

    public static final String HEADER = "id;label;source;featureCount;status";

    private static final int FIELD_COUNT = 5;

    public LoadResult load(Path path) {
        if (path == null) {
            throw new IllegalArgumentException("path must not be null");
        }

        List<String> lines = readLines(path);
        checkHeader(lines, path);

        List<DataSample> samples = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>();

        for (int index = 1; index < lines.size(); index++) {
            String line = lines.get(index);
            if (line.isBlank()) {
                continue;
            }
            int lineNumber = index + 1;
            parseLine(line, lineNumber, samples, errors);
        }

        return new LoadResult(samples, errors);
    }

    private List<String> readLines(Path path) {
        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DataLoadingException("Cannot read " + path, e);
        }
    }

    private void checkHeader(List<String> lines, Path path) {
        if (lines.isEmpty()) {
            throw new DataLoadingException("File is empty: " + path, null);
        }
        String actual = lines.get(0).trim();
        if (!HEADER.equals(actual)) {
            throw new DataLoadingException(
                    "Unexpected header in " + path + ": expected '" + HEADER + "' but was '" + actual + "'", null);
        }
    }

    private void parseLine(String line, int lineNumber,
                           List<DataSample> samples, List<ValidationError> errors) {
        String[] parts = line.split(";", -1);
        if (parts.length != FIELD_COUNT) {
            errors.add(new ValidationError(lineNumber, "row",
                    "expected " + FIELD_COUNT + " fields but was " + parts.length));
            return;
        }

        String id = parts[0].trim();
        String label = parts[1].trim();
        String source = parts[2].trim();
        String featureCountRaw = parts[3].trim();
        String statusRaw = parts[4].trim();

        List<ValidationError> rowErrors = new ArrayList<>();

        if (id.isEmpty()) {
            rowErrors.add(new ValidationError(lineNumber, "id", "id must not be blank"));
        }
        if (label.isEmpty()) {
            rowErrors.add(new ValidationError(lineNumber, "label", "label must not be blank"));
        }
        if (source.isEmpty()) {
            rowErrors.add(new ValidationError(lineNumber, "source", "source must not be blank"));
        }

        int featureCount = -1;
        try {
            featureCount = Integer.parseInt(featureCountRaw);
            if (featureCount <= 0) {
                rowErrors.add(new ValidationError(lineNumber, "featureCount",
                        "featureCount must be positive but was " + featureCount));
            }
        } catch (NumberFormatException e) {
            rowErrors.add(new ValidationError(lineNumber, "featureCount",
                    "featureCount is not a number: '" + featureCountRaw + "'"));
        }

        SampleStatus status = null;
        try {
            status = SampleStatus.valueOf(statusRaw);
        } catch (IllegalArgumentException e) {
            rowErrors.add(new ValidationError(lineNumber, "status",
                    "unknown status: '" + statusRaw + "'"));
        }

        if (!rowErrors.isEmpty()) {
            errors.addAll(rowErrors);
            return;
        }

        samples.add(new DataSample(id, label, source, status, new double[featureCount]));
    }
}
