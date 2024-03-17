package io.jgym.warmups.day30;

// https://www.javaspecialists.eu/archive/Issue062-The-Link-to-the-Outer-Class.html
public class Beetle extends Insect {
    private final int legs;

    public Beetle(int legs) {
        System.out.println("Inside Beetle() Constructor");
        this.legs = legs;
    }

    public void printDetails() {
        System.out.println("The beetle has " + legs + " legs");
        if (legs < 6) {
            System.out.println("Ouch");
        }
    }
}
