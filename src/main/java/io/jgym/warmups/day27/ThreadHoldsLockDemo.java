package io.jgym.warmups.day27;

import java.util.stream.*;

// 1. Search for usages of Thread.holdsLock()
// 2. Explain why someone would use it
// 3. Explain ThreadHoldsLockDemo
// 4. Run demo, looking at interesting results and ea
public class ThreadHoldsLockDemo {
    private static int counter;
    public static void main(String... args) {
        for (int i = 0; i < 3; i++) {
            test(false, false);
            test(true, false);
            test(false, true);
            test(true, true);

            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    private static void test(boolean synchronize, boolean parallel) {
        System.out.println("Test with " + (synchronize ? "synchronized" : "holdsLock()")
                + " running " + (parallel ? "in parallel" : "sequentially"));
        for (int depth = 0; depth <= 10; depth++) {
            test(depth, synchronize, parallel);
        }
        System.out.println("counter = " + counter);
        counter = 0;
        System.out.println();
    }

    private static void test(int depth, boolean synchronize, boolean parallel) {
        long time = System.nanoTime();
        try {
            IntStream range = IntStream.range(0, 2_000_000);
            if (parallel) range = range.parallel();
            range.forEach(i -> {
                callActionSynchronized(depth, synchronize);
            });
        } finally {
            time = System.nanoTime() - time;
            System.out.printf("sequential depth=%d time = %dms%n",
                    depth, (time / 1_000_000));
        }
    }

    public static synchronized void callActionSynchronized(int depth, boolean synchronize) {
        action(depth, synchronize);
    }

    private static void action(int depth, boolean synchronize) {
        if (depth == 0) {
            counter++;
            return;
        }
        if (synchronize) {
            synchronized (ThreadHoldsLockDemo.class) {
                action(depth - 1, synchronize);
            }
        } else {
            assert Thread.holdsLock(ThreadHoldsLockDemo.class);
            action(depth - 1, synchronize);
        }
    }
}
