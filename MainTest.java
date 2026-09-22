import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//As a Hokie, I will conduct myself with honor and integrity at all times.
//I will not lie, cheat, or steal, nor will I accept the actions of those
//who do.
//-- Luci Dulog (906619962)
public class MainTest
    extends student.TestCase
{
    //~ Fields ................................................................
    private Main main; 

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    /**
     * This is the setUp method that is run before every test. 
     */
    @BeforeEach
    public void setUp()
    {
        main = new Main(); 
    }
    
    /**
     * tests the Main() constructor 
     * should not have students or groups registered
     */
    @Test
    public void testConstructor()
    {
        assertEquals(0, main.getStudents().size());
        assertEquals(0, main.getGroups().size()); 
    }
    
    /**
     * Tests the printWelcome() method
     */
    @Test
    public void testPrintWelcome()
    {
        main.printWelcome(); 
        String output = getOutput();
        assertTrue(output.contains("Welcome to HokieConnect!"));
    }
    
    /**
     * Tests the printHelp() method
     */
    @Test
    public void testPrintHelp()
    {
        main.printHelp();
        String output = getOutput();
        assertTrue(output.contains("Commands"));
        assertTrue(output.contains("add student"));
        assertTrue(output.contains("make group"));
    }
    
    /**
     * Tests the printClassInputInstructions() method 
     */
    @Test
    public void testPrintClassInputInstructions()
    {
        main.printClassInputInstructions(); 
        String output = getOutput(); 
        assertTrue(output.contains("CRN Number")); 
        assertTrue(output.contains("Class & Time"));
    }
    
    /**
     * Tests the processCommand() with "help" and an 
     * unrecognized command 
     */
    @Test
    public void testProcessCommand()
    {
        Scanner scanner = new Scanner(""); 
        main.processCommand("help", scanner); 
        String output = getOutput(); 
        assertTrue(output.contains("Commands")); 
        
        notifySystemOut();
        main.processCommand("foobar", scanner); 
        output = getOutput(); 
        assertTrue(output.contains("Unknown command, type 'help'")); 
    }
    
    /**
     * Tests the processCommand() with "add course" without any 
     * students being added: should be an error and doesn't crash
     */
    @Test
    public void testProcessCommandAddCourseNoStudent()
    {
        Scanner scanner = new Scanner(""); 
        main.processCommand("add course", scanner); 
        String output = getOutput(); 
        assertTrue(output.contains(
            "Error: You must add a student first before adding courses!")); 
    }
    
    /**
     * Tests the addStudent(Scanner) method. checks when a student 
     * is added and when it is blank 
     */
    @Test
    public void testAddStudent()
    {
        Scanner scanner = new Scanner("Kyle\n"); 
        main.addStudent(scanner); 
        
        assertEquals(1, main.getStudents().size()); 
        assertEquals("Kyle", main.getStudents().get(0).getName()); 
        
        notifySystemOut();
        Scanner blank = new Scanner("\n"); 
        main.addStudent(blank); 
        
        assertEquals(1, main.getStudents().size()); 
        String output = getOutput(); 
        assertTrue(output.contains("Student name cannot be empty"));      
    }
    
    /**
     * Tests the addCoursesForCurrentStudent(Scanner) method 
     * with a valid and invalid entry. 
     */
    @Test
    public void testAddCoursesForCurrentStudent()
    {
        Scanner nameScanner = new Scanner("Gracie\n"); 
        main.addStudent(nameScanner); 
        
        Scanner courseScanner = new Scanner(
            "CS_2114 TR 3:30PM-4:20PM\ndone\n"); 
        main.addCoursesForCurrentStudent(courseScanner); 
        
        assertEquals(1, main.getStudents().get(0).getCourses().size()); 
        
        notifySystemOut();
        Scanner badScanner = new Scanner("CS_2114 TR\ndone\n"); 
        main.addCoursesForCurrentStudent(badScanner); 
        
        assertEquals(1, main.getStudents().get(0).getCourses().size()); 
        
        String output = getOutput(); 
        assertTrue(output.contains("Invalid format!")); 
    }
    
    /**
     * Tests the addCoursesForCurrentStudent(Scanner) using the 
     * CRN entry format
     */
    @Test
    public void testAddCoursesForCurrentStudentCRN()
    {
        Scanner nameScanner = new Scanner("Janelle\n"); 
        main.addStudent(nameScanner); 
        
        Scanner crnScanner = new Scanner("CRN12345\ndone\n"); 
        main.addCoursesForCurrentStudent(crnScanner); 
        assertEquals(1, main.getStudents().get(0).getCourses().size()); 
        
        String output = getOutput(); 
        assertTrue(output.contains("Added:")); 
    }
    
    /**
     * Tests the viewStudentSchedule(Scanner) for current students 
     * and those that don't exist or were never added 
     */
    @Test
    public void testViewStudentSchedule()
    {
        Scanner nameScanner = new Scanner("Josie\n"); 
        main.addStudent(nameScanner); 
        Scanner courseScanner = new Scanner(
            "CS_2114 TR 3:30PM-4:20PM\ndone\n");
        main.addCoursesForCurrentStudent(courseScanner); 
        
        Scanner viewScanner = new Scanner("Nobody\n"); 
        main.viewStudentSchedule(viewScanner); 
        String output = getOutput(); 
        assertTrue(output.contains("Student 'Nobody' was not found.")); 
    }
    
    /**
     * tests the doMakeGroups() method with no students, and after 
     * two students with the same course and time slot 
     */
    @Test
    public void testDoMakeGroups()
    {
        main.doMakeGroups(); 
        String output = getOutput(); 
        assertTrue(output.contains("No students have been added yet.")); 
        
        notifySystemOut();
        Scanner name1 = new Scanner("Josie\n"); 
        main.addStudent(name1); 
        Scanner course1 = new Scanner("CS_2114 TR 3:30PM-4:20PM\ndone\n");
        main.addCoursesForCurrentStudent(course1); 
        
        Scanner name2 = new Scanner("Hank\n"); 
        main.addStudent(name2); 
        Scanner course2 = new Scanner("CS_2114 TR 3:30PM-4:20PM\ndone\n");
        main.addCoursesForCurrentStudent(course2); 

        main.doMakeGroups(); 
        assertEquals(1, main.getGroups().size()); 
    }
    
    /**
     * tests makeGroups(ArrayList<Student>) with a shared course 
     * and time slot this should create a group and other way 
     * around 
     */
    @Test
    public void testMakeGroups()
    {
        Student s1 = new Student("Kyle"); 
        s1.addCourse(new Course("CS_2114", "TR 3:30PM-4:20PM", "TR")); 
        Student s2 = new Student("Hank"); 
        s2.addCourse(new Course("CS_2114", "TR 3:30PM-4:20PM", "TR")); 
        
        ArrayList<Student> matching = new ArrayList<>(); 
        matching.add(s1);
        matching.add(s2);
        ArrayList<Group> groups = main.makeGroups(matching);
        assertEquals(1, groups.size());
        
        Student s3 = new Student("Ivy");
        s3.addCourse(new Course("MATH_1226", "MWF 9:05AM-9:55AM", "MWF"));
        ArrayList<Student> noMatch = new ArrayList<>();
        noMatch.add(s1);
        noMatch.add(s3);
        ArrayList<Group> emptyGroups = main.makeGroups(noMatch);
        assertEquals(0, emptyGroups.size());
    }
    
    /**
     * Tests listStudents() with no students and with students 
     * that have courses registered
     */
    @Test
    public void testListStudents()
    {
        main.listStudents();
        String output = getOutput();
        assertTrue(output.contains("No students added yet."));
 
        notifySystemOut();
        Scanner nameScanner = new Scanner("Jack\n");
        main.addStudent(nameScanner);
        main.listStudents();
        output = getOutput();
        assertTrue(output.contains("Jack"));
    }
    
    /**
     * Tests the getStudents() and getGroups() getter methods
     */
    @Test
    public void testGetters()
    {
        assertTrue(main.getStudents().isEmpty()); 
        assertTrue(main.getGroups().isEmpty()); 
        
        Scanner nameScanner = new Scanner("Emma\n"); 
        main.addStudent(nameScanner); 
        assertEquals(1, main.getStudents().size()); 
    }
    
    /**
     * Tests startConsole() by simulating typed commands
     */
    @Test
    public void testStartConsole()
    {
        setSystemIn("help\nfoobar\nexit\n");
        main.startConsole();
        String output = getOutput();
        assertTrue(output.contains("Welcome to HokieConnect!"));
        assertTrue(output.contains("Unknown command, type 'help'"));
        assertTrue(output.contains("Goodbye!"));
    }
    
    /**
     * Tests startConsole() with only blank lines 
     * should not crash
     */
    @Test
    public void testStartConsoleBlankInput()
    {
        setSystemIn("\n\nexit\n");
        main.startConsole();
        String output = getOutput();
        assertTrue(output.contains("Goodbye!"));
    }
    
    /**
     * tests the main(String[]) entry point runs the console loop 
     * and exits it 
     */
    @Test
    public void testMain()
    {
        setSystemIn("exit\n"); 
        Main.main(new String[] {}); 
        String output = getOutput(); 
        
        assertTrue(output.contains("Welcome to HokieConnect!")); 
        assertTrue(output.contains("Goodbye!"));
    }
    
}