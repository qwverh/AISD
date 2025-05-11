package AlgotithmDijkstra;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Dijkstra {
    static class Edge {
        int target;
        int weight;

        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    //генерация случайного ориентированного графа
    public static List<List<Edge>> generateGraph(int V, int E) {
        List<List<Edge>> graph = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        int edgesAdded = 0;
        Set<String> addedEdges = new HashSet<>(); //храним "source_target"

        while (edgesAdded < E) {
            int source = rand.nextInt(V);
            int target = rand.nextInt(V);

            if (source != target) {
                String edgeKey = source + "_" + target;

                if (!addedEdges.contains(edgeKey)) { //Если ребро ещё не добавлено
                    int weight = 1 + rand.nextInt(100);
                    graph.get(source).add(new Edge(target, weight));
                    addedEdges.add(edgeKey);
                    edgesAdded++;
                }
            }
        }
        return graph;
    }

    //агоритм Дейкстры
    public static void runDijkstra(List<List<Edge>> graph,int start, String filename, PrintWriter summaryWriter) throws IOException {
        int V = graph.size();
        int[] distances = new int[V];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        int iterations = 0; // Счетчик итераций
        long startTime = System.nanoTime();

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            if (current.distance > distances[u]) continue;

            for (Edge edge : graph.get(u)) {
                iterations++; //увеличиваем счетчик
                int v = edge.target;
                int newDistance = distances[u] + edge.weight;

                if (newDistance < distances[v]) {
                    distances[v] = newDistance;
                    pq.add(new Node(v, newDistance));
                }
            }
        }

        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;

        int totalEdges = 0;
        for (List<Edge> edges : graph) {
            totalEdges += edges.size();
        }

        //Сохранение результатов
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.printf("Vertices: %d\n", V);
            writer.printf("Edges: %d\n", totalEdges);
            writer.printf("Time: %.3f ms\n", durationMs);
            writer.printf("Iterations: %d\n", iterations);
            writer.println("Vertex Distance");
            for (int i = 0; i < V; i++) {
                writer.println(i + " " + (distances[i] == Integer.MAX_VALUE ? 0 : distances[i]));
            }
        }

        //запись в сводный файл
        summaryWriter.printf("%d;%d;%.3f\n", V, iterations, durationMs);
    }
}
