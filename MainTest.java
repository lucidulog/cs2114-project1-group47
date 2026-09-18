import static org.junit.Assert.*;
import junit.framework.TestCase;

public class MainTest
extends TestCase
{
//~Fields...................................................................
private Main mainObject;


public MainTest()
{
    // Required empty constructor 
}

public void setUp()
{
    mainObject = new Main();
}

/**
 * tests the welcomeMessage 
 */
public void testWelcomeMessage()
{
    String result = Main.welcomeMessage();
    
    assertNotNull(result);
    
    assertTrue(result.contains("Welcome to HokieConnect!")); 
    assertTrue(result.contains("Type 'help' for commands."));  
}
/**
 * Tests the helpMessage() 
 */
public void testHelpMessage()
{
    String result = Main.helpMessage(); 
    
    assertTrue(result.contains("Commands:")); 
    assertTrue(result.contains("add student:")); 
    assertTrue(result.contains("add course:")); 
    assertTrue(result.contains("make group:")); 
    assertTrue(result.contains("help")); 
    assertTrue(result.contains("quit:")); 
}

/**
 * Tests the classInputMessage() helper 
 */
public void testClassInputMessage()
{
    String result = Main.classInputMessage(); 
    
    assertNotNull(result); 
    assertTrue(result.contains("Add your classes using one of these formats:")); 
}

/**
 * Tests the processCommand() with true inputs
 */
public void testProcessCommandTrue()
{
    Main.processCommand("help"); 
    Main.processCommand("add student");
    Main.processCommand("add course"); 
    Main.processCommand("make group"); 
    
    Main.processCommand("HELP"); 
    Main.processCommand("ADD STUDENT");
}

/**
 * Test the invalid or bad inputs for processCommand
 */
public void testProcessCommandFalse()
{
    Main.processCommand("foobar"); 
    
    Main.processCommand("add"); 
    
    Main.processCommand("  "); 
    
    Main.processCommand(""); 
    
    Main.processCommand(null); 
}

/**
 * Test running the main method using a console input
 */
public void testMainNormal()
{
    
    String[] args = {}; 
    Main.main(args); 
}
}