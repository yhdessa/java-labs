package edu.course.lab05;

import java.util.Objects;

public final class ValidationError {

    private final int lineNumber;
    private final String field;
    private final String message;

    public ValidationError(int lineNumber, String field, String message) {
        if (lineNumber <= 0) {
            throw new IllegalArgumentException("lineNumber must be positive");
        }
        if (field == null || field.isBlank()) {
            throw new IllegalArgumentException("field must not be blank");
        }
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("message must not be blank");
        }
        this.lineNumber = lineNumber;
        this.field = field;
        this.message = message;
    }

    public int lineNumber() {
        return lineNumber;
    }

    public String field() {
        return field;
    }

    public String message() {
        return message;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ValidationError other = (ValidationError) obj;
        return lineNumber == other.lineNumber
                && field.equals(other.field)
                && message.equals(other.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNumber, field, message);
    }

    @Override
    public String toString() {
        return "ValidationError{line=" + lineNumber + ", field='" + field + "', message='" + message + "'}";
    }
}
