import java.util.ArrayList; 
import java.util.Scanner; 

public class Main
{
    private static Scanner input = new Scanner(System.in);

    /**
     * Starts the program. Prints the welcome message, then reads
     * and processes commands until the user quits.
     *
     * @param args command line arguments, unused
     */
    public static void main(String[] args)
    {
        System.out.println(welcomeMessage());

        boolean running = true;
        while (running && input.hasNextLine())
        {
            String command = input.nextLine().trim();
            if (command.equalsIgnoreCase("quit"))
            {
                System.out.println("Goodbye!");
                running = false;
            }
            else
            {
                processCommand(command);
            }
        }
    }

    /**
     * Creates the welcome message as a string as well
     * as pointing the user toward the help command.
     *
     * @return a String greeting the user and giving
     *          them basic instructions
     */
    public static String welcomeMessage()
    {
        return "Welcome to HokieConnect!\n"
            + " Type 'help' for commands.";
    }

    /**
     * Creates the list of available commands as a string.
     *
     * @return a String listing every command the user can type
     */
    public static String helpMessage()
    {
        return "Commands:\n"
            + " add student: adds a student record\n"
            + " add course: shows how to enter your classes\n"
            + " make group: forms the study groups\n"
            + " help: displays instructions\n"
            + " quit: exits the program";
    }

    /**
     * Creates the instructions for entering classes as a string.
     *
     * @return a String explaining the accepted class formats
     */
    public static String classInputMessage()
    {
        return "Add your classes using one of these formats:\n"
            + " 1) CRN: the 5-digit number (e.g., 12345)\n"
            + " 2) Class and time (e.g., CS 2114 | MWF 3:30-4:20)";
    }

    /**
     * Interprets a command typed by the user and runs it.
     * Unknown or empty commands print a reminder to type help.
     *
     * @param command the raw text the user entered
     */
    public static void processCommand(String command)
    {
        if (command == null || command.trim().isEmpty())
        {
            System.out.println("Unknown command, type 'help'");
        }
        else if (command.equalsIgnoreCase("help"))
        {
            System.out.println(helpMessage());
        }
        else if (command.equalsIgnoreCase("add student")
            || command.equalsIgnoreCase("add course"))
        {
            System.out.println(classInputMessage());
        }
        else if (command.equalsIgnoreCase("make group"))
        {
            System.out.println("Group formation not yet implemented.");
        }
        else
        {
            System.out.println("Unknown command, type 'help'");
        }
    }

    /**
     * Organizes students into study groups based on the classes
     * they take and the times they are free.
     *
     * @param students every student who has entered a schedule
     * @return the groups that were formed
     */
    //public static ArrayList<Group> makeGroups(ArrayList<Student> students)
    //{
        //return new ArrayList<Group>();
    //}
}