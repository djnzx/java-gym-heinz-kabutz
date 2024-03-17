package io.jgym.warmups.day12;

import java.io.*;

public class PersonReader {
    public static void main(String... args) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream("persons.bin")
                )
        )) {
            Object o;
            while((o = in.readObject()) != null) {
                System.out.println(o);
            }
        }
    }
}
