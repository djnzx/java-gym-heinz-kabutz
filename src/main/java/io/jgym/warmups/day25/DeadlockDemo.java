package io.jgym.warmups.day25;

import java.util.concurrent.*;

public class DeadlockDemo {
    public static void main(String... args) throws InterruptedException {
        System.out.println("Runtime.getRuntime().availableProcessors() = " +
                Runtime.getRuntime().availableProcessors());
        BankAccount fnb = new SlowBankAccount(1000);
        BankAccount absa = new SlowBankAccount(1000);

        DeadlockTester tester = new DeadlockTester();

        tester.checkThatCodeDoesNotDeadlock(() -> {
            ExecutorService pool = Executors.newFixedThreadPool(2);
            Future<?> transfer1 = pool.submit(() -> fnb.transferTo(absa, 100));
            Future<?> transfer2 = pool.submit(() -> absa.transferTo(fnb, 100));
            try {
                transfer1.get();
                transfer2.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            pool.shutdown();
        });
    }
}
