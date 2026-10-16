package edu.course.lab05;

public final class ValidationError {

    private final int lineNumber;
    private final String field;
    private final String message;

    public ValidationError(int lineNumber, String field, String message) {
        // TODO: проверить lineNumber, field и message
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
}
