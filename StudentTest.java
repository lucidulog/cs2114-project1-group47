import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Aneesh_Implementation.Course;

/**
 * Tests for Student: one normal case and one bad-input case per method.
 */
public class StudentTest
{
    private Course cs2114;
    private Student student;

    /**
     * Sets up a student and a course before each test.
     */
    @BeforeEach
    public void setUp()
    {
        cs2114 = new Course("CS 2114", 12345, "MWF 9:00AM");
        student = new Student("Hannah");
    }

    /**
     * Normal case: a new student has a name and no courses.
     */
    @Test
    public void testConstructorNormal()
    {
        assertEquals("Hannah", student.getName());
        assertEquals(0, student.getCourses().size());
    }

    /**
     * Bad input: a null or blank name throws instead of creating a student.
     */
    @Test
    public void testConstructorBadName()
    {
        assertThrows(IllegalArgumentException.class, () -> new Student(null));
        assertThrows(IllegalArgumentException.class, () -> new Student(""));
    }

    /**
     * Normal case: getName returns the name that was passed in.
     */
    @Test
    public void testGetName()
    {
        Student other = new Student("Luci");

        assertEquals("Luci", other.getName());
    }

    /**
     * Normal case: adding a course puts it on the list.
     */
    @Test
    public void testAddCourseNormal()
    {
        student.addCourse(cs2114);

        assertEquals(1, student.getCourses().size());
        assertEquals("CS 2114", student.getCourses().get(0).getCourseName());
    }

    /**
     * Bad input: null and duplicate courses are ignored, so the list does not
     * change size.
     */
    @Test
    public void testAddCourseBadInput()
    {
        student.addCourse(cs2114);
        student.addCourse(null);
        student.addCourse(cs2114);

        assertEquals(1, student.getCourses().size());
    }

    /**
     * Normal case: getCourses returns every course that was added.
     */
    @Test
    public void testGetCoursesNormal()
    {
        Course math2114 = new Course("MATH 2114", 54321, "TR 11:00AM");
        student.addCourse(cs2114);
        student.addCourse(math2114);

        assertEquals(2, student.getCourses().size());
    }

    /**
     * Bad input: a student with no courses gets an empty list, not null.
     */
    @Test
    public void testGetCoursesEmpty()
    {
        assertEquals(0, student.getCourses().size());
    }
}
