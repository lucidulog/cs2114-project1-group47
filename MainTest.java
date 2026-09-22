import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**

* Tests the Main class.
  */
  public class MainTest
  {
      private Main main;
    
      /**
    
      * Sets up a new Main object before each test.
        */
        @BeforeEach
        public void setUp()
        {
            main = new Main();
        }
    
      /**
    
      * Tests the Main constructor.
        */
        @Test
        public void testConstructor()
        {
            assertTrue(main.getStudents().isEmpty());
            assertTrue(main.getGroups().isEmpty());
        }
    
      /**
    
      * Tests the getStudents and getGroups methods.
        */
        @Test
        public void testGetters()
        {
            assertNotNull(main.getStudents());
            assertNotNull(main.getGroups());
        
            assertEquals(0, main.getStudents().size());
            assertEquals(0, main.getGroups().size());
        }
    
      /**
    
      * Tests printWelcome without checking console output.
        */
        @Test
        public void testPrintWelcome()
        {
            assertDoesNotThrow(() -> main.printWelcome());
        }
    
      /**
    
      * Tests printHelp without checking console output.
        */
        @Test
        public void testPrintHelp()
        {
            assertDoesNotThrow(() -> main.printHelp());
        }
    
      /**
    
      * Tests printClassInputInstructions without checking console output.
        */
        @Test
        public void testPrintClassInputInstructions()
        {
            assertDoesNotThrow(() -> main.printClassInputInstructions());
        }
    
      /**
    
      * Tests adding a valid student.
        */
        @Test
        public void testAddStudent()
        {
        Scanner scanner = new Scanner("Kyle\n");
    
        main.addStudent(scanner);
    
        assertEquals(1, main.getStudents().size());
        assertEquals("Kyle", main.getStudents().get(0).getName());
        }
    
      /**
    
      * Tests that a blank student name is not added.
        */
        @Test
        public void testAddStudentBlank()
        {
        Scanner scanner = new Scanner("\n");
    
        main.addStudent(scanner);
    
        assertTrue(main.getStudents().isEmpty());
        }
    
      /**
    
      * Tests adding multiple students.
        */
        @Test
        public void testAddMultipleStudents()
        {
            main.addStudent(new Scanner("Kyle\n"));
            main.addStudent(new Scanner("Hannah\n"));
            main.addStudent(new Scanner("Luci\n"));
        
            assertEquals(3, main.getStudents().size());
            assertEquals("Kyle", main.getStudents().get(0).getName());
            assertEquals("Hannah", main.getStudents().get(1).getName());
            assertEquals("Luci", main.getStudents().get(2).getName());
        }
    
      /**
    
      * Tests adding a course to the current student.
        */
        @Test
        public void testAddCoursesForCurrentStudent()
        {
            main.addStudent(new Scanner("Gracie\n"));
        
            Scanner scanner = new Scanner(
                "CS_2114 TR 3:30PM-4:20PM\ndone\n");
        
            main.addCoursesForCurrentStudent(scanner);
        
            Student student = main.getStudents().get(0);
        
            assertEquals(1, student.getCourses().size());
            assertEquals("CS_2114", student.getCourses().get(0).getCourseName());
            assertEquals("TR", student.getCourses().get(0).getDays());
            assertEquals("3:30PM-4:20PM", student.getCourses().get(0).getTime());
        }
    
      /**
    
      * Tests adding a course using a CRN.
        */
        @Test
        public void testAddCourseUsingCRN()
        {
            main.addStudent(new Scanner("Janelle\n"));
        
            Scanner scanner = new Scanner("CRN83531\ndone\n");
        
            main.addCoursesForCurrentStudent(scanner);
        
            Student student = main.getStudents().get(0);
        
            assertEquals(1, student.getCourses().size());
            assertEquals("CS_2114", student.getCourses().get(0).getCourseName());
        }
    
      /**
    
      * Tests that invalid course input is not added.
        */
        @Test
        public void testInvalidCourseInput()
        {
            main.addStudent(new Scanner("Gracie\n"));
        
            Scanner scanner = new Scanner("CS_2114 TR\ndone\n");
        
            main.addCoursesForCurrentStudent(scanner);
        
            Student student = main.getStudents().get(0);
        
            assertTrue(student.getCourses().isEmpty());
        }
    
      /**
    
      * Tests that multiple courses can be added to one student.
        */
        @Test
        public void testAddMultipleCourses()
        {
            main.addStudent(new Scanner("Luci\n"));
        
            Scanner scanner = new Scanner(
                "CS_2114 TR 3:30PM-4:20PM\n"
                + "MATH_2204 MWF 1:00PM-2:00PM\n"
                + "done\n");
        
            main.addCoursesForCurrentStudent(scanner);
        
            Student student = main.getStudents().get(0);
        
            assertEquals(2, student.getCourses().size());
            assertEquals("CS_2114", student.getCourses().get(0).getCourseName());
            assertEquals("MATH_2204", student.getCourses().get(1).getCourseName());
        }
    
      /**
    
      * Tests processCommand when adding a student.
        */
        @Test
        public void testProcessCommandAddStudent()
        {
            Scanner scanner = new Scanner("Emma\n");
        
            main.processCommand("add student", scanner);
        
            assertEquals(1, main.getStudents().size());
            assertEquals("Emma", main.getStudents().get(0).getName());
        }
    
      /**
    
      * Tests processCommand when adding a course.
        */
        @Test
        public void testProcessCommandAddCourse()
        {
            main.addStudent(new Scanner("Emma\n"));
        
            Scanner scanner = new Scanner(
                "CS_2114 TR 3:30PM-4:20PM\ndone\n");
        
            main.processCommand("add course", scanner);
        
            assertEquals(1, main.getStudents().get(0).getCourses().size());
        }
    
      /**
    
      * Tests processCommand with an empty student list.
      * The command should not change the list of students or groups.
        */
        @Test
        public void testProcessCommandAddCourseWithoutStudent()
        {
            Scanner scanner = new Scanner("");
        
            main.processCommand("add course", scanner);
        
            assertTrue(main.getStudents().isEmpty());
            assertTrue(main.getGroups().isEmpty());
        }
    
      /**
    
      * Tests processCommand with an unknown command.
      * It should not change the application state.
        */
        @Test
        public void testProcessCommandUnknownCommand()
        {
            Scanner scanner = new Scanner("");
        
            main.processCommand("foobar", scanner);
        
            assertTrue(main.getStudents().isEmpty());
            assertTrue(main.getGroups().isEmpty());
        }
    
      /**
    
      * Tests viewing the schedule for a student that does not exist.
      * The method should not change the student list.
        */
        @Test
        public void testViewStudentScheduleNotFound()
        {
            Scanner scanner = new Scanner("Nobody\n");
        
            main.viewStudentSchedule(scanner);
        
            assertTrue(main.getStudents().isEmpty());
        }
    
      /**
    
      * Tests viewing the schedule for an existing student.
        */
        @Test
        public void testViewStudentSchedule()
        {
            main.addStudent(new Scanner("Josie\n"));
        
            Scanner courseScanner = new Scanner(
                "CS_2114 TR 3:30PM-4:20PM\ndone\n");
        
            main.addCoursesForCurrentStudent(courseScanner);
        
            Scanner viewScanner = new Scanner("Josie\n");
        
            main.viewStudentSchedule(viewScanner);
        
            assertEquals(1, main.getStudents().size());
            assertEquals(1,main.getStudents().get(0).getCourses().size());
        }
    
      /**
    
      * Tests makeGroups with two students sharing a course.
        */
        @Test
        public void testMakeGroups()
        {
            Student student1 = new Student("Kyle");
            Student student2 = new Student("Hank");
        
            Course course1 =new Course(
                "CS_2114", "3:30PM-4:20PM", "TR");
            Course course2 =new Course(""
                + "CS_2114", "3:30PM-4:20PM", "TR");
        
            student1.addCourse(course1);
            student2.addCourse(course2);
        
            ArrayList<Student> students = new ArrayList<>();
            students.add(student1);
            students.add(student2);
        
            ArrayList<Group> groups = main.makeGroups(students);
        
            assertEquals(1, groups.size());
            assertEquals(2, groups.get(0).size());
            assertTrue(groups.get(0).contains(student1));
            assertTrue(groups.get(0).contains(student2));
        }
    
      /**
    
      * Tests makeGroups when students do not share a course.
        */
        @Test
        public void testMakeGroupsNoMatch()
        {
            Student student1 = new Student("Kyle");
            Student student2 = new Student("Ivy");
        
            student1.addCourse(new Course(
                "CS_2114", "3:30PM-4:20PM", "TR"));
        
            student2.addCourse(new Course(
                "MATH_1226", "9:05AM-9:55AM", "MWF"));
        
            ArrayList<Student> students = new ArrayList<>();
            students.add(student1);
            students.add(student2);
        
            ArrayList<Group> groups = main.makeGroups(students);
        
            assertTrue(groups.isEmpty());
        }
    
      /**
    
      * Tests that makeGroups does not create a group
      * with fewer than two students.
        */
        @Test
        public void testMakeGroupsOneStudent()
        {
            Student student = new Student("Kyle");
        
            student.addCourse(new Course(
                "CS_2114", "3:30PM-4:20PM", "TR"));
        
            ArrayList<Student> students = new ArrayList<>();
            students.add(student);
        
            ArrayList<Group> groups = main.makeGroups(students);
        
            assertTrue(groups.isEmpty());
        }
    
      /**
    
      * Tests doMakeGroups when there are no students.
        */
        @Test
        public void testDoMakeGroupsNoStudents()
        {
            main.doMakeGroups();
        
            assertTrue(main.getGroups().isEmpty());
        }
    
      /**
    
      * Tests doMakeGroups when two students share a course.
        */
        @Test
        public void testDoMakeGroups()
        {
            Student student1 = new Student("Josie");
            Student student2 = new Student("Hank");
        
            Course course1 = new Course(
                "CS_2114", "3:30PM-4:20PM", "TR");
            Course course2 = new Course(
                "CS_2114", "3:30PM-4:20PM", "TR");
        
            student1.addCourse(course1);
            student2.addCourse(course2);
        
            main.getStudents().add(student1);
            main.getStudents().add(student2);
        
            main.doMakeGroups();
        
            assertEquals(1, main.getGroups().size());
            assertEquals(2, main.getGroups().get(0).size());
        }
    
      /**
    
      * Tests listStudents without relying on console output.
        */
        @Test
        public void testListStudents()
        {
            main.listStudents();
    
            main.addStudent(new Scanner("Jack\n"));
    
            assertEquals(1, main.getStudents().size());
            assertEquals("Jack", main.getStudents().get(0).getName());
    
            main.listStudents();
        }
    }
