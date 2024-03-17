package io.jgym.warmups.day05;

public final class CompanyTaxStrategy implements TaxStrategy {
    public double calculateTax(double income) {
        return income * 0.24;
    }
}
