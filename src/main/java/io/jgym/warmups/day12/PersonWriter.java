package io.jgym.warmups.day12;

import java.io.*;

public class PersonWriter {
    public static void main(String... args) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("persons.bin")
                )
        )) {
            out.writeObject(new PersonClass("Heinz", "Kabutz", 48));
            out.writeObject(new PersonRecord("Heinz", "Kabutz", 48));
            out.writeObject(new PersonClass("John", "Green", -49));
            out.writeObject(new PersonRecord("John", "Green", -49));
            out.writeObject(null);
        }
    }
}
