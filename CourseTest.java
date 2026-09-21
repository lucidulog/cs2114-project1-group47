// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension
import junit.framework.TestCase;


/**
 * Tests the Course class.
 */
public class CourseTest extends student.TestCase
{
    private Course course1;
    private Course course2;


    /**
     * Sets up each test method.
     */
    public void setUp()
    {
        course1 = new Course("CS_2114", "9:00AM-11:00AM", "TR");
        course2 = new Course("MATH_2204", "1:00PM-2:00PM", "MWF");
    }


    /**
     * Tests Course's getters.
     */
    public void testGetters()
    {
        System.out.println(org.junit.jupiter.api.AssertionUtils.class.getPackage().getImplementationVersion());
        assertEquals("CS_2114", course1.getCourseName());
        assertEquals("9:00AM-11:00AM", course1.getTime());
        assertEquals("TR", course1.getDays());

        assertEquals("MATH_2204", course2.getCourseName());
        assertEquals("1:00PM-2:00PM", course2.getTime());
        assertEquals("MWF", course2.getDays());
    }


    /**
     * Tests Course's toString() method.
     */
    public void testToString()
    {
        assertEquals(
            "CS_2114 TR 9:00AM-11:00AM",
            course1.toString());

        assertEquals(
            "MATH_2204 MWF 1:00PM-2:00PM",
            course2.toString());
    }


    /**
     * Tests invalid course name.
     */
    public void testInvalidCourseName()
    {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Course("", "9:00AM-11:00AM", "TR"));
    }


    /**
     * Tests invalid days.
     */
    public void testInvalidDays()
    {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Course("CS_2114", "9:00AM-11:00AM", ""));
    }


    /**
     * Tests invalid time.
     */
    public void testInvalidTime()
    {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Course("CS_2114", null, "TR"));
    }


    /**
     * Tests the Course constructor using a CRN.
     */
    public void testCRNConstructor()
    {
        Course course = new Course("83531");

        assertEquals("CS_2114", course.getCourseName());
        assertEquals("3:30PM-4:20PM", course.getTime());
        assertEquals("TR", course.getDays());

        assertThrows(
            IllegalArgumentException.class,
            () -> new Course("78000"));
    }
}