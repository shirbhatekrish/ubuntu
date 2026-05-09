import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

// Node class for storing key-value pairs
class HashNode {
    int key;
    String value;

    HashNode(int key, String value) {
        this.key = key;
        this.value = value;
    }
}

// Hash table using chaining and division method (size = 10)
class HashTable {
    private final int SIZE = 10;
    @SuppressWarnings("unchecked")
    private LinkedList<HashNode>[] table = (LinkedList<HashNode>[]) new LinkedList[SIZE];

    public HashTable() {
        for (int i = 0; i < SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // division hash function
    private int hashFunction(int key) {
        int idx = key % SIZE;
        if (idx < 0) idx += SIZE; // handle negative keys safely
        return idx;
    }

    // insert or update
    public void insert(int key, String value) {
        int index = hashFunction(key);
        LinkedList<HashNode> chain = table[index];

        for (HashNode node : chain) {
            if (node.key == key) {            // update existing key
                node.value = value;
                System.out.println("Updated key " + key + " with value \"" + value + "\" at index " + index);
                return;
            }
        }

        chain.add(new HashNode(key, value));  // new pair
        System.out.println("Inserted (" + key + ", \"" + value + "\") at index " + index);
    }

    // search and print result
    public void search(int key) {
        int index = hashFunction(key);
        LinkedList<HashNode> chain = table[index];

        for (HashNode node : chain) {
            if (node.key == key) {
                System.out.println("Found at index " + index + ": (" + node.key + ", \"" + node.value + "\")");
                return;
            }
        }
        System.out.println("Key " + key + " not found.");
    }

    // delete key if present
    public void delete(int key) {
        int index = hashFunction(key);
        LinkedList<HashNode> chain = table[index];

        Iterator<HashNode> iterator = chain.iterator();
        while (iterator.hasNext()) {
            HashNode node = iterator.next();
            if (node.key == key) {
                iterator.remove();
                System.out.println("Deleted key " + key + " from index " + index);
                return;
            }
        }
        System.out.println("Key " + key + " not found.");
    }

    // display full table
    public void display() {
        System.out.println("\n--- Hash Table Contents ---");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("Index " + i + ":");
            for (HashNode node : table[i]) {
                System.out.print(" -> (" + node.key + ", \"" + node.value + "\")");
            }
            System.out.println();
        }
    }
}

// Menu-driven program
public class HashTableMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashTable hashTable = new HashTable();

        while (true) {
            System.out.println("\n===== HASH TABLE MENU =====");
            System.out.println("1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            int choice;
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.nextLine(); // consume invalid input
                continue;
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter key (integer): ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Please enter an integer for key: ");
                        sc.nextLine();
                    }
                    int key = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter value (string): ");
                    String value = sc.nextLine();
                    hashTable.insert(key, value);
                    break;

                case 2:
                    System.out.print("Enter key to search: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Please enter an integer for key: ");
                        sc.nextLine();
                    }
                    key = sc.nextInt();
                    sc.nextLine();
                    hashTable.search(key);
                    break;

                case 3:
                    System.out.print("Enter key to delete: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Please enter an integer for key: ");
                        sc.nextLine();
                    }
                    key = sc.nextInt();
                    sc.nextLine();
                    hashTable.delete(key);
                    break;

                case 4:
                    hashTable.display();
                    break;

                case 5:
                    System.out.println("Exiting. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Enter number between 1 and 5.");
            }
        }
    }
}
