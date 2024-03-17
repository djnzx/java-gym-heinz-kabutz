package io.jgym.warmups.day29;

import java.lang.management.*;
import java.util.*;
import java.util.concurrent.*;

// -Djava.util.concurrent.ForkJoinPool.common.threadFactory=io.jgym.warmups.day29.CPUMeasuringForkJoinThreadFactory
public class CPUMeasuringForkJoinThreadFactory implements ForkJoinPool.ForkJoinWorkerThreadFactory {
    private final ForkJoinPool.ForkJoinWorkerThreadFactory defaultFactory =
            ForkJoinPool.defaultForkJoinWorkerThreadFactory; // cannot be null

    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
        ForkJoinWorkerThread workerThread = defaultFactory.newThread(pool);
        System.out.println("workerThread created: " + workerThread);
        addWatchThread(workerThread);
        return workerThread;
    }

    private static void addWatchThread(Thread thread) {
        threads.put(thread, new ExecutionTime(thread));
    }

    private static final ThreadMXBean tmbean = ManagementFactory.getThreadMXBean();

    private final static Map<Thread, ExecutionTime> threads = new ConcurrentSkipListMap<>(
            Comparator.comparing(Thread::getName)
    );

    static {
        System.out.println("Using CPUMeasuringForkJoinThreadFactory for common pool");
        Runtime.getRuntime().addShutdownHook(
                new Thread(CPUMeasuringForkJoinThreadFactory::dumpTimes));
    }

    public static void dumpTimes() {
        System.out.println("Worker Threads Summary");
        threads.entrySet()
                .stream()
                .forEach(entry ->
                        System.out.printf("\t%s, us=%dms, sy=%d%n",
                                entry.getKey().getName(),
                                entry.getValue().userTimeMs(),
                                entry.getValue().sysTimeMs()));
        System.out.println("\tUser Time Stats: " +
                threads.values()
                        .stream()
                        .mapToLong(ExecutionTime::userTimeMs)
                        .summaryStatistics());
        System.out.println("\tSystem Time Stats: " +
                threads.values()
                        .stream()
                        .mapToLong(ExecutionTime::sysTimeMs)
                        .summaryStatistics());
        System.out.println("\tTotal CPU Time: " +
                threads.values()
                        .stream()
                        .mapToLong(ExecutionTime::cpuTimeMs)
                        .sum());
    }

    public static void reset() {
        threads.replaceAll((thread, executionTime) -> new ExecutionTime(thread));
    }

    private static class ExecutionTime {
        private final long startCPU;
        private final long startUser;
        private final long threadId;

        public ExecutionTime(Thread thread) {
            startCPU = tmbean.getThreadCpuTime(thread.getId());
            startUser = tmbean.getThreadUserTime(thread.getId());
            this.threadId = thread.getId();
        }

        private long cpuTimeMs() {
            return (tmbean.getThreadCpuTime(threadId) - startCPU) / 1_000_000;
        }

        public long userTimeMs() {
            return (tmbean.getThreadUserTime(threadId) - startUser) / 1_000_000;
        }

        public long sysTimeMs() {
            return cpuTimeMs() - userTimeMs();
        }
    }
}
