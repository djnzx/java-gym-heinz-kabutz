package io.jgym.warmups.day30;

public class Greeter {
    static {
        new Greeter();
    }

    private Greeter() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                greet();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private void greet() {
        System.out.println("Hello there!");
    }

    public static void main(String[] args) {

    }
}
