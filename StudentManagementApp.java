import java.io.*;
import java.util.*;

// Class to represent individual students
class Student {
    private int rollNumber;
    private String name;
    private String grade;

    // Constructor
    public Student(int rollNumber, String name, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.grade = grade;
    }

    // Getter and Setter Methods
    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // toString Method for Displaying Student Information
    @Override
    public String toString() {
        return "Roll Number: " + rollNumber + ", Name: " + name + ", Grade: " + grade;
    }
}

// Class to manage the collection of students
class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();
    private final String fileName = "students.txt";

    // Method to add a student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!");
        saveToFile();
    }

    // Method to remove a student by roll number
    public void removeStudent(int rollNumber) {
        Student student = findStudentByRoll(rollNumber);
        if (student != null) {
            students.remove(student);
            System.out.println("Student removed successfully!");
            saveToFile();
        } else {
            System.out.println("Student not found!");
        }
    }

    // Method to search for a student by roll number
    public Student findStudentByRoll(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    // Method to display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Method to save student data to a file
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.write(student.getRollNumber() + "," + student.getName() + "," + student.getGrade());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    // Method to load student data from a file
    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int rollNumber = Integer.parseInt(data[0]);
                String name = data[1];
                String grade = data[2];
                students.add(new Student(rollNumber, name, grade));
            }
        } catch (IOException e) {
            System.out.println("No existing student data found. Starting fresh.");
        }
    }
}

// Main Class for User Interaction
public class StudentManagementApp {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.loadFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*** Student Management System ***");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search for Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Add Student
                    System.out.print("Enter Roll Number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine();
                    sms.addStudent(new Student(rollNumber, name, grade));
                    break;

                case 2: // Remove Student
                    System.out.print("Enter Roll Number of Student to Remove: ");
                    rollNumber = scanner.nextInt();
                    sms.removeStudent(rollNumber);
                    break;

                case 3: // Search for Student
                    System.out.print("Enter Roll Number to Search: ");
                    rollNumber = scanner.nextInt();
                    Student student = sms.findStudentByRoll(rollNumber);
                    if (student != null) {
                        System.out.println("Student Found: " + student);
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 4: // Display All Students
                    System.out.println("All Students:");
                    sms.displayAllStudents();
                    break;

                case 5: // Exit
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
