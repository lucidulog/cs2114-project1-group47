import java.util.ArrayList;

/**
 * A student and the courses they are enrolled in.
 */
public class Student
{
    private String name;
    private ArrayList<Course> courses;

    /**
     * Creates a student with an empty course list.
     *
     * @param name the student's name
     * @throws IllegalArgumentException if the name is null or blank
     */
    public Student(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException(
                "Invalid. Type help for correct formatting");
        }
        this.name = name;
        this.courses = new ArrayList<Course>();
    }

    /**
     * Gets the student's name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Adds a course to the student's list. Null courses and courses already on
     * the list are ignored.
     *
     * @param course the course to add
     */
    public void addCourse(Course course)
    {
        if (course == null || courses.contains(course))
        {
            return;
        }
        courses.add(course);
    }

    /**
     * Gets all the courses this student has.
     *
     * @return the course list, empty if none were added
     */
    public ArrayList<Course> getCourses()
    {
        return courses;
    }
}
