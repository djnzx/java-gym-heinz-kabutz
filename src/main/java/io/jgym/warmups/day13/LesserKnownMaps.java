package io.jgym.warmups.day13;

import java.util.*;
import java.util.concurrent.*;

public class LesserKnownMaps {
    public static void main(String... args) {
        Map<?, ?>[] maps = {
                new EnumMap<>(Thread.State.class),
                new ConcurrentSkipListMap<>(),
                new LinkedHashMap<>(),
                new IdentityHashMap<>(),
                new WeakHashMap<>()
        };
    }
}
