package io.jgym.warmups.day29;

import java.util.stream.*;

public class ParallelStreamDemo {
    public static void main(String... args) {
        new CPUMeasuringForkJoinThreadFactory();
        long sum = IntStream.rangeClosed(0, 10_000_000)
                .map(i -> i * i)
                .parallel()
                .sum();
    }
}
