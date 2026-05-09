import java.util.Scanner;

// Node for Doubly Linked List
class Student {
    int rollNo;
    String name;
    double marks;
    Student next;
    Student prev;

    Student(int rollNo, String name, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
        this.next = null;
        this.prev = null;
    }
}

// Linked List class for managing Student Records
class StudentList {
    private Student head;

    // Add student at the end
    public void addStudent(int rollNo, String name, double marks) {
        Student newStudent = new Student(rollNo, name, marks);
        if (head == null) {
            head = newStudent;
            System.out.println("Student added successfully!");
            return;
        }

        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudent;
        newStudent.prev = temp;
        System.out.println("Student added successfully!");
    }

    // Delete student by roll number
    public void deleteStudent(int rollNo) {
        if (head == null) {
            System.out.println("No records to delete.");
            return;
        }

        Student temp = head;

        // if head needs to be deleted
        if (temp.rollNo == rollNo) {
            head = temp.next;
            if (head != null)
                head.prev = null;
            System.out.println("Record deleted successfully.");
            return;
        }

        while (temp != null && temp.rollNo != rollNo) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Student with Roll No " + rollNo + " not found.");
            return;
        }

        if (temp.next != null)
            temp.next.prev = temp.prev;
        if (temp.prev != null)
            temp.prev.next = temp.next;

        System.out.println("Record deleted successfully.");
    }

    // Update student details
    public void updateStudent(int rollNo, String newName, double newMarks) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                temp.name = newName;
                temp.marks = newMarks;
                System.out.println("Record updated successfully!");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll No " + rollNo + " not found.");
    }

    // Search for a student by roll number
    public void searchStudent(int rollNo) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                System.out.println("Student Found:");
                System.out.println("Roll No: " + temp.rollNo + ", Name: " + temp.name + ", Marks: " + temp.marks);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found.");
    }

    // Sort by roll number or marks
    public void sortRecords(boolean sortByMarks, boolean ascending) {
        if (head == null) {
            System.out.println("No records to sort.");
            return;
        }

        for (Student i = head; i != null; i = i.next) {
            for (Student j = i.next; j != null; j = j.next) {
                boolean condition;
                if (sortByMarks)
                    condition = ascending ? (i.marks > j.marks) : (i.marks < j.marks);
                else
                    condition = ascending ? (i.rollNo > j.rollNo) : (i.rollNo < j.rollNo);

                if (condition) {
                    // swap data only (not links)
                    int tempRoll = i.rollNo;
                    String tempName = i.name;
                    double tempMarks = i.marks;

                    i.rollNo = j.rollNo;
                    i.name = j.name;
                    i.marks = j.marks;

                    j.rollNo = tempRoll;
                    j.name = tempName;
                    j.marks = tempMarks;
                }
            }
        }
        System.out.println("Records sorted successfully!");
    }

    // Display all student records
    public void display() {
        if (head == null) {
            System.out.println("No records found.");
            return;
        }

        System.out.println("\n--- Student Records ---");
        Student temp = head;
        while (temp != null) {
            System.out.println("Roll No: " + temp.rollNo + ", Name: " + temp.name + ", Marks: " + temp.marks);
            temp = temp.next;
        }
    }
}

// Main class with menu
public class StudentRecordSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentList list = new StudentList();

        while (true) {
            System.out.println("\n===== Student Record Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. Search Student");
            System.out.println("5. Sort Records");
            System.out.println("6. Display All Records");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();
                    list.addStudent(roll, name, marks);
                    break;

                case 2:
                    System.out.print("Enter Roll No to delete: ");
                    roll = sc.nextInt();
                    list.deleteStudent(roll);
                    break;

                case 3:
                    System.out.print("Enter Roll No to update: ");
                    roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter new Marks: ");
                    marks = sc.nextDouble();
                    list.updateStudent(roll, name, marks);
                    break;

                case 4:
                    System.out.print("Enter Roll No to search: ");
                    roll = sc.nextInt();
                    list.searchStudent(roll);
                    break;

                case 5:
                    System.out.println("Sort by: 1. Marks  2. Roll No");
                    int sortType = sc.nextInt();
                    System.out.println("Order: 1. Ascending  2. Descending");
                    int order = sc.nextInt();
                    list.sortRecords(sortType == 1, order == 1);
                    break;

                case 6:
                    list.display();
                    break;

                case 7:
                    System.out.println("Exiting program. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
