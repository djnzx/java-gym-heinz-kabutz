package io.jgym.warmups.day05;

public sealed abstract class IndividualTaxStrategy implements TaxStrategy
        permits EmployeeTaxStrategy, FreelancerTaxStrategy {
}
