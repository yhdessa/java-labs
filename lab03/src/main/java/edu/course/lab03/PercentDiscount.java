package edu.course.lab03;

public final class PercentDiscount implements DiscountPolicy {

    private final double percent;

    public PercentDiscount(double percent) {
        // TODO: проверить диапазон 0..100
        this.percent = percent;
    }

    @Override
    public double apply(double price) {
        throw new UnsupportedOperationException("TODO");
    }
}
