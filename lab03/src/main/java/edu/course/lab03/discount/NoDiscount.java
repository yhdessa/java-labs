package edu.course.lab03.discount;

public final class NoDiscount implements DiscountPolicy {

    @Override
    public double apply(double price) {
        if (price < 0) {
            throw new IllegalArgumentException();
        } return price;
    }
}
