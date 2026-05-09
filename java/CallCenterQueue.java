import java.util.LinkedList;
import java.util.Queue;

class Call {
    int customerID;
    int callTime;

    Call(int customerID, int callTime) {
        this.customerID = customerID;
        this.callTime = callTime;
    }

    public String toString() {
        return "Customer ID: " + customerID + ", Call Time: " + callTime + " mins";
    }
}

public class CallCenterQueue {
    public static void main(String[] args) {

        Queue<Call> queue = new LinkedList<>();

        // addCall()
        queue.add(new Call(101, 5));
        queue.add(new Call(102, 3));
        queue.add(new Call(103, 7));

        // viewQueue()
        System.out.println("Current Queue:");
        for (Call c : queue) {
            System.out.println(c);
        }

        // answerCall()
        System.out.println("\nAnswered Call:");
        System.out.println(queue.poll());

        // viewQueue()
        System.out.println("\nQueue After Answering:");
        for (Call c : queue) {
            System.out.println(c);
        }

        // isQueueEmpty()
        System.out.println("\nIs Queue Empty? " + queue.isEmpty());
    }
}