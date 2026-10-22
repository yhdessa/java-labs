package edu.course.lab04;

import java.util.Objects;

import javax.management.ObjectName;

public final class SampleId {

    private final String dataset;
    private final int number;

    public SampleId(String dataset, int number) {
        if (dataset == null || dataset.isBlank()) {
            throw new IllegalArgumentException();
        } if (number <= 0) {
            throw new IllegalArgumentException();
        }
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
        if (this == obj) {
            return true;
        } if (obj == null || getClass() != obj.getClass()) {
            return false;
        } SampleId other = (SampleId) obj;
        return Objects.equals(number, other.number) 
            && Objects.equals(dataset, other.dataset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, dataset);
    }
}
