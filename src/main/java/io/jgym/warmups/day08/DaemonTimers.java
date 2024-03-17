package io.jgym.warmups.day08;

import java.util.*;
import java.util.concurrent.*;

public class DaemonTimers {
    public static void main(String... args) throws InterruptedException {
        Timer timer1 = new Timer(true);
        timer1.schedule(new TimerTask() {
            public void run() {
                showDaemonStatus();
            }
        }, 1000, 1000);

        var timer2 = Executors.newScheduledThreadPool(
                1, new DaemonThreadFactory());
        timer2.scheduleWithFixedDelay(DaemonTimers::showDaemonStatus,
                1, 1, TimeUnit.SECONDS);
        Thread.sleep(5500);
    }

    public static void showDaemonStatus() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is " +
                (Thread.currentThread().isDaemon() ? "" : "not ") + "a daemon thread");
    }
}
