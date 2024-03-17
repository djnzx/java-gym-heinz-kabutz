package io.jgym.warmups.day30;

public class NestedBug2 {
    private Integer wings = new Integer(2);

    public NestedBug2() {
        new ComplexBug();
    }

    private class ComplexBug extends Insect {
        public void printDetails() {
            if (wings != null) { // line 12
                System.out.println(wings);
            }
        }
    }

    public static void main(String[] arguments) {
        new NestedBug2();
    }
}
