package io.jgym.warmups.day30;

// cfr --hidebridgemethods false --innerclasses false
public class InnerClassMagic {
    private int i;

    private class Inner {
        {
            i = 42;
        }
    }

    public static void main(String[] args) {
        InnerClassMagic outer = new InnerClassMagic();
        Inner inner = outer.new Inner();
        System.out.println("outer.i = " + outer.i);
    }
}
