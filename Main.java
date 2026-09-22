import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

//-------------------------------------------------------------------------
/**
 * Main command-line application interface for HokieConnect.
 *
 * @author Luci Dulog (906619962)
 * @version (2026.09.19)
 */
public class Main
{
    //~ Fields ................................................................

    private ArrayList<Student> students;
    private ArrayList<Group> groups;
    private ArrayList<String> locations;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created Main object.
     */
    public Main()
    {
        students = new ArrayList<>();
        groups = new ArrayList<>();
        locations = new ArrayList<>();
        // Predefined list of meeting locations
        locations.add("Newman Library Floor 2");
        locations.add("Newman Library Floor 4 Cubicles");
        locations.add("Newman Library Group Floor 4 Study Rooms");
        locations.add("The Bridge");
        locations.add("Torgersen Hall");
        locations.add("New Classroom Building Floor 1 (NCB)");
        locations.add("New Classroom Building Floor 2 (NCB)");
        locations.add("New Classroom Building Floor 3 (NCB)");
        locations.add("Undergraduate Science Labratory Building (USLB)");
        locations.add("Squires Student Center");
        locations.add("Data and Decision Sciences (DDS)");
        locations.add("Holden Hall");
        locations.add("Surge Space Building");
        locations.add("Dietrick Dining Hall Floor 1");
        locations.add("Goodwin Hall Floor");
        locations.add("Hitt Hall");
        locations.add("Lavery Hall (Inside)");
        locations.add("Lavery Hall (Outside)");
        locations.add("McBryde Hall Floor 1");
        locations.add("McBryde Hall Floor 2");
        locations.add("McBryde Hall Floor 3");
    }
    
    //~ Methods ...............................................................

    /**
     * Prints the greeting message to the console.
     */
    public void printWelcome()
    {
        System.out.println("Welcome to HokieConnect! Please "
            + "or type 'help' to see all available commands."); 
    }
    
    /**
     * Prints the basic command options and course formatting instructions.
     */
    public void printHelp()
    {
        System.out.println("=== Commands ===");
        System.out.println(" - help: displays instructions"); 
        System.out.println(" - add student: adds a student record"); 
        System.out.println(" - add course: adds course(s) to the most recently added student");
        System.out.println(" - view schedule: displays all courses registered for a student");
        System.out.println(" - make group: forms study groups once the last person " 
            + "is done adding their classes"); 
        System.out.println(" - exit: exits the application"); 
        
        System.out.println("\n=== Course Entry Format ===");
        System.out.println("You can enter course info either in ONE LINE or STEP-BY-STEP:");
        System.out.println("  One-line format : CS_2114 TR 3:30PM-4:20PM");
        System.out.println("  Step 1 Name     : CS_2114         (Department Number with underscore)");
        System.out.println("  Step 2 Days     : TR or MWF or F  (Meeting days)");
        System.out.println("  Step 3 Time     : 3:30PM-4:20PM   (Start-End time range)");
    }

    /**
     * Prints the accepted formats for adding a course.
     */
    public void printClassInputInstructions()
    {
        System.out.println("Please add the class using one of the following formats:");
        System.out.println(" 1) CRN Number: Enter the 5-digit CRN (e.g., CRN12345)");
        System.out.println(" 2) Class & Time: Enter class name and time slot (e.g., CS_2114 TR 3:30-4:20)");
    }
    
    /**
     * Starts the command line console loop.
     */
    public void startConsole()
    {
        printWelcome(); 
        
        Scanner scanner = new Scanner(System.in); 
        boolean running = true; 
        
        while (running)
        {
            System.out.print("> "); 
            String line = scanner.nextLine(); 
            
            String command = line.trim().toLowerCase();
            
            if (command.equals("exit") || command.equals("quit"))
            {
                System.out.println("Goodbye!"); 
                running = false; 
            }
            else if (command.equals("help"))
            {
                printHelp();
            }
            else if (command.length() > 0)
            {
                processCommand(command, scanner); 
            }
        }
        scanner.close();
    }
    
    /**
     * Processes command inputs by the user.
     * 
     * @param command The string entered at the console prompt.
     * @param scanner The active Scanner object to read further inputs.
     */
    public void processCommand(String command, Scanner scanner)
    {
        if (command.equalsIgnoreCase("add student"))
        {
            addStudent(scanner);
        }
        else if (command.equalsIgnoreCase("add course"))
        {
            if (students.isEmpty())
            {
                System.out.println("Error: You must add a student first before adding courses!");
            }
            else
            {
                addCoursesForCurrentStudent(scanner);
            }
        }
        else if (command.equalsIgnoreCase("view schedule") || command.equalsIgnoreCase("show schedule"))
        {
            if (students.isEmpty())
            {
                System.out.println("No students have been added yet.");
            }
            else
            {
                viewStudentSchedule(scanner);
            }
        }
        else if (command.equalsIgnoreCase("make group"))
        {
            doMakeGroups();
        }
        else if (command.equalsIgnoreCase("students"))
        {
            System.out.println(students.size() + " student(s) registered:");
            for (Student s : students)
            {
                System.out.println(" - " + s.getName());
            }
        }
        else 
        {
            System.out.println("Unknown command, type 'help'"); 
        }
    }

    /**
     * Prompts the user for a student name and adds a new Student object.
     * 
     * @param scanner The active Scanner object to read user input.
     */
    public void addStudent(Scanner scanner)
    {
        System.out.print("What is the student's name? ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty())
        {
            System.out.println("Student name cannot be empty.");
            return;
        }

        Student newStudent = new Student(name);
        students.add(newStudent);
        System.out.println("Added student: " + newStudent.getName());
    }

    /**
     * Adds course(s) to the active student, supporting single-line input or multi-prompt input.
     * 
     * @param scanner The active Scanner object to read user input.
     */
    /**
     * Adds course(s) to the most recently added student using single-line input.
     * 
     * @param scanner The active Scanner object to read user input.
     */
<<<<<<< Updated upstream
    public void addCoursesForCurrentStudent(Scanner scanner) {
=======
    public void addCoursesForCurrentStudent(Scanner scanner) 
    {
>>>>>>> Stashed changes
        Student currentStudent = students.get(students.size() - 1);
        System.out.println("--- Adding courses for " + currentStudent.getName() + " ---");
 
        while (true)
        {
            System.out.print("Enter Course (Format: CS_2114 TR 3:30PM-4:20PM) or 'done': ");
            String input = scanner.nextLine().trim();
 
            if (input.equalsIgnoreCase("done") || input.equalsIgnoreCase("exit"))
            {
                break;
            }
 
            String[] parts = input.split("\\s+");
 
            if (input.contains("CRN")) {
                // Handle CRN input
                String CRN_number = input.substring(3);
                System.out.println("CRN Number received: " + CRN_number); // Debugging line
                Course newCourse = new Course(CRN_number);
                if (newCourse != null) {
                    currentStudent.addCourse(newCourse);
                    System.out.println("Added: " + newCourse + "\n");
                }
                continue;
            }
            else if (parts.length == 3) {
                // Handle Class & Time input
                String[] classParts = input.split(" ");
                if (classParts.length != 3) {
                    System.out.println("Invalid format! Must enter class name, days, and time separated by spaces.");
                    System.out.println("Example: CS_2114 TR 3:30PM-4:20PM\n");
                    continue;
                }
                String name = classParts[0].trim();
                String days = classParts[1].trim();
                String time = normalizeTime(classParts[2].trim());

                Course newCourse = new Course(name, time, days);
                if (newCourse != null) {
                    currentStudent.addCourse(newCourse);
                    System.out.println("Added: " + newCourse + "\n");
                }
                continue;
            }
            else {
                System.out.println("Invalid input format! Please enter either a CRN or a class with days and time.");
                System.out.println("Example CRN: CRN12345");
                System.out.println("Example Class & Time: CS_2114 TR 3:30PM-4:20PM\n");
            }
        }
    }

    /**
     * Displays all registered courses for a selected student.
     * 
     * @param scanner The active Scanner object to read user input.
     */
    public void viewStudentSchedule(Scanner scanner)
    {
        System.out.print("Enter student name to view schedule: ");
        String searchName = scanner.nextLine().trim();

        Student target = null;
        for (Student s : students)
        {
            if (s.getName().equalsIgnoreCase(searchName))
            {
                target = s;
                break;
            }
        }

        if (target == null)
        {
            System.out.println("Student '" + searchName + "' was not found.");
            return;
        }

        ArrayList<Course> courseList = target.getCourses();
        System.out.println("\n=== Schedule for " + target.getName() + " ===");

        if (courseList.isEmpty())
        {
            System.out.println("No courses registered yet.");
        }
        else
        {
            for (Course c : courseList)
            {
                System.out.println(" - " + c.toString());
            }
        }
        System.out.println();
    }

        /**
     * Cleans up manually-typed times (e.g. "TR3:30-4:20") into the format
     * Course expects ("TR 3:30PM-4:20PM"), inferring AM/PM when missing.
     * 
     * @param raw The raw time string typed by the user.
     * @return The normalized time string.
     */
    private String normalizeTime(String raw)
    {
        String s = raw.trim().replaceFirst("^([MTWRFSUmtwrfsu]+)(\\d)", "$1 $2");
 
        int spaceIdx = s.indexOf(' ');
        if (spaceIdx < 0)
        {
            return s; // malformed; Course's own validation will reject it with the standard message
        }
        String days = s.substring(0, spaceIdx).toUpperCase();
        String rest = s.substring(spaceIdx + 1).trim();
 
        String[] segments = rest.split("-", 2);
        StringBuilder result = new StringBuilder(days).append(' ');
        for (int i = 0; i < segments.length; i++)
        {
            result.append(withMeridiem(segments[i].trim()));
            if (i == 0 && segments.length > 1)
            {
                result.append('-');
            }
        }
        return result.toString();
    }
 
    /**
     * Adds AM/PM to a bare clock time like "3:30", guessing based on a
     * typical class-day schedule.
     * 
     * @param clock The bare clock time (with or without AM/PM already).
     * @return The clock time with AM/PM appended.
     */
    private String withMeridiem(String clock)
    {
        String upper = clock.toUpperCase();
        if (upper.endsWith("AM") || upper.endsWith("PM"))
        {
            return upper;
        }
        String[] parts = clock.split(":");
        if (parts.length != 2)
        {
            return clock; // leave as-is; validation will reject with the standard message
        }
        try
        {
            int hour = Integer.parseInt(parts[0].trim());
            String meridiem = (hour == 12 || (hour >= 1 && hour <= 6)) ? "PM" : "AM";
            return clock + meridiem;
        }
        catch (NumberFormatException e)
        {
            return clock;
        }
    }
 
    /**
     * Builds study groups from the current student roster and reports the
     * result to the console.
     */
    public void doMakeGroups()
    {
        if (students.isEmpty())
        {
            System.out.println("No students have been added yet.");
            return;
        }
 
        groups = makeGroups(students);
 
        if (groups.isEmpty())
        {
            System.out.println("No groups could be formed "
                + "(need at least 2 students sharing a course and time slot).");
            return;
        }
 
        System.out.println("Formed " + groups.size() + " group(s):");
        listGroups();
    }

    public ArrayList<Group> makeGroups(ArrayList<Student> studentList)
    {
        ArrayList<Group> result = new ArrayList<>();
        LinkedHashMap<String, ArrayList<Student>> buckets = new LinkedHashMap<>();
        LinkedHashMap<String, Course> bucketCourse = new LinkedHashMap<>();

        for (Student s : studentList)
        {
            for (Course c : s.getCourses())
        {
            String key = courseKey(c);
            buckets.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
            bucketCourse.putIfAbsent(key, c);
        }
        }

        for (String key : buckets.keySet())
        {
            ArrayList<Student> members = buckets.get(key);
            if (members.size() >= 2)
            {
                Course c = bucketCourse.get(key);
                try
                {
                    result.add(new Group(c, members));
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("Could not form group for " + c.getCourseName() + ": " + e.getMessage());
                }
            }
        }
        return result;
    }

    /**
     * Builds a normalized identity key for a Course so that two Course
     * objects describing the same real class -- one built from a CRN
     * lookup, the other typed in manually as name/days/time -- land in the
     * same bucket even if their raw strings differ in case, spacing, or
     * day order.
     *
     * @param c The course to key.
     * @return A normalized "name|days|time" key.
     */
    private String courseKey(Course c)
    {
        String name = c.getCourseName().trim().toUpperCase();

        char[] dayChars = c.getDays().replaceAll("\\s+", "").toUpperCase().toCharArray();
        java.util.Arrays.sort(dayChars);
        String days = new String(dayChars);

        String time = c.getTime().replaceAll("\\s+", "").toUpperCase();

        return name + "|" + days + "|" + time;
    }
 
    /**
     * Prints every registered student and the courses they're enrolled in.
     */
    public void listStudents()
    {
        if (students.isEmpty())
        {
            System.out.println("No students added yet.");
            return;
        }
        System.out.println("Students:");
        for (Student s : students)
        {
            ArrayList<Course> courses = s.getCourses();
            StringBuilder sb = new StringBuilder("  " + s.getName() + " - ");
            if (courses.isEmpty())
            {
                sb.append("(no courses added yet)");
            }
            else
            {
                for (int i = 0; i < courses.size(); i++)
                {
                    if (i > 0) sb.append(", ");
                    sb.append(courses.get(i).getCourseName());
                }
            }
            System.out.println(sb);
        }
    }
 
    /**
     * Prints every currently formed study group and its meeting location.
     */
    private void listGroups()
    {
        if (groups.isEmpty())
        {
            System.out.println("No groups formed yet. Try 'make group'.");
            return;
        }
        for (Group g : groups)
        {
            System.out.print(g);
            if (!locations.isEmpty())
            {
                int idx = (int)(Math.random() * locations.size());
                System.out.print("Suggested Meeting Location: " + locations.get(idx) + "\n");
            }
        }
    }
 
    /**
     * Gets the list of registered students.
     * 
     * @return ArrayList of students.
     */
    public ArrayList<Student> getStudents()
    {
        return students;
    }
 
    /**
     * Gets the list of currently formed groups.
     * 
     * @return ArrayList of groups.
     */
    public ArrayList<Group> getGroups()
    {
        return groups;
    }

    /**
     * Main entry point to launch the program.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args)
    {
        Main app = new Main(); 
        app.startConsole();
    }
}