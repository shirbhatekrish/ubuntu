import java.util.Scanner;

public class SimpleLibraryRecords {

    // Data
    private static final int MAX_BOOKS = 50;
    private static final int MAX_MEMBERS = 50;

    private String[] bookNames = new String[MAX_BOOKS];
    private int[] borrowCount = new int[MAX_BOOKS];
    private String[] memberNames = new String[MAX_MEMBERS];
    private int[] memberBorrow = new int[MAX_MEMBERS];

    private int bookTotal = 0;
    private int memberTotal = 0;
    private Scanner sc = new Scanner(System.in);

    // Add record
    public void addRecord() {
        System.out.print("Enter member name: ");
        String member = sc.nextLine().trim();
        System.out.print("Enter book name: ");
        String book = sc.nextLine().trim();

        // update book record
        int bookIndex = findBook(book);
        if (bookIndex == -1) {
            bookNames[bookTotal] = book;
            borrowCount[bookTotal] = 1;
            bookTotal++;
        } else {
            borrowCount[bookIndex]++;
        }

        // update member record
        int memberIndex = findMember(member);
        if (memberIndex == -1) {
            memberNames[memberTotal] = member;
            memberBorrow[memberTotal] = 1;
            memberTotal++;
        } else {
            memberBorrow[memberIndex]++;
        }

        System.out.println("Record added successfully!");
    }

    private int findBook(String book) {
        for (int i = 0; i < bookTotal; i++) {
            if (bookNames[i].equalsIgnoreCase(book)) return i;
        }
        return -1;
    }

    private int findMember(String member) {
        for (int i = 0; i < memberTotal; i++) {
            if (memberNames[i].equalsIgnoreCase(member)) return i;
        }
        return -1;
    }

    // Compute average borrowed books
    public void computeAverage() {
        if (memberTotal == 0) {
            System.out.println("No records available.");
            return;
        }

        int total = 0;
        for (int i = 0; i < memberTotal; i++) {
            total += memberBorrow[i];
        }

        double avg = (double) total / memberTotal;
        System.out.printf("Average number of books borrowed per member: %.2f%n", avg);
        System.out.println("Time Complexity: O(n)");
        System.out.println("Space Complexity: O(1)");
    }

    // Find highest and lowest borrowed book
    public void findHighLowBook() {
        if (bookTotal == 0) {
            System.out.println("No book records available.");
            return;
        }

        int max = borrowCount[0];
        int min = borrowCount[0];
        String maxBook = bookNames[0];
        String minBook = bookNames[0];

        for (int i = 1; i < bookTotal; i++) {
            if (borrowCount[i] > max) {
                max = borrowCount[i];
                maxBook = bookNames[i];
            }
            if (borrowCount[i] < min) {
                min = borrowCount[i];
                minBook = bookNames[i];
            }
        }

        System.out.println("Most Borrowed Book: " + maxBook + " (" + max + " times)");
        System.out.println("Least Borrowed Book: " + minBook + " (" + min + " times)");
        System.out.println("Time Complexity: O(n)");
        System.out.println("Space Complexity: O(1)");
    }

    // Count non-borrowing members
    public void countNonBorrowers() {
        System.out.print("Enter total number of members in library: ");
        int totalMembers = sc.nextInt();
        sc.nextLine(); // consume newline

        int nonBorrowers = totalMembers - memberTotal;
        if (nonBorrowers < 0) nonBorrowers = 0;

        System.out.println("Number of members who haven’t borrowed any books: " + nonBorrowers);
        System.out.println("Time Complexity: O(1)");
        System.out.println("Space Complexity: O(1)");
    }

    // Find most frequently borrowed book (mode)
    public void findMostFrequentBook() {
        if (bookTotal == 0) {
            System.out.println("No books borrowed yet.");
            return;
        }

        int maxCount = borrowCount[0];
        String modeBook = bookNames[0];

        for (int i = 1; i < bookTotal; i++) {
            if (borrowCount[i] > maxCount) {
                maxCount = borrowCount[i];
                modeBook = bookNames[i];
            }
        }

        System.out.println("Most Frequently Borrowed Book: " + modeBook + " (" + maxCount + " times)");
        System.out.println("Time Complexity: O(n)");
        System.out.println("Space Complexity: O(1)");
    }

    // Display all records
    public void displayRecords() {
        if (bookTotal == 0) {
            System.out.println("No records to display.");
            return;
        }

        System.out.println("\n=== Borrow Records ===");
        for (int i = 0; i < bookTotal; i++) {
            System.out.println((i + 1) + ". " + bookNames[i] + " → " + borrowCount[i] + " times");
        }
        System.out.println("======================");
    }

    // Menu
    public void menu() {
        while (true) {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add Borrow Record");
            System.out.println("2. Compute Average Books Borrowed");
            System.out.println("3. Find Highest & Lowest Borrowed Book");
            System.out.println("4. Count Non-Borrowing Members");
            System.out.println("5. Find Most Frequently Borrowed Book");
            System.out.println("6. Display All Records");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }

            switch (choice) {
                case 1: addRecord(); break;
                case 2: computeAverage(); break;
                case 3: findHighLowBook(); break;
                case 4: countNonBorrowers(); break;
                case 5: findMostFrequentBook(); break;
                case 6: displayRecords(); break;
                case 7: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // main method
    public static void main(String[] args) {
        SimpleLibraryRecords lib = new SimpleLibraryRecords();
        lib.menu();
    }
}
