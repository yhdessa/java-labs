package edu.course.lab05;

import java.util.List;

public final class LoadResult {

    private final List<DataSample> samples;
    private final List<ValidationError> errors;

    public LoadResult(List<DataSample> samples, List<ValidationError> errors) {
        // TODO: проверить списки на null + защититься от изменений снаружи
        throw new UnsupportedOperationException("TODO");
    }

    public List<DataSample> samples() {
        throw new UnsupportedOperationException("TODO");
    }

    public List<ValidationError> errors() {
        throw new UnsupportedOperationException("TODO");
    }
}
