package io.jgym.warmups.day08;

import java.util.concurrent.*;

public class DaemonThreadFactory implements ThreadFactory {
    private final ThreadFactory defaultFactory = Executors.defaultThreadFactory();

    public Thread newThread(Runnable r) {
        Thread thread = defaultFactory.newThread(r);
        thread.setDaemon(true);
        return thread;
    }
}
