package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;

public final class SampleRegistry {

    private final Map<SampleId, DataSample> entries = new HashMap<>();

    // TODO: добавить или заменить значение
    public void put(SampleId id, DataSample sample) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: получить значение (что вернуть если ключа нет — реши сам)
    public DataSample get(SampleId id) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: удалить значение
    public void remove(SampleId id) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: проверить наличие ключа
    public boolean contains(SampleId id) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: узнать размер
    public int size() {
        throw new UnsupportedOperationException("TODO");
    }
}
