package io.jgym.warmups.day15;

// From articles:
//
// https://www.javaspecialists.eu/archive/Issue277-Strings-with-Zero-HashCode.html
// https://www.javaspecialists.eu/archive/Issue278-Free-Memory.html
// https://horstmann.com/unblog/2020-04-02/

public class FunWithString {
    private static int leak;

    public static void main(String... args) {
        String s = "democrats understood clouting";
        System.out.println(s.hashCode());
        System.out.println((s + s).hashCode());
        System.out.println((s + s + s).hashCode());

        String[] strings = new String[20];
        for (int i = 0; i < strings.length; i++) {
            s += s;
            strings[i] = s;
            strings[i].hashCode();
        }

        // slow up to Java 12, fast after that
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            long time = System.nanoTime();
            try {
                for (int j = 0; j < 10_000; j++) {
                    leak = string.hashCode();
                }
            } finally {
                time = System.nanoTime() - time;
                System.out.printf("i=%d time = %dms%n", i, (time / 1_000_000));
            }
        }
    }
}
