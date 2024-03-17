package io.jgym.warmups.day22;

import java.util.*;

public class BeachDemo2 {
    public static void main(String... args) {
        var items = new Vector<String>();
        Collections.addAll(items, "sunblock", "bucket", "raki", "razor", "umbrella",
                "rage", "surfboard");
        var toRemove = new Vector<String>();
        Enumeration<String> en = items.elements();
        while (en.hasMoreElements()) {
            String item = en.nextElement();
            if (item.startsWith("ra")) toRemove.add(item);
        }
        items.removeAll(toRemove);
        System.out.println("items = " + items);
    }
}
