package com.grafos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class TopSortTest {

    @Test
    void testKahnBasic() {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 6; i++) adj.add(new ArrayList<>());
        adj.get(5).add(2);
        adj.get(5).add(0);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        List<Integer> order = TopSort.kahn(adj, 6);

        assertEquals(6, order.size());
        int idx5 = order.indexOf(5);
        int idx4 = order.indexOf(4);
        int idx2 = order.indexOf(2);
        int idx3 = order.indexOf(3);
        int idx0 = order.indexOf(0);
        int idx1 = order.indexOf(1);
        assertTrue(idx5 < idx2);
        assertTrue(idx5 < idx0);
        assertTrue(idx4 < idx0);
        assertTrue(idx4 < idx1);
        assertTrue(idx2 < idx3);
        assertTrue(idx3 < idx1);
    }

    @Test
    void testKahnWithCycle() {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 3; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(0);

        assertThrows(IllegalArgumentException.class, () -> {
            TopSort.kahn(adj, 3);
        });
    }

    @Test
    void testKahnSimple() {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 3; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(1).add(2);

        List<Integer> order = TopSort.kahn(adj, 3);

        assertEquals(3, order.size());
        int idx0 = order.indexOf(0);
        int idx1 = order.indexOf(1);
        int idx2 = order.indexOf(2);
        assertTrue(idx0 < idx1);
        assertTrue(idx1 < idx2);
    }
}
