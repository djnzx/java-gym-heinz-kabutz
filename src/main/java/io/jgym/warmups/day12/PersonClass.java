package io.jgym.warmups.day12;

import java.io.*;

public class PersonClass implements Serializable {
    private final String firstName;
    private final String lastName;
    private final int age;

    public PersonClass(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (age < 0) throw new IllegalArgumentException("age < 0");
        this.age = age;
    }

    public String toString() {
        return "PersonClass{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
