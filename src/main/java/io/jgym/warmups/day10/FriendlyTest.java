package io.jgym.warmups.day10;

import java.lang.invoke.*;
import java.lang.reflect.*;

public class FriendlyTest {
    public static void main(String... args) throws Throwable {
//        var friendly = new Friendly() {};
//        friendly.warmGreeting("John");

        var method = Friendly.class.getMethod("warmGreeting", String.class);
//        method.invoke(null, "Raj");

        var dummy = Proxy.newProxyInstance(
                Friendly.class.getClassLoader(),
                new Class<?>[] { Friendly.class },
                (proxy, method1, args1) -> {
                    throw new IllegalStateException();
                }
        );
//        method.invoke(dummy, "Stelios");

        var mh = MethodHandles.lookup().unreflectSpecial(method, Friendly.class);
        mh.invoke(dummy, "Stelios");
    }
}
