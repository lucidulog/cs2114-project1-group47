import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Group class.
 *
 * @author Zongqi Nie
 * @version (2026.09.22)
 */
public class GroupTest
{
    private Course course;
    private Student hannah;
    private Student luci;
    private ArrayList<Student> students;
    private Group group;

    @BeforeEach
    public void setUp()
    {
        course = new Course("CS_2114", "3:30PM-4:20PM", "TR");
        hannah = new Student("Hannah");
        luci = new Student("Luci");

        students = new ArrayList<>();
        students.add(hannah);
        students.add(luci);
        group = new Group(course, students);
    }

    @Test
    public void testGetters()
    {
        assertSame(course, group.getCourse());
        assertEquals("CS_2114", group.getCourseName());
        assertEquals("TR", group.getDays());
        assertEquals("3:30PM-4:20PM", group.getTime());
        assertEquals(2, group.getMembers().size());
        assertSame(group.getMembers(), group.getStudents());
    }

    @Test
    public void testInvalidConstructor()
    {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Group(null, students));
        assertThrows(
            IllegalArgumentException.class,
            () -> new Group(course, null));

        students.add(null);
        assertThrows(
            IllegalArgumentException.class,
            () -> new Group(course, students));
    }

    @Test
    public void testSizeAndIsEmpty()
    {
        assertEquals(2, group.size());
        assertFalse(group.isEmpty());

        Group empty = new Group(course, new ArrayList<Student>());
        assertEquals(0, empty.size());
        assertTrue(empty.isEmpty());
    }

    @Test
    public void testAddMember()
    {
        Student aneesh = new Student("Aneesh");

        assertTrue(group.addMember(aneesh));
        assertEquals(3, group.size());
        assertTrue(group.contains(aneesh));

        assertFalse(group.addMember(new Student("Aneesh")));
        assertEquals(3, group.size());

        assertThrows(
            IllegalArgumentException.class,
            () -> group.addMember(null));
    }

    @Test
    public void testRemoveMember()
    {
        assertTrue(group.removeMember(hannah));
        assertEquals(1, group.size());
        assertFalse(group.contains(hannah));

        assertFalse(group.removeMember(hannah));
        assertFalse(group.removeMember(null));
    }

    @Test
    public void testContains()
    {
        assertTrue(group.contains(hannah));
        assertTrue(group.contains(new Student("Hannah")));
        assertFalse(group.contains(new Student("Aneesh")));
        assertFalse(group.contains(null));
    }

    @Test
    public void testMatchesSection()
    {
        Course same = new Course("cs_2114", "3:30pm-4:20pm", "tr");
        Course different =
            new Course("MATH_2114", "3:30PM-4:20PM", "TR");

        assertTrue(group.matchesSection(same));
        assertFalse(group.matchesSection(different));
        assertFalse(group.matchesSection(null));
    }

    @Test
    public void testDuplicateStudent()
    {
        students.add(new Student("Hannah"));
        Group duplicateGroup = new Group(course, students);

        assertEquals(2, duplicateGroup.size());
    }

    @Test
    public void testToString()
    {
        String expected = "CS_2114 TR 3:30PM-4:20PM (2 students)\n"
            + "  - Hannah\n"
            + "  - Luci\n";

        assertEquals(expected, group.toString());
    }
}
