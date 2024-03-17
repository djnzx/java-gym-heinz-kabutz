package io.jgym.warmups.day11;

// https://www.javaspecialists.eu/archive/Issue245-Surprising--Cast.html
public class Test2 { // JLS 15.7.1
    public static void main(String... args) {
        // 6, 7, 8, ?
        int a = 4;
        a += a = 3;
        System.out.println(a);
    }
}