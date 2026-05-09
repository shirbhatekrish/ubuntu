import java.util.Scanner;

class LinearProbingHash {
    private int size;
    private Integer[] table;
    private boolean[] deleted; // tracks deleted cells for proper probing

    // Constructor
    public LinearProbingHash(int size) {
        this.size = size;
        table = new Integer[size];
        deleted = new boolean[size];
    }

    // Hash function using division method
    private int hashFunction(int key) {
        int index = key % size;
        if (index < 0) index += size; // handle negative keys
        return index;
    }

    // Insert a key using linear probing
    public void insert(int key) {
        int index = hashFunction(key);
        int startIndex = index;

        do {
            if (table[index] == null || deleted[index]) {
                table[index] = key;
                deleted[index] = false;
                System.out.println("Inserted key " + key + " at index " + index);
                return;
            } else if (table[index] == key) {
                System.out.println("Key " + key + " already exists at index " + index);
                return;
            }

            index = (index + 1) % size;
        } while (index != startIndex);

        System.out.println("Hash table is full. Cannot insert key " + key);
    }

    // Search for a key
    public void search(int key) {
        int index = hashFunction(key);
        int startIndex = index;

        do {
            if (table[index] == null && !deleted[index]) {
                System.out.println("Key " + key + " not found.");
                return;
            } else if (table[index] != null && table[index] == key) {
                System.out.println("Key " + key + " found at index " + index);
                return;
            }
            index = (index + 1) % size;
        } while (index != startIndex);

        System.out.println("Key " + key + " not found.");
    }

    // Delete a key
    public void delete(int key) {
        int index = hashFunction(key);
        int startIndex = index;

        do {
            if (table[index] == null && !deleted[index]) {
                System.out.println("Key " + key + " not found.");
                return;
            } else if (table[index] != null && table[index] == key) {
                table[index] = null;
                deleted[index] = true;
                System.out.println("Key " + key + " deleted from index " + index);
                return;
            }

            index = (index + 1) % size;
        } while (index != startIndex);

        System.out.println("Key " + key + " not found.");
    }

    // Display the hash table
    public void display() {
        System.out.println("\n--- Hash Table ---");
        for (int i = 0; i < size; i++) {
            if (table[i] != null)
                System.out.println("Index " + i + ": " + table[i]);
            else if (deleted[i])
                System.out.println("Index " + i + ": [Deleted]");
            else
                System.out.println("Index " + i + ": [Empty]");
        }
    }

    // Main menu for user operations
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of hash table: ");
        int n = sc.nextInt();

        LinearProbingHash hashTable = new LinearProbingHash(n);
        int choice;

        while (true) {
            System.out.println("\n===== HASH TABLE MENU =====");
            System.out.println("1. Insert a key");
            System.out.println("2. Search for a key");
            System.out.println("3. Delete a key");
            System.out.println("4. Display table");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Please enter a number between 1 and 5: ");
                sc.nextLine();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter key to insert: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Please enter an integer key: ");
                        sc.nextLine();
                    }
                    int key = sc.nextInt();
                    hashTable.insert(key);
                    break;

                case 2:
                    System.out.print("Enter key to search: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Please enter an integer key: ");
                        sc.nextLine();
                    }
                    key = sc.nextInt();
                    hashTable.search(key);
                    break;

                case 3:
                    System.out.print("Enter key to delete: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Please enter an integer key: ");
                        sc.nextLine();
                    }
                    key = sc.nextInt();
                    hashTable.delete(key);
                    break;

                case 4:
                    hashTable.display();
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please select 1-5.");
            }
        }
    }
}
