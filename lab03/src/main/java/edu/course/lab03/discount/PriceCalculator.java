package edu.course.lab03.discount;

public final class PriceCalculator {

    private final DiscountPolicy policy;

    public PriceCalculator(DiscountPolicy policy) {
        if (policy == null) {
            throw new IllegalArgumentException();        
        } this.policy = policy;
    }

    public double calculate(double price) {
        if (price < 0) {
            throw new IllegalArgumentException();
        } return policy.apply(price);
    }
}
