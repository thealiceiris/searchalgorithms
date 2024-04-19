// Import necessary classes
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

// define a Node class that represents a node in the graph
class Node {
    String data; // data stored in the node
    ArrayList<Node> neighbors; // List of neighboring nodes

    // Constructor to initialize the node
    Node(String data) {
        this.data = data;
        neighbors = new ArrayList<>();
    }

    // Method to add a neighboring node
    void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }
}

// Main class containing search algorithms
public class SearchAlgorithms {
    private static Node[] nodes; // Array to store nodes of the graph
    private static Scanner scanner = new Scanner(System.in); // Scanner for user input

    // Main method to execute the program
    public static void main(String[] args) {
        initializeGraph(); // Initialize the graph
        // Display menu for selecting search algorithm
        System.out.println("Select a search algorithm:");
        System.out.println("1. BFS");
        System.out.println("2. DFS");
        System.out.println("3. Iterative Deepening");
        System.out.println("4. A* Search");
        int choice = scanner.nextInt(); // Get user's choice
        scanner.nextLine(); // Consume newline character

        // Prompt user to enter the item to search
        System.out.print("Enter the item to search: ");
        String searchItem = scanner.nextLine();

        // Perform the selected search algorithm based on user's choice
        switch (choice) {
            case 1:
                bfs(searchItem); // Perform Breadth First Search
                break;
            case 2:
                dfs(searchItem); // Perform Depth First Search
                break;
            case 3:
                iterativeDeepeningSearch(searchItem); // Perform Iterative Deepening Search
                break;
            case 4:
                aStarSearch(searchItem); // Perform A* Search
                break;
            default:
                System.out.println("Invalid choice."); // Display message for invalid choice
        }
    }

    // Method to initialize the graph with nodes and edges
    private static void initializeGraph() {
        nodes = new Node[6]; // Create an array of nodes
        // Create nodes and add them to the array
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(String.valueOf((char) ('A' + i)));
        }
        // Add edges between nodes to form the graph
        nodes[0].addNeighbor(nodes[1]);
        nodes[0].addNeighbor(nodes[3]);
        nodes[1].addNeighbor(nodes[2]);
        nodes[2].addNeighbor(nodes[4]);
        nodes[3].addNeighbor(nodes[4]);
        nodes[4].addNeighbor(nodes[5]);
    }

    // Method to perform Breadth First Search
    private static void bfs(String searchItem) {
        long startTime = System.nanoTime(); // Record start time
        Queue<Node> queue = new LinkedList<>(); // Create a queue for BFS
        queue.offer(nodes[0]); // Add the starting node to the queue
        boolean found = false; // Flag to indicate if the item is found

        // Perform BFS until the queue is empty or the item is found
        while (!queue.isEmpty() && !found) {
            Node current = queue.poll(); // Remove the front node from the queue
            if (current.data.equals(searchItem)) {
                found = true; // Item found, set flag to true
            } else {
                // Add neighboring nodes to the queue
                for (Node neighbor : current.neighbors) {
                    queue.offer(neighbor);
                }
            }
        }

        long endTime = System.nanoTime(); // Record end time
        long duration = endTime - startTime; // Calculate duration of BFS
        // Display result and time taken
        System.out.println("BFS: " + (found ? "Item found" : "Item not found") + " (Time taken: " + duration + " nanoseconds)");
    }

    // Method to perform Depth First Search
    private static void dfs(String searchItem) {
        long startTime = System.nanoTime(); // Record start time
        Stack<Node> stack = new Stack<>(); // Create a stack for DFS
        stack.push(nodes[0]); // Add the starting node to the stack
        boolean found = false; // Flag to indicate if the item is found

        // Perform DFS until the stack is empty or the item is found
        while (!stack.isEmpty() && !found) {
            Node current = stack.pop(); // Remove the top node from the stack
            if (current.data.equals(searchItem)) {
                found = true; // Item found, set flag to true
            } else {
                // Add neighboring nodes to the stack
                for (Node neighbor : current.neighbors) {
                    stack.push(neighbor);
                }
            }
        }

        long endTime = System.nanoTime(); // Record end time
        long duration = endTime - startTime; // Calculate duration of DFS
        // Display result and time taken
        System.out.println("DFS: " + (found ? "Item found" : "Item not found") + " (Time taken: " + duration + " nanoseconds)");
    }

    // Method to perform Iterative Deepening Search
    private static void iterativeDeepeningSearch(String searchItem) {
        long startTime = System.nanoTime(); // Record start time
        boolean found = false; // Flag to indicate if the item is found

        int maxDepth = 0; // Start with depth limit 0

        // Perform Iterative Deepening until the item is found
        while (!found) {
            found = dfsLimited(nodes[0], searchItem, maxDepth); // Perform limited DFS
            maxDepth++; // Increment maxDepth for next iteration
        }

        long endTime = System.nanoTime(); // Record end time
        long duration = endTime - startTime; // Calculate duration of Iterative Deepening
        // Display result and time taken
        System.out.println("Iterative Deepening Search: " + (found ? "Item found" : "Item not found") + " (Time taken: " + duration + " nanoseconds)");
    }


    // Method to perform limited Depth First Search for Iterative Deepening
    private static boolean dfsLimited(Node node, String searchItem, int maxDepth) {
        if (node.data.equals(searchItem)) {
            return true; // Item found
        }

        if (maxDepth == 0) {
            return false; // Reached maximum depth
        }

        // Recursively search neighboring nodes within the depth limit
        for (Node neighbor : node.neighbors) {
            if (dfsLimited(neighbor, searchItem, maxDepth - 1)) {
                return true; // Item found within depth limit
            }
        }

        return false; // Item not found within depth limit
    }

    // Method to perform A* Search
    private static void aStarSearch(String searchItem) {
        long startTime = System.nanoTime(); // Record start time
        // Implement A* Search algorithm here (not implemented in this example)
        long endTime = System.nanoTime(); // Record end time
        long duration = endTime - startTime; // Calculate duration of A* Search
        // Display result and time taken
        System.out.println("A* Search: Item not found (Time taken: " + duration + " nanoseconds)");
    }
}
