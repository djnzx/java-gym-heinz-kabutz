package io.jgym.warmups.day26;

import io.jgym.warmups.day26.*;

import java.lang.invoke.*;
import java.lang.reflect.*;

public class VarhandleDemo {
    // not writing to final fields
    // atomic updates
    // not boxing
    public static void main(String... args) throws ReflectiveOperationException {
        var arthur = new DontPanic(1000, 42);
        System.out.println("arthur = " + arthur);
        meaningOfLifeField.set(arthur, 6);
        happynessField.set(arthur, -1000);
        ageField.set(arthur, 43);
        System.out.println("arthur = " + arthur);
        System.out.println("arthur.getMeaningOfLife() = " + arthur.getMeaningOfLife());
        System.out.println(meaningOfLifeField.get(arthur));

        var ford = new DontPanic(1000, 42);
        System.out.println("ford = " + ford);
        // MEANING_OF_LIFE.set(ford, 6);
        // HAPPYNESS.set(ford, -1000);
        AGE.set(ford, 43);
        System.out.println("ford = " + ford);

        int currentAge;
        do {
            currentAge = (int) AGE.get(ford);
        } while(!AGE.compareAndSet(ford, currentAge, currentAge + 1));
        System.out.println("ford = " + ford);

        while(true) {
            // happynessField.get(arthur);
            HAPPYNESS.get(ford);
        }
    }

    private static final Field meaningOfLifeField, happynessField, ageField;

    static {
        try {
            meaningOfLifeField = DontPanic.class.getDeclaredField("meaningOfLife");
            happynessField = DontPanic.class.getDeclaredField("happyness");
            ageField = DontPanic.class.getDeclaredField("age");
            meaningOfLifeField.setAccessible(true);
            happynessField.setAccessible(true);
            ageField.setAccessible(true);
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    private static final VarHandle MEANING_OF_LIFE, HAPPYNESS, AGE;

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(
                    DontPanic.class, MethodHandles.lookup()
            );
            MEANING_OF_LIFE = lookup.findVarHandle(DontPanic.class, "meaningOfLife", int.class);
            HAPPYNESS = lookup.findVarHandle(DontPanic.class, "happyness", int.class);
            AGE = lookup.findVarHandle(DontPanic.class, "age", int.class);
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }
}