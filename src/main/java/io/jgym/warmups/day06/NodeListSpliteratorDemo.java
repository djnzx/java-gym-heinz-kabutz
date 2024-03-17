package io.jgym.warmups.day06;

import org.w3c.dom.Node;

import java.util.stream.*;

public class NodeListSpliteratorDemo {
    public static void main(String... args) {
        Stream<Node> sequential = StreamSupport.stream(new NodeListSpliterator(null), false);
        Stream<Node> parallel = StreamSupport.stream(new NodeListSpliterator(null), true);
    }
}
