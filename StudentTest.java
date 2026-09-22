import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension
// import junit.framework.TestCase;

/**
 * Tests the Student class.
 */
public class StudentTest {
    
    private Student student1;
    
    /**
     * Set up for testing.
     */
    @BeforeEach
    public void setUp()
    {
        student1 = new Student("John Doe");
    }

    /**
     * Tests the getName method.
     */
    @Test
    public void testGetName()
    {
        assertEquals("John Doe", student1.getName());
    }

    /**
     * Tests adding courses to a student and retrieving the list of courses.
     */
    @Test
    public void testAddCourseAndGetCourses()
    {
        Course course1 = new Course("CS_2114", "9:00AM-11:00AM", "TR");
        Course course2 = new Course("MATH_2204", "1:00PM-2:00PM", "MWF");
        
        student1.addCourse(course1);
        student1.addCourse(course2);
        
        assertEquals(2, student1.getCourses().size());
        assertTrue(student1.getCourses().contains(course1));
        assertTrue(student1.getCourses().contains(course2));
    }

    /**
     * Tests that a new student has an empty list of courses.
     */
    @Test
    public void testGetCoursesEmpty()
    {
        assertTrue(student1.getCourses().isEmpty());
    }

    /**
     * Tests that adding a null course throws a NullPointerException.
     */
    @Test
    public void testAddCourseNull()
    {
        assertThrows(NullPointerException.class, () -> {
            student1.addCourse(null);
        });
    }

}
