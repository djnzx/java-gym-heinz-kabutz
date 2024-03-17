package io.jgym.warmups.day03;

import java.util.stream.*;

public class PrimeNumbers {
    public static void main(String... args) {
        System.out.println(isPrime(23948237));
        System.out.println(isPrime(Integer.MAX_VALUE)); // 2^31 - 1
        System.out.println(isPrime(12L + Integer.MAX_VALUE)); // 2^31 + 11

    }

    private static boolean isPrime(long n) {
        long time = System.nanoTime();
        try {
            return LongStream.range(2, n).parallel().noneMatch(i -> n % i == 0);
        } finally {
            time = System.nanoTime() - time;
            System.out.printf("time = %dms%n", (time / 1_000_000));
        }
    }
}
