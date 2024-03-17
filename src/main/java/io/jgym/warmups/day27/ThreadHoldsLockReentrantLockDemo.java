package io.jgym.warmups.day27;

import java.util.concurrent.locks.*;
import java.util.stream.*;

public class ThreadHoldsLockReentrantLockDemo {
    private static final ReentrantLock lock = new ReentrantLock();
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
        System.out.println("Test with " + (synchronize ? "ReentrantLock" : "isHeldByCurrentThread()")
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

    public static  void callActionSynchronized(int depth, boolean synchronize) {
        lock.lock();
        try {
            action(depth, synchronize);
        } finally {
            lock.unlock();
        }
    }

    private static void action(int depth, boolean synchronize) {
        if (depth == 0) {
            counter++;
            return;
        }
        if (synchronize) {
                lock.lock();
                try {
                    action(depth - 1, synchronize);
                } finally {
                    lock.unlock();
                }
        } else {
            assert lock.isHeldByCurrentThread();
            action(depth - 1, synchronize);
        }
    }
}
