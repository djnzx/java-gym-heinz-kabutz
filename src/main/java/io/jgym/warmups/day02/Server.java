package io.jgym.warmups.day02;

import java.io.*;
import java.net.*;
import java.util.concurrent.locks.*;

public class Server {
    public static void main(String... args) throws Exception {
        ServerSocket ss = new ServerSocket(8080);
        for (int i = 0; i < 100; i++) {
            Socket s = ss.accept();

            Runnable chat = () -> {
                try {
                    System.out.println("Connected to " + s);
                    InputStream in = s.getInputStream();
                    OutputStream out = s.getOutputStream();
                    int data;
                    while ((data = in.read()) != -1) {
                        if (Character.isLetter(data)) data ^= ' ';
                        out.write(data);
                    }
                } catch (IOException consumeAndExit) {
                }
            };
            new Thread(chat).start();
        }
        LockSupport.park();
    }
}
