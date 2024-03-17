package io.jgym.warmups.day07;

// Heinz's ProjectEuler.net key: 717601_3I7Kn6n5LTCjvqEf4D5B27eL5lrWge0V
public class MersennePrimes {
    public static void main(String... args) {
        for (int exp = 0; exp < 64; exp++) {
            long number = (1L << exp) - 1;
            long time = System.nanoTime();
            if (isPrime(number)) {
                time = System.nanoTime() - time;
                System.out.printf("2^%d-1 is prime in %dms%n", exp, time / 1_000_000);
            }
        }
    }

    private static boolean isPrime(long number) {
        if (number < 2) return false;
        long upto = (long) Math.sqrt(number) + 1;
        for (long div = 2; div <= upto; div++) {
            if (number % div == 0) return false;
        }
        return true;
    }
}