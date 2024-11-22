package daa;

import java.util.Arrays;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    int level, cost, bound;
    int[] assignment;

    public Node(int level, int cost, int bound, int[] assignment) {
        this.level = level;
        this.cost = cost;
        this.bound = bound;
        this.assignment = assignment;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.bound, other.bound);
    }
}

public class Assignment6 {
    private static int calculateBound(int[][] cost, int[] assignment) {
        int n = cost.length, totalCost = 0;
        boolean[] assigned = new boolean[n];

        for (int task : assignment)
            if (task != -1) assigned[task] = true;

        for (int i = 0; i < n; i++) {
            if (assignment[i] == -1) {
                int rowMin = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++)
                    if (!assigned[j]) rowMin = Math.min(rowMin, cost[i][j]);
                totalCost += rowMin;
            }
        }
        return totalCost;
    }

    public static void branchAndBound(int[][] cost) {
        int n = cost.length;
        int[] bestAssignment = new int[n];
        Arrays.fill(bestAssignment, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(-1, 0, calculateBound(cost, bestAssignment), bestAssignment));

        int minCost = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.level == n - 1) {
                if (current.cost < minCost) {
                    minCost = current.cost;
                    bestAssignment = current.assignment.clone();
                }
                continue;
            }

            int nextLevel = current.level + 1;
            for (int j = 0; j < n; j++) {
                if (!isAssigned(current.assignment, j)) {
                    int[] newAssignment = current.assignment.clone();
                    newAssignment[nextLevel] = j;
                    int newCost = current.cost + cost[nextLevel][j];
                    if (newCost < minCost) {
                        int newBound = newCost + calculateBound(cost, newAssignment);
                        pq.add(new Node(nextLevel, newCost, newBound, newAssignment));
                    }
                }
            }
        }

        System.out.println("Minimum Cost: " + minCost);
        System.out.println("Optimal Assignment: " + Arrays.toString(bestAssignment));
    }

    private static boolean isAssigned(int[] assignment, int task) {
        for (int t : assignment)
            if (t == task) return true;
        return false;
    }

    public static void main(String[] args) {
        int[][] costMatrix = {
            {9, 2, 7, 8},
            {6, 4, 3, 7},
            {5, 8, 1, 8},
            {7, 6, 9, 4}
        };
        branchAndBound(costMatrix);
    }
}
