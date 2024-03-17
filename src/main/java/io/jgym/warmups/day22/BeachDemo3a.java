package io.jgym.warmups.day22;

import java.util.*;

public class BeachDemo3a {
    public static void main(String... args) {
        var items = new ArrayList<String>();
        Collections.addAll(items, "sunblock", "bucket", "raki", "razor", "umbrella",
                "rage", "surfboard");
        Iterator<String> it = items.iterator(); // fail-fast iterator
        while (it.hasNext()) {
            String item = it.next();
            if (item.startsWith("ra")) it.remove();
        }
        System.out.println("items = " + items);
    }
}
