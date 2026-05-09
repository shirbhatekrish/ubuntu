import java.util.Scanner;

class Event {
    int maxSize;
    String[] eventArray;
    int front;
    int rear;
    int count;

    Event(int size) {
        maxSize = size;
        eventArray = new String[maxSize];
        front = 0;
        rear = -1;
        count = 0;
    }

    public void addEvent(String event) {
        if (count == maxSize) {
            System.out.println("Event list is full already, can't add event: " + event);
            return;
        }

        rear = (rear + 1) % maxSize;
        eventArray[rear] = event;
        System.out.println(event + " is added to the event list.");
        count++;
    }

    public void nextEvent() {
        if (count == 0) {
            System.out.println("Event list is empty, can't process next event.");
            return;
        }

        System.out.println(eventArray[front] + " is completed and deleted from the list.");
        front = (front + 1) % maxSize;
        count--;
    }

    public void cancelEvent() {
        if (count == 0) {
            System.out.println("Event list is empty, can't cancel event.");
            return;
        }

        System.out.println(eventArray[rear] + " is canceled.");
        rear = (rear - 1 + maxSize) % maxSize;
        count--;
    }

    public void display() {
        if (count == 0) {
            System.out.println("Event list is empty.");
            return;
        }

        System.out.println("Pending Event list is:");
        for (int i = 0; i < count; i++) {
            int index = (front + i) % maxSize;
            System.out.print(eventArray[index] + " ");
        }
        System.out.println();
    }
}

public class EventDemo2 {
    public static void main(String[] args) {
        Event a1 = new Event(5);

        a1.addEvent("Diwali");
        a1.addEvent("Dasara");
        a1.addEvent("Holi");
        a1.addEvent("Padva");
        a1.addEvent("MakarSankranti");

        a1.display();

        a1.nextEvent();
        a1.nextEvent();
        a1.display();

        a1.cancelEvent();
        a1.display();
    }
}
