package io.jgym.warmups.day05;

public class TaxPayer {
    private final TaxStrategy strategy;
    private final double income;

    public TaxPayer(double income, TaxStrategy strategy) {
        this.strategy = strategy;
        this.income = income;
    }

    public double calculateTax() {
        return strategy.calculateTax(income);
    }
}
