package io.jgym.warmups.day12;

import java.io.*;

public record PersonRecord(String firstName, String lastName, int age)
        implements Serializable {
    public PersonRecord {
        if (age < 0) throw new IllegalArgumentException("age < 0");
    }
}
