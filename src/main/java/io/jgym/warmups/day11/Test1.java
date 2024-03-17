package io.jgym.warmups.day11;

// https://www.javaspecialists.eu/archive/Issue245-Surprising--Cast.html
public class Test1 { // JLS 15.7.1
    public static void main(String... args) {
        // 3, 6, 9, ?
        int i = 2;
        int j = (i = 3) * i;
        System.out.println(j);
    }
}
