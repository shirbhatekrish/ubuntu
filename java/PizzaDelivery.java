import java.util.*;

/**
 * PizzaDelivery.java
 * ------------------
 * Models nearby locations as a weighted graph and finds
 * the minimum delivery time (shortest path) from the shop
 * to all customer locations using Dijkstra's Algorithm.
 *
 * Works perfectly in Notepad / Ubuntu terminal.
 *
 * Compile: javac PizzaDelivery.java
 * Run:     java PizzaDelivery
 */

public class PizzaDelivery {
    private final int numLocations;
    private final int[][] timeMatrix; // adjacency matrix (travel time)
    private final Scanner sc = new Scanner(System.in);

    public PizzaDelivery(int n) {
        this.numLocations = n;
        this.timeMatrix = new int[n][n];

        // Initialize with "no path" as 0 or large number
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) timeMatrix[i][j] = 0;
            }
        }
    }

    // Add connection between locations
    public void addRoute(int src, int dest, int time) {
        if (src < 0 || dest < 0 || src >= numLocations || dest >= numLocations) {
            System.out.println("Invalid location index.");
            return;
        }
        timeMatrix[src][dest] = time;
        timeMatrix[dest][src] = time; // undirected
        System.out.println("Added route: " + (char)('A' + src) + " ↔ " + (char)('A' + dest) + " (" + time + " mins)");
    }

    // Display adjacency matrix
    public void displayMatrix() {
        System.out.println("\nAdjacency Matrix (Travel Time in Minutes):");
        System.out.print("     ");
        for (int i = 0; i < numLocations; i++) {
            System.out.print((char)('A' + i) + "   ");
        }
        System.out.println();
        for (int i = 0; i < numLocations; i++) {
            System.out.print((char)('A' + i) + " | ");
            for (int j = 0; j < numLocations; j++) {
                System.out.printf("%-3d ", timeMatrix[i][j]);
            }
            System.out.println();
        }
    }

    // Dijkstra's Algorithm to find shortest delivery times
    public void dijkstra(int start) {
        final int INF = Integer.MAX_VALUE;
        int[] dist = new int[numLocations];
        boolean[] visited = new boolean[numLocations];

        Arrays.fill(dist, INF);
        dist[start] = 0;

        for (int count = 0; count < numLocations - 1; count++) {
            int u = minDistance(dist, visited);
            if (u == -1) break; // all remaining are unreachable
            visited[u] = true;

            for (int v = 0; v < numLocations; v++) {
                if (!visited[v] && timeMatrix[u][v] != 0 && dist[u] != INF
                        && dist[u] + timeMatrix[u][v] < dist[v]) {
                    dist[v] = dist[u] + timeMatrix[u][v];
                }
            }
        }

        // Display shortest times
        System.out.println("\nMinimum delivery times from location " + (char)('A' + start) + ":");
        for (int i = 0; i < numLocations; i++) {
            if (dist[i] == INF)
                System.out.println("→ " + (char)('A' + i) + ": unreachable");
            else
                System.out.println("→ " + (char)('A' + i) + ": " + dist[i] + " minutes");
        }
    }

    // Find vertex with minimum distance value
    private int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < numLocations; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Menu-driven interface
    public void menu() {
        while (true) {
            System.out.println("\n===== PIZZA DELIVERY MENU =====");
            System.out.println("1. Display Adjacency Matrix");
            System.out.println("2. Add a new route");
            System.out.println("3. Find Minimum Delivery Time (Dijkstra)");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            String choiceStr = sc.nextLine().trim();
            if (choiceStr.isEmpty()) continue;
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number (1-4).");
                continue;
            }

            switch (choice) {
                case 1:
                    displayMatrix();
                    break;
                case 2:
                    System.out.print("Enter route (e.g., AB): ");
                    String route = sc.nextLine().trim().toUpperCase();
                    if (route.length() < 2) {
                        System.out.println("Invalid input.");
                        break;
                    }
                    int src = route.charAt(0) - 'A';
                    int dest = route.charAt(1) - 'A';
                    if (src < 0 || dest < 0 || src >= numLocations || dest >= numLocations) {
                        System.out.println("Invalid locations.");
                        break;
                    }
                    System.out.print("Enter time between " + route.charAt(0) + " and " + route.charAt(1) + ": ");
                    int time;
                    try {
                        time = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid time.");
                        break;
                    }
                    addRoute(src, dest, time);
                    break;
                case 3:
                    System.out.print("Enter starting location (A-" + (char)('A' + numLocations - 1) + "): ");
                    String startStr = sc.nextLine().trim().toUpperCase();
                    if (startStr.isEmpty()) break;
                    int start = startStr.charAt(0) - 'A';
                    if (start < 0 || start >= numLocations) {
                        System.out.println("Invalid starting location.");
                        break;
                    }
                    dijkstra(start);
                    break;
                case 4:
                    System.out.println("Exiting... have a great delivery!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        while (n <= 0 || n > 26) {
            System.out.print("Enter number of locations (1–26): ");
            try {
                n = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }

        PizzaDelivery city = new PizzaDelivery(n);
        System.out.println("Enter routes (e.g., AB 5 for A↔B = 5 mins). Type 'done' when finished.");
        while (true) {
            System.out.print("Route (or 'done'): ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;
            if (input.length() < 3) {
                System.out.println("Invalid input. Use format: AB 5");
                continue;
            }

            String[] parts = input.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Invalid format. Try: AB 5");
                continue;
            }

            String edge = parts[0].toUpperCase();
            int time;
            try {
                time = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number for time.");
                continue;
            }

            int src = edge.charAt(0) - 'A';
            int dest = edge.charAt(1) - 'A';
            if (src < 0 || dest < 0 || src >= n || dest >= n) {
                System.out.println("Invalid locations (A-" + (char)('A' + n - 1) + ").");
                continue;
            }
            city.addRoute(src, dest, time);
        }

        city.menu();
        sc.close();
    }
}
