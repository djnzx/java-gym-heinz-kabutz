package io.jgym.warmups.day01;

import java.io.*;
import java.net.*;

class SocketFactory {
    private static final int REUSE_LIMIT = 16_000;
    private static boolean SIMPLE = false;

    private int reuseCount;

    // Make this virtual IP with:
    // MacOS: sudo ifconfig en0 alias 10.0.0.1  # repeat for each address needed
    // Ubuntu: sudo ip address add 10.0.0.0/8 dev lo  # adds from 10.0.0.0 to 10.255.255.255
    private static final String IP_1_TO_2 = "10.0.";
    private int ip3 = 0;
    private int ip4 = 1;
    InetAddress inetAddress;

    public Socket nextSocket(String host, int port) throws IOException {
        if (SIMPLE)
            return new Socket(host, port);

        if (reuseCount == 0) {
            try {
                inetAddress = InetAddress.getByName(IP_1_TO_2 + ip3 + "." + ip4);
                System.out.printf("Changed to %s%n", inetAddress);
                ip4++;
                if (ip4 == 256) {
                    ip4 = 0;
                    ip3++;
                }
            } catch (Throwable t) {
                throw new IllegalStateException("No more addresses to use");
            }
        }
        reuseCount = (reuseCount + 1) % REUSE_LIMIT;
        return new Socket(host, port, inetAddress, 0);
    }
}
