import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistration {

    static class Course {
        String code, subject, topics, schedule, duration;
        int capacity, enrolled;

        Course(String code, String subject, String topics, int capacity, String schedule, String duration) {
            this.code = code;
            this.subject = subject;
            this.topics = topics;
            this.capacity = capacity;
            this.schedule = schedule;
            this.duration = duration;
        }
    }

    static class Student {
        String id, name;
        ArrayList<Course> courses = new ArrayList<>();

        Student(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static ArrayList<Course> courses = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static Course findCourse(String code) {
        for (Course c : courses) if (c.code.equalsIgnoreCase(code)) return c;
        return null;
    }

    static void listCourses() {
        for (Course c : courses) {
            System.out.println("Course Code : " + c.code);
            System.out.println("Subject     : " + c.subject);
            System.out.println("Topics      : " + c.topics);
            System.out.println("Schedule    : " + c.schedule);
            System.out.println("Duration    : " + c.duration);
            System.out.println("Slots left  : " + (c.capacity - c.enrolled));
            System.out.println(); // blank line between courses
        }
    }

    static void register(Student s) {
        System.out.print("Course code: ");
        Course c = findCourse(sc.nextLine().trim());
        if (c == null) System.out.println("Course not found.");
        else if (s.courses.contains(c)) System.out.println("Already registered.");
        else if (c.enrolled == c.capacity) System.out.println("Course is full.");
        else {
            s.courses.add(c);
            c.enrolled++;
            System.out.println("Registered for " + c.code);
        }
    }

    static void drop(Student s) {
        System.out.print("Course code: ");
        Course c = findCourse(sc.nextLine().trim());
        if (c == null || !s.courses.contains(c)) System.out.println("You are not registered for that course.");
        else {
            s.courses.remove(c);
            c.enrolled--;
            System.out.println("Dropped " + c.code);
        }
    }

    public static void main(String[] args) {
        courses.add(new Course("CS101", "Intro to Programming", "Java basics", 3, "Mon/Wed 10:00", "12 weeks"));
        courses.add(new Course("CS102", "Data Structures", "Lists, stacks, trees", 2, "Tue/Thu 11:00", "12 weeks"));
        courses.add(new Course("MA101", "Discrete Maths", "Logic and graphs", 3, "Mon/Fri 09:00", "10 weeks"));

        System.out.println("=== COURSE REGISTRATION ===");
        System.out.print("Create your student ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Enter your name: ");
        Student me = new Student(id, sc.nextLine().trim());
        System.out.println("Welcome, " + me.name + "!" + " (ID: " + me.id + ")" );

        while (true) {
            System.out.println("\n1. List courses  \n2. Register  \n3. Drop  \n4. My courses  \n0. Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();

            if (choice.equals("1")) listCourses();
            else if (choice.equals("2")) register(me);
            else if (choice.equals("3")) drop(me);
            else if (choice.equals("4")) {
                if (me.courses.isEmpty()) System.out.println("No courses yet.");
                for (Course c : me.courses) System.out.println(c.code + " - " + c.subject);
            }
            else if (choice.equals("0")) break;
            else System.out.println("Invalid choice.");
        }
        System.out.println("Goodbye!");
    }
}