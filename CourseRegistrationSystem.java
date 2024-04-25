//task:5
//student course registration system
import java.util.ArrayList;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int enrolledStudents;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean addStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        }
        return false;
    }

    public void removeStudent() {
        enrolledStudents--;
    }

    @Override
    public String toString() {
        return "Course Code: " + code + ", Title: " + title + ", Description: " + description + ", Capacity: " + capacity
                + ", Enrolled Students: " + enrolledStudents;
    }
}

class Student {
    private int id;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.addStudent()) {
            registeredCourses.add(course);
            System.out.println("Course " + course.getCode() + " successfully registered for " + name);
        } else {
            System.out.println("Failed to register for course " + course.getCode() + ". Course is full.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.removeStudent();
            System.out.println("Course " + course.getCode() + " successfully dropped for " + name);
        } else {
            System.out.println("You are not registered for course " + course.getCode());
        }
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Course c1 = new Course("CS101", "Introduction to Programming", "Learn basics of programming", 50);
        Course c2 = new Course("CS201", "Data Structures", "Learn about data structures", 40);
        Course c3 = new Course("CS301", "Algorithms", "Study advanced algorithms", 30);

        Student s1 = new Student(1, "John");
        Student s2 = new Student(2, "Alice");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Display Course Listing");
            System.out.println("2. Register for Course");
            System.out.println("3. Drop Course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayCourseListing(new Course[]{c1, c2, c3});
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    int studentId = scanner.nextInt();
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.next();
                    registerCourse(s1, s2, studentId, courseCode, c1, c2, c3);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    int studentId2 = scanner.nextInt();
                    System.out.print("Enter course code: ");
                    String courseCode2 = scanner.next();
                    dropCourse(s1, s2, studentId2, courseCode2, c1, c2, c3);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void displayCourseListing(Course[] courses) {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public static void registerCourse(Student s1, Student s2, int studentId, String courseCode, Course... courses) {
        Student student = studentId == 1 ? s1 : s2;
        Course courseToRegister = null;
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                courseToRegister = course;
                break;
            }
        }
        if (courseToRegister != null) {
            student.registerCourse(courseToRegister);
        } else {
            System.out.println("Invalid course code.");
        }
    }

    public static void dropCourse(Student s1, Student s2, int studentId, String courseCode, Course... courses) {
        Student student = studentId == 1 ? s1 : s2;
        Course courseToDrop = null;
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                courseToDrop = course;
                break;
            }
        }
        if (courseToDrop != null) {
            student.dropCourse(courseToDrop);
        } else {
            System.out.println("Invalid course code.");
        }
    }
}
