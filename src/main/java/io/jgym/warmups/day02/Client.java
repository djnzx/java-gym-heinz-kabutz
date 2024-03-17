package io.jgym.warmups.day02;

import java.io.*;
import java.net.*;
import java.util.concurrent.locks.*;

public class Client {
    public static void main(String... args) throws Exception {
        for (int i = 0; i < 100; i++) {
            Socket s = new Socket("localhost", 8080);
            Runnable chat = () -> {
                try {
                    System.out.println("Connected to " + s);
                    InputStream in = s.getInputStream();
                    OutputStream out = s.getOutputStream();
                    long progress = 0;
                    while (true) {
                        out.write('a');
                        in.read();
                        if (progress++ % 10_000 == 0) {
                            System.out.println(Thread.currentThread() + " progress = " + progress);
                        }
                        Thread.sleep(1);
                    }
                } catch (Exception consumeAndExit) {
                }
            };
            new Thread(chat).start();
        }
        LockSupport.park();
    }
}
