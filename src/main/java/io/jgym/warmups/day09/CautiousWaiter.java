package io.jgym.warmups.day09;

import io.jgym.warmups.day08.*;

import java.util.concurrent.*;

public class CautiousWaiter {
    private boolean ready = false;

    public synchronized void waitForIt() {
        boolean interrupted = Thread.interrupted();
        while(!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                interrupted = true;
            }
        }
        System.out.println("Now we are ready!");
        if (interrupted) Thread.currentThread().interrupt();
    }

    public synchronized void waitForItBroken() {
        while(!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Now we are ready!");
    }

    public synchronized void goGoGo() {
        ready = true;
        notifyAll();
    }

    public static void main(String... args) {
        var timer = Executors.newScheduledThreadPool(1, new DaemonThreadFactory());
        var cw = new CautiousWaiter();
        timer.schedule(cw::goGoGo, 1, TimeUnit.SECONDS);
        var main = Thread.currentThread();
        timer.schedule(main::interrupt, 500, TimeUnit.MILLISECONDS);
        cw.waitForIt();
    }
}
