package io.jgym.warmups.day21;

import io.jgym.warmups.day08.*;
import sun.misc.*;

import java.lang.reflect.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ExceptionThrower {
    public static void rethrow(Throwable t) {
        sneakyThrow1(t);
//        sneakyThrow1a(t);
//        sneakyThrow2(t);
//        sneakyThrow3(t);
//        sneakyThrow4(t);
    }

    private static final ExecutorService thrower =
            Executors.newSingleThreadExecutor(new DaemonThreadFactory());

    private static void sneakyThrow1(Throwable t) {
        Thread current = Thread.currentThread();
        thrower.submit(() -> {
            try {
                Method stop0 = Thread.class.getDeclaredMethod("stop0", Object.class);
                stop0.setAccessible(true);
                stop0.invoke(current, t);
            } catch (ReflectiveOperationException ignore) {
            }
        });
        LockSupport.park();
    }

    private static void sneakyThrow1a(Throwable t) {
        // Thread.currentThread().stop(t); // <--- this worked in Java 7
    }

    private static final ThreadLocal<Throwable> toThrow = new ThreadLocal<>();

    private static class ThrowerClass {
        public ThrowerClass() throws Throwable {
            throw toThrow.get();
        }
    }

    private static void sneakyThrow2(Throwable t) {
        toThrow.set(t);
        try {
            ThrowerClass.class.newInstance();
        } catch (ReflectiveOperationException ignore) {
        } finally {
            toThrow.remove();
        }
    }

    private static void sneakyThrow3(Throwable t) {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            unsafe.throwException(t);
        } catch (ReflectiveOperationException ignore) {
        }
    }

    private static void sneakyThrow4(Throwable t) {
        ExceptionThrower.<RuntimeException>uncheckedThrow(t);
    }

    /**
     * The sneaky part of sneaky throw, relying on generics
     * limitations to evade compiler complaints about rethrowing
     * unchecked exceptions.
     */
    @SuppressWarnings("unchecked")
    static <T extends Throwable>
    void uncheckedThrow(Throwable t) throws T {
        if (t != null)
            throw (T) t; // rely on vacuous cast
        else
            throw new Error("Unknown Exception");
    }
}
