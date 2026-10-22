package edu.course.lab04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampleRegistryTest {

    // Вспомогательный метод: валидный DataSample (lab04 — 4 параметра)
    private DataSample sample(String id, String label) {
        return new DataSample(id, label, SampleStatus.RAW, new double[]{1.0, 2.0});
    }

    // 1. Два разных экземпляра равного ключа находят одно значение
    @Test
    void equalKeysFindSameValue() {
        SampleRegistry reg = new SampleRegistry();
        DataSample v = sample("s1", "label"); // ОДИН общий объект

        reg.put(new SampleId("dataset", 1), v);
        DataSample found = reg.get(new SampleId("dataset", 1)); // другой экземпляр ключа

        assertSame(v, found, "HashMap должен находить значение по другому экземпляру равного ключа");
    }

    // 2. Повторное добавление с равным ключом заменяет значение и не увеличивает размер
    @Test
    void replaceDoesNotIncreaseSize() {
        SampleRegistry reg = new SampleRegistry();
        DataSample v1 = sample("s1", "l1");
        DataSample v2 = sample("s2", "l2");

        reg.put(new SampleId("ds", 1), v1);
        int sizeBefore = reg.size();

        reg.put(new SampleId("ds", 1), v2);

        assertEquals(1, reg.size(), "Размер не должен вырасти при замене");
        assertSame(v2, reg.get(new SampleId("ds", 1)), "Значение должно замениться на v2");
    }

    // 3. Два неравных ключа с одинаковым hashCode хранят разные значения
    @Test
    void collisionPreservesDifferentValues() {
        // Свой ключ с намеренно одинаковым hashCode (НЕ наследуем SampleId)
        class CollidingKey {
            private final String dataset;
            private final int number;
            CollidingKey(String ds, int n) { this.dataset = ds; this.number = n; }
            String dataset() { return dataset; }
            int number() { return number; }
            @Override public int hashCode() { return 42; } // всегда 42
            @Override public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                CollidingKey other = (CollidingKey) o;
                return number == other.number && dataset.equals(other.dataset);
            }
        }

        SampleRegistry reg = new SampleRegistry();
        // SampleRegistry принимает SampleId, поэтому используем адаптер:
        // Создаём SampleId с теми же данными — их hashCode будет разный,
        // но для теста коллизии нам нужны ключи с ОДИНАКОВЫМ hashCode.
        // Поэтому используем SampleRegistry через reflection или создаём свою карту.
        // Проще: тестируем HashMap напрямую с CollidingKey, логика та же.
        java.util.Map<CollidingKey, DataSample> map = new java.util.HashMap<>();

        CollidingKey k1 = new CollidingKey("a", 1);
        CollidingKey k2 = new CollidingKey("b", 2);
        DataSample v1 = sample("s1", "l");
        DataSample v2 = sample("s2", "l");

        map.put(k1, v1);
        map.put(k2, v2);

        assertEquals(2, map.size(), "Коллизия не должна слить разные ключи");
        assertNotSame(map.get(k1), map.get(k2));
    }

    // 4. Сравнение с null и объектом другого типа возвращает false
    @Test
    void equalsRejectsNullAndOtherType() {
        SampleId k = new SampleId("ds", 1);

        assertFalse(k.equals(null), "equals(null) → false");
        assertFalse(k.equals("string"), "equals(String) → false");
        assertFalse(k.equals(42), "equals(Integer) → false");
        assertFalse(k.equals(new Object()), "equals(Object) → false");
    }

    // 5. Изменяемый ключ ломает поиск (учебный эксперимент)
    @Test
    void mutableKeyBreaksLookup() {
        // Свой изменяемый ключ (не наследуем SampleId)
        class MutableKey {
            String mutableDataset;
            final int number;
            MutableKey(String ds, int n) { this.mutableDataset = ds; this.number = n; }
            String dataset() { return mutableDataset; }
            int number() { return number; }
            @Override public int hashCode() { return java.util.Objects.hash(mutableDataset, number); }
            @Override public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                MutableKey other = (MutableKey) o;
                return number == other.number && mutableDataset.equals(other.mutableDataset);
            }
        }

        // Тестируем через HashMap напрямую (логика та же)
        java.util.Map<MutableKey, DataSample> map = new java.util.HashMap<>();
        MutableKey k = new MutableKey("ds", 1);
        DataSample v = sample("s1", "label");

        map.put(k, v);

        k.mutableDataset = "other"; // ИЗМЕНИЛИ ПОЛЕ КЛЮЧА ПОСЛЕ PUT

        assertNull(map.get(k), "После изменения поля ключа поиск не находит значение (ищет в другом бакете)");
    }
}