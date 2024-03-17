package io.jgym.warmups.playground;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

// https://twitter.com/volker_simonis/status/1326592733008564226
public class Main {
    private static ExecutorService executor = Executors.newFixedThreadPool(2);
    private static int iterations = 10000000;

    public static class Runner {
        // writes to canceled happen before a CAS on suspended
        // reads on canceled happen after a CAS on suspended
        private boolean canceled = false;
        // an optimistic lock. false == locked, true == unlocked
        private final AtomicBoolean suspended = new AtomicBoolean(false);

        private volatile boolean result = false;
        private final CountDownLatch latch = new CountDownLatch(1);

        public void start() {
            // start two tasks that synchronize on suspended and canceled
            // this is a minimized version of a synchronization mechanism in cats effect
            Future<?> f1 = executor.submit(() -> {
                try {
                    latch.await();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // assumption: this task already has the lock

                // release the lock
                suspended.compareAndSet(false, true);
                if (canceled) {
                    // double-check, the other thread may have set canceled but failed the CAS check,
                    // so we'll try to reacquire the lock
                    if (suspended.compareAndSet(true, false)) {
                        result = true;
                    }
                }
            });

            Future<?> f2 = executor.submit(() -> {
                try {
                    latch.await();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                canceled = true;
                // attempt to acquire the lock to set result.
                // regardless of whether the CAS succeeds or not, the write to canceled should be published
                if (suspended.compareAndSet(true, false)) {
                    result = true;
                }
            });

            // signal threads to proceed
            latch.countDown();

            try {
                // wait for tasks to complete
                f1.get();
                f2.get();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // after both tasks have completed, result should be true
            if (result != true) {
                System.out.println("STUCK");
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < iterations; i++) {
            Runner runner = new Runner();
            runner.start();
        }
        System.exit(0);
    }
}
