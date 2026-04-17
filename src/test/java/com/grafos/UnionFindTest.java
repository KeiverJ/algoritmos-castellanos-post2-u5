package com.grafos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnionFindTest {

    @Test
    void testFindSingle() {
        UnionFind uf = new UnionFind(5);
        assertEquals(0, uf.find(0));
        assertEquals(2, uf.find(2));
    }

    @Test
    void testUnion() {
        UnionFind uf = new UnionFind(5);
        uf.union(0, 1);
        assertEquals(uf.find(0), uf.find(1));
        assertNotEquals(uf.find(0), uf.find(2));
    }

    @Test
    void testUnionSameSet() {
        UnionFind uf = new UnionFind(3);
        uf.union(0, 1);
        uf.union(1, 2);
        assertEquals(uf.find(0), uf.find(1));
        assertEquals(uf.find(1), uf.find(2));
    }

    @Test
    void testPathSplitting() {
        UnionFind uf = new UnionFind(5);
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(2, 3);
        uf.find(0);
        uf.find(3);
        assertEquals(uf.find(0), uf.find(3));
    }

    @Test
    void testUnionBySize() {
        UnionFind uf = new UnionFind(10);
        uf.union(0, 1);
        uf.union(0, 2);
        uf.union(0, 3);
        uf.union(4, 5);
        uf.union(4, 6);
        uf.union(0, 4);
        assertEquals(uf.find(0), uf.find(5));
    }
}
