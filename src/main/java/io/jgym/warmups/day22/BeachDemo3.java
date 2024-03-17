package io.jgym.warmups.day22;

import java.util.*;

public class BeachDemo3 {
    public static void main(String... args) {
        var items = new ArrayList<String>();
        Collections.addAll(items, "sunblock", "bucket", "raki", "razor", "umbrella",
                "rage", "surfboard");
        Iterator<String> en = items.iterator(); // fail-fast iterator
        while (en.hasNext()) {
            String item = en.next();
            if (item.startsWith("ra")) items.remove(item);
        }
        System.out.println("items = " + items);
    }
}
