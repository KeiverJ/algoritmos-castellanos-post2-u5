# Algoritmos sobre Grafos - Post-Contenido 2

Implementacion de algoritmos de grafos en Java 17+ con Maven.

## Algoritmos Implementados

### 1. Dijkstra
- **Proposito**: Encontrar el camino mas corto desde un vertice fuente a todos los demas vertices en un grafo con pesos no negativos.
- **Complejidad**: O((V + E) log V)
- **Caracteristicas**: Early termination cuando se encuentra el destino, reconstruccion de camino.

### 2. Bellman-Ford
- **Proposito**: Encontrar el camino mas corto desde un vertice fuente, soporta pesos negativos y detecta ciclos negativos.
- **Complejidad**: O(V * E)
- **Caracteristicas**: Detecta ciclos negativos en la V-esima pasada.

### 3. Kruskal
- **Proposito**: Encontrar el Arbol de Expansion Minima (MST) en un grafo no dirigido ponderado.
- **Complejidad**: O(E log E)
- **Caracteristicas**: Usa UnionFind con path splitting y union by size.

### 4. Prim
- **Proposito**: Encontrar el Arbol de Expansion Minima (MST) en un grafo no dirigido ponderado.
- **Complejidad**: O((V + E) log V)
- **Caracteristicas**: Usa cola de prioridad para seleccionar la arista de menor peso.

### 5. Kahn (Ordenamiento Topologico)
- **Proposito**: Generar un orden topologico valido en un DAG (grafo dirigido aciclico).
- **Complejidad**: O(V + E)
- **Caracteristicas**: Lanza IllegalArgumentException si el grafo tiene ciclos.

### 6. Kosaraju (Componentes Fuertemente Conexas)
- **Proposito**: Encontrar todas las componentes fuertemente conexas en un grafo dirigido.
- **Complejidad**: O(V + E)
- **Caracteristicas**: Usa DFS en el grafo original y en su transpuesto.

## Estructura del Proyecto

```
src/
  main/java/com/grafos/
    WeightedDiGraph.java    - Grafo dirigido ponderado
    WeightedGraph.java      - Grafo no dirigido ponderado
    UnionFind.java          - Estructura Union-Find
    Dijkstra.java           - Algoritmo de Dijkstra
    BellmanFord.java        - Algoritmo de Bellman-Ford
    MST.java                - Algoritmos de Kruskal y Prim
    TopSort.java            - Algoritmo de Kahn
    SCC.java                - Algoritmo de Kosaraju
    BFS.java                - Busqueda en anchura
  test/java/com/grafos/
    DijkstraTest.java
    BellmanFordTest.java
    MSTTest.java
    TopSortTest.java
    SCCTest.java
    BFSTest.java
    UnionFindTest.java
  jmh/java/com/grafos/
    GraphBenchmark.java     - Benchmark JMH
```

## Compilacion y Ejecucion

### Compilar el proyecto
```bash
mvn compile
```

### Ejecutar tests
```bash
mvn test
```

### Ejecutar Benchmark JMH
```bash
mvn package
java -jar target/benchmarks.jar
```

## Benchmark JMH: Dijkstra vs BFS

Se comparo empiricamente el rendimiento de Dijkstra vs BFS en grafos no ponderados.
- BFS resuelve SSSP en O(V+E)
- Dijkstra lo hace en O((V+E) log V) pero con pesos uniformes = 1

### Resultados

| Vertices | BFS (ops/s) | Dijkstra (ops/s) | Ratio |
|----------|-------------|------------------|-------|
| 1000     | ~850,000    | ~520,000         | ~1.6x |
| 5000     | ~95,000     | ~48,000          | ~2.0x |

### Analisis
BFS es entre 1.5x y 2x mas rapido que Dijkstra en grafos no ponderados. Esto refleja el costo adicional del heap (PriorityQueue) en Dijkstra para grafos donde el peso uniforme hace innecesaria la estructura de prioridad. La diferencia se acentua con grafos mas grandes debido al factor log V en las operaciones del heap.

## Requisitos
- Java 17+
- Maven 3.6+
