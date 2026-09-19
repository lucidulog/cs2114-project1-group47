import java.util.Scanner;
//-------------------------------------------------------------------------
/**
 *  This is the main class. It serves as the applicant's primary entry 
 *  point, displayin an initial welcome message and user guidance upon 
 *  startup. It manages execution by interpreting incoming user commands 
 *  and driving overall application. 
 *
 *  @author Luci Dulog (lucidulog)
 *  @version (2026.09.19)
 */
public class Main
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created Main object.
     */
    public Main()
    {
        //Default Constructor
    }
    
    /**
     * Prints the greeting message to the console
     */
    public void printWelcome()
    {
        System.out.println("Welcome to HokieConnect! Please enter your time to get started, "
            + "or type 'help' to see all available commands."); 
    }
    
    /**
     * Prints the basic command options for the user
     */
    public void printHelp()
    {
        System.out.println("Commands:");
        System.out.println(" - help: displays instructions"); 
        System.out.println(" - add student: adds a student record"); 
        System.out.println(" - make group: forms study groups once the last person " 
            + "is done adding their classes"); 
        System.out.println(" - exit: exits the application"); 
    }
    
    /**
     * Starts the command line console loop
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
                processCommand(command); 
            }
        }
        scanner.close();
    }
    
    /**
     * Processes command inputs by the user 
     * 
     * @param command       The string entered at the console prompt 
     *              
     */
    public void processCommand(String command)
    {
        if (command.equalsIgnoreCase("add student"))
        {
            System.out.println("What is the student's name?"); 
        }
        
        else if (command.equalsIgnoreCase("make group"))
        {
            System.out.println("Group creation feature coming soon!");
        }
        
        else 
        {
            System.out.println("Unkown command, type 'help'"); 
        }
    }
    
    /**
     * Main entry point to launch the program
     * 
     * @param args      Command-line arguments
     */
    public static void main(String[] args)
    {
        Main app = new Main(); 
        app.startConsole();
    }


    //~ Methods ...............................................................


}
