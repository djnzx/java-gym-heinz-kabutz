package io.jgym.warmups.day05;

public final class FreelancerTaxStrategy extends IndividualTaxStrategy {
    public double calculateTax(double income) {
        if (income < 20_000) return income * 0.24;
        return 20_000 * 0.24 + (income - 20_000) * 0.29;
    }
}
