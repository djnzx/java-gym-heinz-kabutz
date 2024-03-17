package io.jgym.warmups.day05;

public sealed interface TaxStrategy permits CompanyTaxStrategy,
        IndividualTaxStrategy, TrustTaxStrategy {
    double calculateTax(double income);
}
