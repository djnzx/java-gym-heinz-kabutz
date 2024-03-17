package io.jgym.warmups.day21;

import java.sql.*;

public class ExceptionThrowerDemo {
    public static void main(String... args) {
        Runnable job = () -> ExceptionThrower.rethrow(
                new SQLException("Database exploded"));

        try {
            job.run();
            throw new AssertionError("No exception at all");
        } catch (Exception e) {
            if (e instanceof SQLException) {
                System.out.println("Got ourselves a SQLException: " + e);
            } else {
                throw new AssertionError("Wrong exception: " + e.getClass());
            }
        }
    }
}
