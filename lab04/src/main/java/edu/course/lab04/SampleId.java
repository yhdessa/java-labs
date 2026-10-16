package edu.course.lab04;

public final class SampleId {

    private final String dataset;
    private final int number;

    public SampleId(String dataset, int number) {
        // TODO: проверить dataset на null/blank, number на смысловые ограничения
        this.dataset = dataset;
        this.number = number;
    }

    public String dataset() {
        return dataset;
    }

    public int number() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO: равенство по dataset и number
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int hashCode() {
        // TODO: хеш от тех же полей, что в equals
        throw new UnsupportedOperationException("TODO");
    }
}
