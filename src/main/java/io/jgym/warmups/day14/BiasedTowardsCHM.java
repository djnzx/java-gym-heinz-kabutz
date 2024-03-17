package io.jgym.warmups.day14;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class BiasedTowardsCHM {
    public static void main(String... args) {
        for (int i = 0; i < 30; i++) {
            test();
        }
    }

    private static void test() {
        System.gc();
        System.gc();
        System.gc();
        Map<Long, Long> squares = new ConcurrentHashMap<>();
        long time = System.nanoTime();
        try {
            LongStream.range(0, 1_000_000)
                    .parallel()
                    .forEach(number -> squares.put(number * number, number));
        } finally {
            time = System.nanoTime() - time;
            System.out.printf("time = %dms%n", (time / 1_000_000));
        }
    }
}
