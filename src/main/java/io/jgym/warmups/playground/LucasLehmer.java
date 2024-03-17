package io.jgym.warmups.playground;

import java.math.*;

public class LucasLehmer {
    public static void main(String... args) {
        long time = System.nanoTime();
        try {
            int number = 0;
            for (int i = 0; i < 1_000_000; i++) {
                if (isMersennePrime(i)) {
                    if (++number == 20) break;
                    System.out.printf("isMersennePrime(%d) is prime%n", i);
                }
            }
        } finally {
            time = System.nanoTime() - time;
            System.out.printf("time = %dms%n", (time / 1_000_000));
        }
    }

    public static boolean isMersennePrime(int exponent) {
        if (exponent < 2) return false;
        if (exponent == 2) return true;

        BigInteger s = BigInteger.valueOf(4);
        BigInteger m = BigInteger.ONE.shiftLeft(exponent)
                .subtract(BigInteger.ONE);
        for (int i = 0; i < exponent - 2; i++) {
            s = s.multiply(s).subtract(BigInteger.TWO).remainder(m);
        }
        return s.equals(BigInteger.ZERO);
    }
}
