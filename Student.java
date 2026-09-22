import java.util.ArrayList;
//-------------------------------------------------------------------------
/**
*  This is the student class. It manages individual student records and 
*  their assigned academic schedules by providing core methods like 
*  getName(), getCourse(), and addCourse(). It serves to assign students to
*  their imputed classes. 
*
*  @author Hannah Dai
*  @version (2026.09.19)
*/

public class Student 
{
    //~Fields.............................................................
    private String name;
    private ArrayList<Course> courses;

    //~ Constructor.......................................................
    
    // -------------------------------------------------------------------
    /**
     * Creates a new Student object with the specified name and an empty
     * list of courses.
     * 
     * @param name      The full name of the student.
     */
    public Student(String name) 
    {
        this.name = name;
        this.courses = new ArrayList<>();
    }
    
    /**
     * Getter method to return the name of the student 
     * 
     * @return          The student's name. 
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Gets the list of courses this student is currently taking. 
     * 
     * @return          An ArrayList containing the student's courses
     */
    public ArrayList<Course> getCourses() 
    {
        return courses;
    }
    
    /**
     * Adds a new course to this student's schedule. 
     * 
     * @param course    The course object to be added
     */
    public void addCourse(Course course) 
    {
        if (course == null) {
            throw new NullPointerException("Course cannot be null");
        }
        courses.add(course);
    }
}