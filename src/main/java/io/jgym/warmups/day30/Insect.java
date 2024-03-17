package io.jgym.warmups.day30;

public abstract class Insect {
    public Insect() {
        System.out.println("Inside Insect() Constructor");
        printDetails();  // don't ever do this!
    }

    public void printDetails() {
        System.out.println("Just an insect");
    }
}

