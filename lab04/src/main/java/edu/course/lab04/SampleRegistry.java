package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;

public final class SampleRegistry {

    private final Map<SampleId, DataSample> entries = new HashMap<>();

    public void put(SampleId id, DataSample sample) {
        if (id == null || sample == null) {
            throw new IllegalArgumentException();
        } entries.put(id, sample);
    }

    public DataSample get(SampleId id) {
        if (id == null) {
            throw new IllegalArgumentException();
        } return entries.get(id);
    }

    public void remove(SampleId id) {
        if (id == null) {
            throw new IllegalArgumentException();
        } entries.remove(id);
    }

    public boolean contains(SampleId id) {
        if (id == null) throw new IllegalArgumentException();
        return entries.containsKey(id);
    }

    public int size() {
        return entries.size();
    }
}