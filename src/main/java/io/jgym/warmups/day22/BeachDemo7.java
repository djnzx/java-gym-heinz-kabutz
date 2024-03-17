package io.jgym.warmups.day22;

import java.util.*;
import java.util.concurrent.*;

public class BeachDemo7 {
    public static void main(String... args) {
        var items = new ConcurrentLinkedQueue<String>();
        Collections.addAll(items, "sunblock", "bucket", "raki", "razor", "umbrella",
                "rage", "surfboard");
        items.removeIf(item -> item.startsWith("ra"));
        System.out.println("items = " + items);
    }
}
