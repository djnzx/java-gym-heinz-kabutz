package io.jgym.warmups.day05;

public final class EmployeeTaxStrategy extends IndividualTaxStrategy {
    /*
      South African Tax Law Feb 2020
      Up to R195,850: 18% of taxable income
      R195,851–R305,850: 26% (R35,253 plus 26% of taxable income above R195,850)
      R305,851–R423,300: 31% (R63,853 plus 31% of taxable income above R305,850)
      R423,301–R555,600: 36% (R100,263 plus 36% of taxable income above R423,300)
      R555,601–R708,310: 39% (R147,891 plus 39% of taxable income above R555,600)
      R708,311 – R1,500,000: 41% (R207,448 plus 41% of taxable income above R708,310)
      R1,500,001+: 45% (R532,041 plus 45% of taxable income above R1,500,000)

      Tax Exemption:
        R79,000: for those under 65 years;
        R122,300: for those aged 65–74 years;
        R136,750: for those aged 75 years and over
     */
    public double calculateTax(double income) {
        // senior Java programmer should get about R1m - $64k
        return income * 0.325;
    }
}
