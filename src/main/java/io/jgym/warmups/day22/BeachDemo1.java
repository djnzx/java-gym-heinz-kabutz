package io.jgym.warmups.day22;

import java.util.*;

public class BeachDemo1 {
    public static void main(String... args) {
        var items = new Vector<String>();
        Collections.addAll(items, "sunblock", "bucket", "raki", "razor", "umbrella",
                "rage", "surfboard");
        Enumeration<String> en = items.elements(); // random nonsense
        while (en.hasMoreElements()) {
            String item = en.nextElement();
            if (item.startsWith("ra")) items.remove(item);
        }
        System.out.println("items = " + items);
    }
}
