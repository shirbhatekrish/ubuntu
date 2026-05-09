import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CustomerSearch {

    // --- Linear Search ---
    public static boolean linearSearch(int[] customerIds, int targetId) {
        
        for (int i = 0; i < customerIds.length; i++) {
            if (customerIds[i] == targetId) {
                return true; // found the target
            }
        }
        return false; // not found
    }

    // --- Binary Search ---
    public static boolean binarySearch(int[] customerIds, int targetId) {
       
        Arrays.sort(customerIds);
        int low = 0;
        int high = customerIds.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int guess = customerIds[mid];

            if (guess == targetId) {
                return true;
            } else if (guess < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

   
    private static void printIds(int[] ids) {
        System.out.print("[ ");
        for (int id : ids) {
            System.out.print(id + " ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        
        int[] customerIds = new int[10];
        for (int i = 0; i < customerIds.length; i++) {
            customerIds[i] = 1000 + rand.nextInt(9000);
        }

        System.out.println("Customer Account IDs:");
        printIds(customerIds);

        System.out.print("\nEnter Customer ID to search for: ");
        int target = input.nextInt();

        // Linear Search
        boolean foundLinear = linearSearch(customerIds, target);
        System.out.println("Linear Search Result: " + (foundLinear ? "Found" : "Not Found"));

        // Binary Search
        boolean foundBinary = binarySearch(customerIds, target);
        System.out.println("Binary Search Result: " + (foundBinary ? "Found" : "Not Found"));

    
        input.close();
    }
}
