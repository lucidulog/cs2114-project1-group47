import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
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

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created Main object.
     */
    public Main()
    {
        students = new ArrayList<>();
    }
    
    //~ Methods ...............................................................

    /**
     * Prints the greeting message to the console.
     */
    public void printWelcome()
    {
        System.out.println("Welcome to HokieConnect! Please enter your time to get started, "
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
        System.out.println("  Step 1 Name     : CS_2114         (Department_Number with underscore)");
        System.out.println("  Step 2 Days     : TR or MWF or F  (Meeting days)");
        System.out.println("  Step 3 Time     : 3:30PM-4:20PM   (Start-End time range)");
    }

    /**
     * Prints the accepted formats for adding a course.
     */
    public void printClassInputInstructions()
    {
        System.out.println("Please add the class using one of the following formats:");
        System.out.println(" 1) CRN Number: Enter the 5-digit CRN (e.g., 12345)");
        System.out.println(" 2) Class & Time: Enter class name and time slot (e.g., CS 2114 | MWF3:30-4:20)");
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
            System.out.println("Group creation feature coming soon!"); // should call doMakeGroups()
        }
        else if (command.equalsIgnoreCase("students"))
        {
            System.out.println(students.size() + " student(s) registered:");
            for (Student s : students)
            {
                System.out.println(" - " + s.getName());
            }
        }
        else if (command.equalsIgnoreCase("groups"))
        {
            System.out.println("View all groups feature coming soon!"); //listGroups();
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
    public void addCoursesForCurrentStudent(Scanner scanner)
    {
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

            if (parts.length != 3)
            {
                System.out.println("Invalid format! Must enter all 3 parts separated by spaces.");
                System.out.println("Example: CS_2114 TR 3:30PM-4:20PM\n");
                continue;
            }

            try
            {
                // parts[0] = Name, parts[1] = Days, parts[2] = Time
                Course newCourse = new Course(parts[0], parts[2], parts[1]);
                currentStudent.addCourse(newCourse);
                System.out.println("Added: " + newCourse + "\n");
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Error: " + e.getMessage() + "\n");
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
     * Gets the list of registered students.
     * 
     * @return ArrayList of students.
     */
    public ArrayList<Student> getStudents()
    {
        return students;
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