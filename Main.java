import java.util.ArrayList;
public class Main 
{
    //private static CourseCatalog catalog; 
    
    public static void main(String[] args) 
    {
        printWelcome();
    }
    
    /**
     * This is the Welcome main method. 
     */
    public static void printWelcome()
    {
        System.out.println("Welcome to HokieConnect!"
            + "Please enter your name to get started, "
            + "or type 'help' to see all available commands.");
    }
    
    public static void printHelp()
    {
        System.out.println("Commands:\n"
            + " - help: displays instructions\n"
            + " - add student: adds a student record\n"
            + " - make group; Forms study groups once the last person "
            + " is done adding their classes");
    }
    
    /**
     * prints instructions on how to add classes
     */
    public static void printClassInputInstructions()
    {
        System.out.println("Please add your classes using the following formats:\n"
            + " 1) CRN Number: Enter the 5-digit CRN (e.g., 12345)\n"
            + " 2) Class & Time: Enter class name and time slot (e.g., CS 2114 | MWF3:30-4:20"); 
    }
    public static void processCommand(String command)
    {
        if (command == null || command.trim().isEmpty())
        {
            System.out.println("Unknown command, type 'help'");
            return;
        }
        if (command.equalsIgnoreCase("help"))
        {
            printHelp();
        }
        else if (command.equalsIgnoreCase("add course"))
        {
            printClassInputInstructions();
        }
        else if (command.equals("make group"))
        {
            System.out.println("Group formation not yet implemented.");
        }
        else
        {
            System.out.println("Unknown command, type 'help'");
        }
    }
    
    //public ArrayList<Group> makeGroups(ArrayList<student> students)
    //{
        //return new ArrayList<Group>(); 
    //}
}


