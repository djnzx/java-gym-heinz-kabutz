package io.jgym.warmups.day06;

import org.w3c.dom.*;

import java.util.*;
import java.util.function.*;

public class NodeListSpliterator implements Spliterator<Node> {
    private final NodeList list;
    private int index;        // current index, modified on advance/split
    private final int fence;  // one past last index

    public NodeListSpliterator(NodeList list) {
        this(list, 0, list.getLength());
    }

    private NodeListSpliterator(NodeList list, int index, int fence) {
        this.list = list;
        this.index = index;
        this.fence = fence;
    }

    public boolean tryAdvance(Consumer<? super Node> action) {
        Objects.requireNonNull(action);
        if (index >= 0 && index < fence) {
            Node node = list.item(index++);
            action.accept(node);
            return true;
        }
        return false;
    }

    public NodeListSpliterator trySplit() {
        int lo = index, mid = (lo + fence) >>> 1;
        return (lo >= mid)
                ? null
                : new NodeListSpliterator(list, lo, index = mid);
    }

    public long estimateSize() {
        return fence - index;
    }

    public int characteristics() {
        return 0;
    }
}
