package io.jgym.warmups.day11;

public class JoshBlochPuzzler {
    // Puzzler: What does it print? Why?
    // https://twitter.com/joshbloch/status/1330266449844310024
    public static void main(String... args) {
        short i = -1, j = 0; // short is 16 bits
        while (i != 0) {
//            i >>>= 1;  // >>> doesn't do sign extension
            i = (short) ((i & 0xffff) >>> 1);  // >>> doesn't do sign extension
            j++;
        }
        System.out.println(j);
    }
}
// https://javaspecialists.teachable.com/p/bits-and-bytes