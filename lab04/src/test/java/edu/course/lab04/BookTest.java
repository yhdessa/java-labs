package edu.course.lab04;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void sameIsbnFindsValueInMap() {
        // Подготовка: две книги с одинаковым ISBN, разные title
        Book b1 = new Book("978-0134685991", "Effective Java");
        Book b2 = new Book("978-0134685991", "Java Concurrency");
        Map<Book, String> map = new HashMap<>();
        // Действие: кладем по первой
        map.put(b1, "value1");
        // Проверка: находим по второй (другой экземпляр, тот же ISBN)
        assertSame("value1", map.get(b2), "HashMap должен находить значение по эквивалентному ключу");
    }

    @Test
    void differentIsbnNotEqual() {
        Book b1 = new Book("978-0134685991", "Title A");
        Book b2 = new Book("978-0134686002", "Title B"); // другой ISBN
        assertNotEquals(b1, b2); //Книги с разными ISBN не равны
        assertNotEquals(b1.hashCode(), b2.hashCode()); //Хешкоды могут совпасть, но не обязаны — тест на неравенство объектов
    }
}