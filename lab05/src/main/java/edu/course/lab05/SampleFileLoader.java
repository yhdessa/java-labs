package edu.course.lab05;

import java.nio.file.Path;

public final class SampleFileLoader {

    // Формат: id;label;source;featureCount;status
    // Пример: S-101;cat;camera-1;128;READY
    public LoadResult load(Path path) {
        // TODO: прочитать файл как UTF-8, проверить заголовок,
        // разбирать строки обычным циклом, ошибки строк — в список,
        // отсутствующий файл — DataLoadingException с cause
        throw new UnsupportedOperationException("TODO");
    }
}
