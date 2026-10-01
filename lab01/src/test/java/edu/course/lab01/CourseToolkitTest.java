package edu.course.lab01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);
        assertTrue(result);
    }
    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);
        assertFalse(result);
    }
    @Test
    void returnsTrueForZero() {
        boolean result = CourseToolkit.isEven(0);
        assertTrue(result);
    }
    @Test
    void returnsTrueForNegativeEvenNumber() {
        boolean result = CourseToolkit.isEven(-8);
        assertTrue(result);
    }

    @Test
    void returnsFalseForZero() {
        assertFalse(CourseToolkit.isPrime(0));
    }
    @Test
    void returnsFalseForCompositeSix() {
        assertFalse(CourseToolkit.isPrime(6));
    }
    @Test
    void returnsFalseForSquareOfPrime() {
        assertFalse(CourseToolkit.isPrime(49));
    }
    @Test
    void returnsTrueForTwo() {
        assertTrue(CourseToolkit.isPrime(2));
    }
    @Test
    void returnsTrueForPrime() {
        assertTrue(CourseToolkit.isPrime(13));
    }
    @Test
    void returnsTrueForPalindrome() {
        assertTrue(CourseToolkit.isPalindrome("level"));
        assertTrue(CourseToolkit.isPalindrome("шалаш"));
    }
    @Test
    void returnsFalseForNonPalindrome() {
        assertFalse(CourseToolkit.isPalindrome("l3vel"));
        assertFalse(CourseToolkit.isPalindrome("Арбуз"));
    }
    @Test
    void isCaseSensitive() {
        assertFalse(CourseToolkit.isPalindrome("leveL"));
    }
    @Test
    void spacesMatter() {
        assertTrue(CourseToolkit.isPalindrome("a b a"));
        assertFalse(CourseToolkit.isPalindrome("ab a"));
    }
    @Test
    void rejectsNullText() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.isPalindrome(null));
    }
    @Test
    void returnsAverageForValues() {
        int[] values = new int[]{10, 20, 30};
        double result = CourseToolkit.average(values);
        assertEquals(20.0, result, 1e-9);
    }
    @Test
    void rejectsNullArrayForAverage() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(null));
    }
    @Test
    void rejectsEmptyArrayForAverage() {
        int[] values = new int[]{};
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(values));
    }
    @Test
    void returnsAverageForNegativeValues() {
        int[] values = new int[]{-10, -20, -30};
        double result = CourseToolkit.average(values);
        assertEquals(-20.0, result, 1e-9);
    }
    @Test
    void doesNotModifyArrayForAverage() {
        int[] values = new int[]{10, 20, 30};
        CourseToolkit.average(values);
        assertArrayEquals(new int[]{10, 20, 30}, values);
    }
    @Test
    void returnsMinForValues() {
        int[] values = new int[]{10, 20, 30};
        int result = CourseToolkit.min(values);
        assertEquals(10, result);
    }
    @Test
    void returnsMinForEmptyValues() {
        int[] values = new int[]{};
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.min(values));
    }
    @Test
    void rejectsNullArrayForMin() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.min(null));
    }
    @Test
    void returnsMinForNegativeValues() {
        int[] values = new int[]{-10, -20, -30};
        int result = CourseToolkit.min(values);
        assertEquals(-30, result);
    }
    @Test
    void returnsMaxForValues() {
        int[] values = new int[]{10, 20, 30};
        int result = CourseToolkit.max(values);
        assertEquals(30, result);
    }
    @Test
    void returnsMaxForEmptyValues() {
        int[] values = new int[]{};
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.max(values));
    }
    @Test
    void rejectsNullArrayForMax() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.max(null));
    }
    @Test
    void returnsMaxForNegativeValues() {
        int[] values = new int[]{-10, -20, -30};
        int result = CourseToolkit.max(values);
        assertEquals(-10, result);
    }
}