package io.jgym.warmups.day22;

import java.util.*;

public class BeachDemo4 {
    public static void main(String... args) {
        var items = new ArrayList<String>();
        Collections.addAll(items, "sunblock", "bucket", "raki", "razor", "umbrella",
                "rage", "surfboard");
        var toRemove = new ArrayList<String>();
        Iterator<String> en = items.iterator();
        while (en.hasNext()) {
            String item = en.next();
            if (item.startsWith("ra")) toRemove.add(item);
        }
        items.removeAll(toRemove);
        System.out.println("items = " + items);
    }
}
