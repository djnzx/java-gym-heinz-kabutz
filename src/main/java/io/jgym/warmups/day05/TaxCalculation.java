package io.jgym.warmups.day05;

public class TaxCalculation {
    public static void main(String... args) {
        var amazon = new TaxPayer(10, new CompanyTaxStrategy());
        var giannis = new TaxPayer(35_000, new EmployeeTaxStrategy());
        var giorgos = new TaxPayer(54_000, new FreelancerTaxStrategy());//d -> 0);
        var dogsAndCats = new TaxPayer(4_000, new TrustTaxStrategy());

        System.out.println("amazon.calculateTax() = " + amazon.calculateTax());
        System.out.println("giannis.calculateTax() = " + giannis.calculateTax());
        System.out.println("giorgos.calculateTax() = " + giorgos.calculateTax());
        System.out.println("dogsAndCats.calculateTax() = " + dogsAndCats.calculateTax());
    }

//    private static final class ZeroTaxStrategy implements TaxStrategy {
//        public double calculateTax(double income) {
//            return 0;
//        }
//    }
}
