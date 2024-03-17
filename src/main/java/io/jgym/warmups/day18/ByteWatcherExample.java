package io.jgym.warmups.day18;

import java.util.concurrent.*;
import java.util.stream.*;

public class ByteWatcherExample {
    private static Object leak;

    public static void main(String... args) throws Exception {
        ByteWatcherSingleThread bw = new ByteWatcherSingleThread();

        Callable<?> creator = () ->
            IntStream.range(0, 1_000_000).boxed().parallel().collect(Collectors.toSet());

        leak = creator.call();

        bw.reset();
        leak = creator.call();
        long bytes = bw.calculateAllocations();

        System.out.println("bytes = " + bytes);
    }
}
