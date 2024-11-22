package daa;
import java.util.*;

public class Assignment4 {
    static class Edge {
        int dest, wt;
        Edge(int dest, int wt) {
            this.dest = dest;
            this.wt = wt;
        }
    }

    static class Pair implements Comparable<Pair> {
        int n, path;
        Pair(int n, int path) {
            this.n = n;
            this.path = path;
        }
        @Override
        public int compareTo(Pair p2) {
            return Integer.compare(this.path, p2.path);
        }
    }

    // Create graph and initialize edges
    public static void createGraph(ArrayList<Edge>[] graph) {
        graph[0].add(new Edge(1, 2));
        graph[0].add(new Edge(2, 4));
        graph[1].add(new Edge(3, 7));
        graph[1].add(new Edge(2, 1));
        graph[2].add(new Edge(4, 3));
        graph[3].add(new Edge(5, 1));
        graph[4].add(new Edge(3, 2));
        graph[4].add(new Edge(5, 5));
    }

    // Dijkstra's algorithm implementation
    public static void dijkstra(ArrayList<Edge>[] graph, int src) {
        int[] dist = new int[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        boolean[] vis = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            if (vis[curr.n]) continue;
            vis[curr.n] = true;

            // Update distances for neighbors
            for (Edge e : graph[curr.n]) {
                int newDist = dist[curr.n] + e.wt;
                if (newDist < dist[e.dest]) {
                    dist[e.dest] = newDist;
                    pq.add(new Pair(e.dest, newDist));
                }
            }
        }

        // Print the shortest distances
        System.out.println(Arrays.toString(dist));
    }

    public static void main(String[] args) {
        int v = 6;
        ArrayList<Edge>[] graph = new ArrayList[v];
        for (int i = 0; i < v; i++) graph[i] = new ArrayList<>();
        
        createGraph(graph);
        dijkstra(graph, 0);
    }
}
