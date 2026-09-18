import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // Course catalog loaded from fall_26.json
    private static List<Course> catalog = new ArrayList<>();
    private static Map<String, Course> catalogByCrn = new HashMap<>();

    private static final ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Group> groups = new ArrayList<>();

    // The student currently in the middle of entering their classes; null if nobody is mid-entry
    private static Student currentStudent = null;

    public Main() {
    }

    public static void main(String[] args) {
        printWelcome();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                if (!scanner.hasNextLine()) {
                    break; // input stream closed
                }
                String line = scanner.nextLine();
                String command = (line == null) ? "" : line.trim();

                if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("quit")) {
                    System.out.println("Goodbye!");
                    break;
                }
                processCommand(command);
            }
        } catch (Exception e) {
            // Catch-all so an unexpected runtime issue never crashes the console session
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    // Converts a 24-hour "HH:mm" string into the "h:mmAM"/"h:mmPM" form Course expects.
    // "11:00" -> "11:00AM", "12:15" -> "12:15PM", "13:05" -> "1:05PM", "00:30" -> "12:30AM".
    private static String toAmPm(String hhmm) {
        try {
            String[] parts = hhmm.split(":");
            int hour = Integer.parseInt(parts[0].trim());
            String minute = parts[1].trim();

            String mer = hour < 12 ? "AM" : "PM";
            int display = hour % 12;
            if (display == 0) display = 12;

            return String.format("%d:%s%s", display, minute, mer);
        } catch (Exception e) {
            return hhmm; // leave as-is; Course's validation will reject with the standard message
        }
    }

    // ----------------------------------------------------------
    // UI
    // ----------------------------------------------------------

    public static void printWelcome() {
        System.out.println("Welcome to HokieConnect! Please enter your name to get started, "
                + "or type 'help' to see all available commands.");
    }

    public static void printHelp() {
        System.out.println("Commands:\n"
                + " - help: displays instructions\n"
                + " - add student: adds a student record\n"
                + " - make group: forms study groups once the last person is done adding their classes");
    }

    public static void printClassInputInstructions() {
        System.out.println("Please add your classes using the following format:\n"
                + " Class Name | Days | StartTime-EndTime (e.g., CS 2114 | MWF | 9:00AM-9:50AM)\n"
                + " Or enter a 5-digit CRN from the Fall 2026 catalog.");
    }

    public static void processCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            System.out.println("Unknown command, type 'help'");
            return;
        }

        if (command.equalsIgnoreCase("help")) {
            printHelp();
        } else if (command.equalsIgnoreCase("add course")) {
            printClassInputInstructions();
        } else if (command.equalsIgnoreCase("make group")) {
            finishCurrentStudent(); // wrap up whoever was mid-entry before grouping
            doMakeGroups();
        } else if (command.equalsIgnoreCase("add student")) {
            System.out.println("What is the student's name?");
        } else if (command.toLowerCase().startsWith("add student ")) {
            finishCurrentStudent(); // don't lose whoever was already mid-entry
            startNewStudent(command.substring("add student ".length()).trim());
        } else if (command.equalsIgnoreCase("done")) {
            finishCurrentStudent();
        } else if (command.equalsIgnoreCase("students")) {
            listStudents();
        } else if (command.equalsIgnoreCase("courses")) {
            listCourses();
        } else if (command.equalsIgnoreCase("groups")) {
            listGroups();
        } else if (currentStudent == null) {
            // Nobody's mid-entry, so this line is a new student's name
            startNewStudent(command);
        } else {
            // Somebody's mid-entry: this line is a class, either a CRN or "Name | Days | Time"
            addCourseToCurrentStudent(command);
        }
    }

    // Starts a new student's session and walks them through adding classes
    private static void startNewStudent(String name) {
        try {
            currentStudent = new Student(name);
            System.out.println("Hi, " + currentStudent.getName() + "! Let's add your classes.");
            printClassInputInstructions();
            System.out.println("Type 'done' when you've added all your classes.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Saves the in-progress student to the roster and clears the active session
    private static void finishCurrentStudent() {
        if (currentStudent == null) {
            return;
        }
        students.add(currentStudent);
        System.out.println(currentStudent.getName() + " is all set with "
                + currentStudent.getCourses().size() + " course(s).");
        System.out.println("Next person, please enter your name (or type 'make group' if everyone's done).");
        currentStudent = null;
    }

    // Accepts either a 5-digit CRN or "Name | Days | Start-End" and enrolls the active student
    private static void addCourseToCurrentStudent(String input) {
        String trimmed = input.trim();

        // CRN path -> look up in the fall_26 catalog
        if (trimmed.matches("\\d{5}")) {
            Course found = catalogByCrn.get(trimmed);
            if (found == null) {
                System.out.println("No course with CRN " + trimmed + " in the catalog.");
                return;
            }
            currentStudent.addCourse(found);
            System.out.println("Added " + found.getCourseName() + ".");
            return;
        }

        // Manual path: "Name | Days | StartTime-EndTime"
        if (trimmed.contains("|")) {
            String[] parts = trimmed.split("\\|");
            if (parts.length != 3) {
                System.out.println("Invalid. Type help for correct formatting");
                return;
            }
            String name = parts[0].trim();
            String days = parts[1].trim().toUpperCase();
            String timeRange = parts[2].trim().toUpperCase();

            String timeField = days + " " + timeRange;
            Course course = findOrCreateManualCourse(name, days, timeField);
            if (course != null) {
                currentStudent.addCourse(course);
                System.out.println("Added " + course.getCourseName() + ".");
            }
            return;
        }

        System.out.println("Invalid. Type help for correct formatting");
    }

    // Reuses a matching manually-entered course if one already exists, otherwise creates one.
    private static Course findOrCreateManualCourse(String name, String days, String timeField) {
        for (Course c : catalog) {
            if (c.getCourseName().equalsIgnoreCase(name)
                    && c.getDays().equalsIgnoreCase(days)
                    && c.getTime().equalsIgnoreCase(timeField)) {
                return c;
            }
        }
        try {
            Course course = new Course(name, timeField, days);
            catalog.add(course);
            return course;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static void listCourses() {
        if (catalog.isEmpty()) {
            System.out.println("No courses available in the catalog.");
            return;
        }
        System.out.println("Course Catalog:");
        for (Course c : catalog) {
            System.out.println("  " + c);
        }
    }

    private static void listStudents() {
        if (students.isEmpty() && currentStudent == null) {
            System.out.println("No students added yet.");
            return;
        }
        System.out.println("Students:");
        for (Student s : students) {
            printStudentLine(s);
        }
        if (currentStudent != null) {
            printStudentLine(currentStudent); // still mid-entry, but worth showing
        }
    }

    private static void printStudentLine(Student s) {
        ArrayList<Course> courses = s.getCourses();
        StringBuilder sb = new StringBuilder("  " + s.getName() + " - ");
        if (courses.isEmpty()) {
            sb.append("(no courses added yet)");
        } else {
            for (int i = 0; i < courses.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(courses.get(i).getCourseName());
            }
        }
        System.out.println(sb);
    }

    private static void doMakeGroups() {
        if (students.isEmpty()) {
            System.out.println("No students have been added yet.");
            return;
        }
        groups = new Main().makeGroups(students);
        if (groups.isEmpty()) {
            System.out.println("No groups could be formed "
                    + "(need at least 2 students sharing a course and time slot).");
            return;
        }
        System.out.println("Formed " + groups.size() + " group(s):");
        listGroups();
    }

    private static void listGroups() {
        if (groups.isEmpty()) {
            System.out.println("No groups formed yet. Try 'make group'.");
            return;
        }
        for (Group g : groups) {
            System.out.print(g);
        }
    }

    // Organizes students into groups based on shared course name + days + time slot.
    // A group needs at least 2 students; singles are left ungrouped.
    public ArrayList<Group> makeGroups(ArrayList<Student> studentList) {
        ArrayList<Group> result = new ArrayList<>();
        LinkedHashMap<String, ArrayList<Student>> buckets = new LinkedHashMap<>();
        LinkedHashMap<String, Course> bucketCourse = new LinkedHashMap<>();

        for (Student s : studentList) {
            for (Course c : s.getCourses()) {
                String key = c.getCourseName() + "|" + c.getDays() + "|" + c.getTime();
                buckets.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
                bucketCourse.putIfAbsent(key, c);
            }
        }

        for (String key : buckets.keySet()) {
            ArrayList<Student> members = buckets.get(key);
            if (members.size() >= 2) {
                Course c = bucketCourse.get(key);
                try {
                    result.add(new Group(c.getCourseName(), c.getTime(), members));
                } catch (IllegalArgumentException e) {
                    System.out.println("Could not form group for " + c.getCourseName()
                            + ": " + e.getMessage());
                }
            }
        }
        return result;
    }
}