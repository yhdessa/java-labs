package edu.course.lab04;

public final class Book {

    private final String isbn;
    private final String title;

    public Book(String isbn, String title) {
        // TODO: проверить isbn и title на null/blank
        this.isbn = isbn;
        this.title = title;
    }

    public String isbn() {
        return isbn;
    }

    public String title() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO: равенство по isbn
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int hashCode() {
        // TODO: хеш от тех же полей, что в equals
        throw new UnsupportedOperationException("TODO");
    }
}
