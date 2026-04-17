package com.grafos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BellmanFordTest {

    @Test
    void testBellmanFordBasic() {
        int[][] edges = {
            {0, 1, 10},
            {0, 2, 5},
            {1, 2, 2},
            {1, 3, 1},
            {2, 3, 9},
            {2, 4, 3},
            {3, 4, 4}
        };

        var result = BellmanFord.run(edges, 5, 0);

        assertFalse(result.hasNegCycle());
        assertEquals(0, result.dist()[0]);
        assertEquals(7, result.dist()[1]);
        assertEquals(5, result.dist()[2]);
        assertEquals(8, result.dist()[3]);
        assertEquals(8, result.dist()[4]);
    }

    @Test
    void testBellmanFordNegativeWeights() {
        int[][] edges = {
            {0, 1, 1},
            {0, 2, 4},
            {1, 2, -3},
            {1, 3, 2},
            {2, 3, 3}
        };

        var result = BellmanFord.run(edges, 4, 0);

        assertFalse(result.hasNegCycle());
        assertEquals(0, result.dist()[0]);
        assertEquals(1, result.dist()[1]);
        assertEquals(-2, result.dist()[2]);
        assertEquals(1, result.dist()[3]);
    }

    @Test
    void testBellmanFordNegativeCycle() {
        int[][] edges = {
            {0, 1, 1},
            {1, 2, -2},
            {2, 0, -2}
        };

        var result = BellmanFord.run(edges, 3, 0);

        assertTrue(result.hasNegCycle());
    }

    @Test
    void testBellmanFordNoNegativeCycle() {
        int[][] edges = {
            {0, 1, 1},
            {1, 2, 2},
            {2, 0, 3}
        };

        var result = BellmanFord.run(edges, 3, 0);

        assertFalse(result.hasNegCycle());
    }

    @Test
    void testBellmanFordUnreachable() {
        int[][] edges = {
            {0, 1, 5}
        };

        var result = BellmanFord.run(edges, 3, 0);

        assertFalse(result.hasNegCycle());
        assertEquals(0, result.dist()[0]);
        assertEquals(5, result.dist()[1]);
        assertEquals(Integer.MAX_VALUE, result.dist()[2]);
    }
}
