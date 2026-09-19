import java.util.ArrayList;
import java.util.Scanner;

//-------------------------------------------------------------------------
/**
 * Main command-line application interface for HokieConnect.
 * Manages user interactive console commands to add students and courses.
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
        System.out.println("Welcome to HokieConnect!" 
            +" Please type 'help' to see all available commands."); 
    }
    
    /**
     * Prints the basic command options for the user.
     */
    public void printHelp()
    {
        System.out.println("Commands:");
        System.out.println(" - help: displays instructions"); 
        System.out.println(" - add student: adds a student record"); 
        System.out.println(" - add course: adds a course to the current student");
        System.out.println(" - make group: forms study groups once the last person " 
            + "is done adding their classes"); 
        System.out.println(" - exit: exits the application"); 
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
                System.out.println("Ready to add course for: " 
                    + students.get(students.size() - 1).getName());
            }
        }
        else if (command.equalsIgnoreCase("make group"))
        {
            System.out.println("Group creation feature coming soon!");
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