package io.jgym.warmups.day04;

import java.util.*;
import java.util.concurrent.*;

public class Queues {
    public static void main(String... args) {
        Queue<?>[] queues = {
                // not threadsafe
                new LinkedList<>(),
                new ArrayDeque<>(),
                new PriorityQueue<>(),

                // threadsafe
                new ConcurrentLinkedQueue<>(),
                new ConcurrentLinkedDeque<>(),

                new LinkedBlockingQueue<>(), // Fixed thread pool
                new ArrayBlockingQueue<>(10),
                new LinkedBlockingDeque<>(),

                new LinkedTransferQueue<>(), // ForkJoinPool

                // specialized
                new SynchronousQueue<>(), // Cached thread pool
                new DelayQueue<>(), // scheduled thread pool
                new PriorityBlockingQueue<>(), // heap
        };
    }
}
