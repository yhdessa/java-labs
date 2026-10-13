package edu.course.lab03;

public final class PriceCalculator {

    private final DiscountPolicy policy;

    public PriceCalculator(DiscountPolicy policy) {
        // TODO: проверить policy на null
        this.policy = policy;
    }

    public double calculate(double price) {
        throw new UnsupportedOperationException("TODO");
    }
}
