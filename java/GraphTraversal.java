import java.util.*;

/**
 * GraphTraversal.java
 *
 * - Uses adjacency matrix for DFS
 * - Uses adjacency list (ArrayList[]) for BFS
 * - Interactive: build graph by entering edges, then use a menu to display and run BFS/DFS
 *
 * Compile:
 *   javac GraphTraversal.java
 * Run:
 *   java GraphTraversal
 */
public class GraphTraversal {

    private final int numVertices;
    private final int[][] adjMatrix;          // for DFS
    @SuppressWarnings("unchecked")
    private final ArrayList<Integer>[] adjList; // for BFS
    private final Scanner sc = new Scanner(System.in);

    // Constructor
    public GraphTraversal(int vertices) {
        this.numVertices = vertices;
        this.adjMatrix = new int[vertices][vertices];
        this.adjList = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    // Adds undirected edge between u and v (0-based indices)
    public void addEdge(int u, int v) {
        if (u < 0 || v < 0 || u >= numVertices || v >= numVertices) {
            System.out.println("Invalid vertex index.");
            return;
        }
        // matrix
        adjMatrix[u][v] = 1;
        adjMatrix[v][u] = 1;

        // list - avoid duplicate entries
        if (!adjList[u].contains(v)) adjList[u].add(v);
        if (!adjList[v].contains(u)) adjList[v].add(u);
    }

    // Display adjacency matrix
    public void displayMatrix() {
        System.out.println("\nAdjacency Matrix:");
        // header
        System.out.print("   ");
        for (int j = 0; j < numVertices; j++) {
            System.out.print((char)('A' + j) + " ");
        }
        System.out.println();
        for (int i = 0; i < numVertices; i++) {
            System.out.print((char)('A' + i) + ": ");
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Display adjacency list
    public void displayList() {
        System.out.println("\nAdjacency List:");
        for (int i = 0; i < numVertices; i++) {
            System.out.print((char)('A' + i) + ": ");
            // print neighbors as letters
            for (int nb : adjList[i]) {
                System.out.print((char)('A' + nb) + " ");
            }
            System.out.println();
        }
    }

    // DFS using adjacency matrix (recursive)
    public void dfs(int start) {
        if (!validVertex(start)) {
            System.out.println("Invalid start vertex.");
            return;
        }
        boolean[] visited = new boolean[numVertices];
        System.out.print("DFS starting from " + (char)('A' + start) + ": ");
        dfsRec(start, visited);
        System.out.println();
    }

    private void dfsRec(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print((char)('A' + v) + " ");
        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[v][i] == 1 && !visited[i]) {
                dfsRec(i, visited);
            }
        }
    }

    // BFS using adjacency list (iterative)
    public void bfs(int start) {
        if (!validVertex(start)) {
            System.out.println("Invalid start vertex.");
            return;
        }
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);

        System.out.print("BFS starting from " + (char)('A' + start) + ": ");

        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.print((char)('A' + u) + " ");
            // iterate neighbors in stored order
            for (int nb : adjList[u]) {
                if (!visited[nb]) {
                    visited[nb] = true;
                    q.add(nb);
                }
            }
        }
        System.out.println();
    }

    private boolean validVertex(int idx) {
        return idx >= 0 && idx < numVertices;
    }

    // Helper: parse an edge input string like "AB", "A B", "A,B", "a-b" etc.
    // Returns pair int[2] or null if invalid.
    private int[] parseEdge(String line) {
        if (line == null) return null;
        // keep only letters
        StringBuilder letters = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (Character.isLetter(c)) letters.append(c);
        }
        if (letters.length() < 2) return null;
        char a = Character.toUpperCase(letters.charAt(0));
        char b = Character.toUpperCase(letters.charAt(1));
        int u = a - 'A';
        int v = b - 'A';
        return new int[] { u, v };
    }

    // Interactive graph construction
    private void buildGraph() {
        System.out.println("\nEnter routes between locations (edges). Use letters A, B, C ...");
        System.out.println("Examples: AB  A B  A,B  A-B  (case-insensitive). Enter 'done' when finished.");
        while (true) {
            System.out.print("Enter connection (or 'done'): ");
            String line = sc.nextLine().trim();
            if (line.equalsIgnoreCase("done")) break;
            if (line.isEmpty()) continue;
            int[] pair = parseEdge(line);
            if (pair == null) {
                System.out.println("Invalid input. Provide two letters like AB or A B.");
                continue;
            }
            int u = pair[0], v = pair[1];
            if (!validVertex(u) || !validVertex(v)) {
                System.out.println("Vertices out of range. Allowed A to " + (char)('A' + numVertices - 1));
                continue;
            }
            addEdge(u, v);
            System.out.println("Added edge " + (char)('A' + u) + " - " + (char)('A' + v));
        }
    }

    // Menu loop
    private void menu() {
        while (true) {
            System.out.println("\n===== GRAPH MENU =====");
            System.out.println("1. Display Adjacency Matrix");
            System.out.println("2. Display Adjacency List");
            System.out.println("3. Perform BFS Traversal");
            System.out.println("4. Perform DFS Traversal");
            System.out.println("5. Add additional edge");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            String choiceLine = sc.nextLine().trim();
            if (choiceLine.isEmpty()) continue;
            int choice;
            try {
                choice = Integer.parseInt(choiceLine);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number 1-6.");
                continue;
            }

            switch (choice) {
                case 1:
                    displayMatrix();
                    break;
                case 2:
                    displayList();
                    break;
                case 3:
                    System.out.print("Enter starting vertex (A-" + (char)('A' + numVertices - 1) + "): ");
                    String sBfs = sc.nextLine().trim();
                    if (sBfs.isEmpty()) { System.out.println("No input."); break; }
                    int startBfs = Character.toUpperCase(sBfs.charAt(0)) - 'A';
                    bfs(startBfs);
                    break;
                case 4:
                    System.out.print("Enter starting vertex (A-" + (char)('A' + numVertices - 1) + "): ");
                    String sDfs = sc.nextLine().trim();
                    if (sDfs.isEmpty()) { System.out.println("No input."); break; }
                    int startDfs = Character.toUpperCase(sDfs.charAt(0)) - 'A';
                    dfs(startDfs);
                    break;
                case 5:
                    System.out.print("Enter connection to add (e.g., AB): ");
                    String l = sc.nextLine().trim();
                    int[] pair = parseEdge(l);
                    if (pair == null) {
                        System.out.println("Invalid input.");
                        break;
                    }
                    int u = pair[0], v = pair[1];
                    if (!validVertex(u) || !validVertex(v)) {
                        System.out.println("Vertices out of range.");
                        break;
                    }
                    addEdge(u, v);
                    System.out.println("Added edge " + (char)('A' + u) + " - " + (char)('A' + v));
                    break;
                case 6:
                    System.out.println("Exiting. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Pick 1-6.");
            }
        }
    }

    // MAIN - entry point
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int vertices = 0;
        while (vertices <= 0) {
            System.out.print("Enter number of locations (1-26): ");
            String line = in.nextLine().trim();
            try {
                vertices = Integer.parseInt(line);
                if (vertices < 1 || vertices > 26) {
                    System.out.println("Please pick between 1 and 26 vertices (A-Z).");
                    vertices = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }

        GraphTraversal graph = new GraphTraversal(vertices);
        graph.buildGraph();   // interactive edge input
        graph.menu();         // menu-driven operations
        in.close();
    }
}
