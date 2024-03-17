package io.jgym.warmups.day10;

public interface Friendly {
    default void warmGreeting(String name) {
        // Thanks Thomas :-)
        System.out.println("Good morning, " + name + "!");
    }
    void hug();
    void highFive();
}