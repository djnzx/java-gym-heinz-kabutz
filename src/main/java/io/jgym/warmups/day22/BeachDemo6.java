package io.jgym.warmups.day22;

import java.util.*;
import java.util.concurrent.*;

public class BeachDemo6 {
    public static void main(String... args) {
        var items = new ConcurrentLinkedQueue<String>();
        Collections.addAll(items, "sunblock", "bucket", "raki", "razor", "umbrella",
                "rage", "surfboard");
        Iterator<String> en = items.iterator(); // weakly-consistent iterator
        while (en.hasNext()) {
            String item = en.next();
            if (item.startsWith("ra")) items.remove(item);
        }
        System.out.println("items = " + items);
    }
}
