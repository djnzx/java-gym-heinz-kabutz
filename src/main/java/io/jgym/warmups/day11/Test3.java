package io.jgym.warmups.day11;

// https://www.javaspecialists.eu/archive/Issue245-Surprising--Cast.html
public class Test3 { // JLS 15.7.1
    public static void main(String... args) {
        // 1, 2, ?
        int j = 1;
        try {
            int i = forgetIt() / (j = 2);
        } catch (Exception e) {
            System.out.println(j);
        }
    }

    static int forgetIt() throws Exception {
        throw new Exception("I'm outta here!");
    }
}
