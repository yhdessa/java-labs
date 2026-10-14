package edu.course.lab03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.course.lab03.discount.DiscountPolicy;
import edu.course.lab03.discount.NoDiscount;
import edu.course.lab03.discount.PercentDiscount;
import edu.course.lab03.discount.PriceCalculator;

class DiscountPolicyTest {
    @Test
    void testNoDiscount() {
        DiscountPolicy policy = new NoDiscount();
        assertEquals(100.0, policy.apply(100.0));
    }
    void TestNoDiscountWithCalculator() {
        DiscountPolicy policy = new NoDiscount();
        PriceCalculator calculator = new PriceCalculator(policy);
        assertEquals(100.0, calculator.calculate(100.0));
    }
    @Test
    void testPercentDiscount() {
        DiscountPolicy policy = new PercentDiscount(10);
        assertEquals(90.0, policy.apply(100));
    }
    @Test
    void testPercentDiscountWithCalculator() {
        DiscountPolicy policy = new PercentDiscount(10);
        PriceCalculator calculator = new PriceCalculator(policy);
        assertEquals(90.0, calculator.calculate(100));
    }
    @Test
    void testPriceCalculatorWithStrategy() {
        DiscountPolicy policy = new PercentDiscount(10);
        PriceCalculator calculator = new PriceCalculator(policy);
        assertEquals(90.0, calculator.calculate(100));
    }
    @Test
    void TestNegativePercentThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(-30.0));
    }
    @Test
    void TestSuperiorPercentThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(150.0));
    }
    @Test
    void TestNegativePriceThrowsException() {
        DiscountPolicy policy = new NoDiscount();
        assertThrows(IllegalArgumentException.class, () -> {policy.apply(-30.0);});
    }
    @Test
    void TestNegativeCalculationThrowsException() {
        DiscountPolicy policy = new NoDiscount();
        PriceCalculator calculator = new PriceCalculator(policy);
        assertThrows(IllegalArgumentException.class, () -> {calculator.calculate(-30.0);});
    }
}
