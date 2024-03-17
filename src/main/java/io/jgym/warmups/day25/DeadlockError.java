package io.jgym.warmups.day25;

public class DeadlockError extends Error {
    private static final long serialVersionUID = 1L;
    private final Thread thread;

    public DeadlockError(Thread thread) {
        super("Deadlock involving thread: " + thread);
        this.thread = thread;
    }

    public Thread getThread() {
        return thread;
    }
}
