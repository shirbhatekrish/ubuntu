import java.util.Scanner;

public class SalarySorter {

    // Selection Sort
    public static void selectionSort(float[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // swap
            float temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // Bubble Sort
    public static void bubbleSort(float[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    float temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // Optimization
        }
    }

    // Display top 5 highest salaries
    public static void displayTopFive(float[] arr) {
        System.out.println("\nTop 5 Highest Salaries:");
        int n = arr.length;
        int count = Math.min(5, n);
        for (int i = n - 1; i >= n - count; i--) {
            System.out.println((n - i) + ". " + arr[i]);
        }
    }

    // Utility function to copy array
    public static float[] copyArray(float[] arr) {
        float[] newArr = new float[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    // Main program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();

        float[] salaries = new float[n];
        System.out.println("Enter employee salaries:");
        for (int i = 0; i < n; i++) {
            System.out.print("Salary " + (i + 1) + ": ");
            salaries[i] = sc.nextFloat();
        }

        System.out.println("\nChoose Sorting Algorithm:");
        System.out.println("1. Selection Sort");
        System.out.println("2. Bubble Sort");
        System.out.print("Enter choice (1 or 2): ");
        int choice = sc.nextInt();

        float[] sorted = copyArray(salaries);

        if (choice == 1) {
            selectionSort(sorted);
            System.out.println("\nSalaries sorted using Selection Sort:");
        } else if (choice == 2) {
            bubbleSort(sorted);
            System.out.println("\nSalaries sorted using Bubble Sort:");
        } else {
            System.out.println("Invalid choice. Defaulting to Selection Sort.");
            selectionSort(sorted);
        }

        // Display sorted list
        for (float s : sorted) {
            System.out.print(s + " ");
        }

        // Show top 5
        displayTopFive(sorted);

        sc.close();
    }
}
