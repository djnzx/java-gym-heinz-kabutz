package io.jgym.warmups.day26;

import java.lang.reflect.*;

public class DontPanic {
    private final int meaningOfLife = 42;
    private final int happyness;
    private int age;

    public DontPanic(int happyness, int age) {
        this.happyness = happyness;
        this.age = age;
    }

    public String toString() {
        return "DontPanic{" +
                "meaningOfLife=" + meaningOfLife +
                ", happyness=" + happyness +
                ", age=" + age +
                '}';
    }

    public int getMeaningOfLife() {
        return meaningOfLife;
    }
}
