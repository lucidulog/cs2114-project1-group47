import java.util.ArrayList;
//-------------------------------------------------------------------------
/**
*  This is the Group class. A Group is one study group, which means every
*  student in it is taking the same course on the same days at the same
*  time. It stores the Course the group was built around along with the
*  roster of students, and provides methods like addMember(), contains(),
*  and size() to manage that roster.
*
*  @author Zongqi Nie
*  @version (2026.09.19)
*/

public class Group
{
    //~Fields.............................................................
    private Course course;
    private ArrayList<Student> members;

    //~ Constructor.......................................................

    // -------------------------------------------------------------------
    /**
     * Creates a new Group for the given course and fills it with a
     * starting roster. A student listed twice is only stored once.
     *
     * @param course    The course this group meets for.
     * @param members   The students that belong to this group.
     */
    public Group(Course course, ArrayList<Student> members)
    {
        if (course == null)
        {
            throw new IllegalArgumentException("Course cannot be null");
        }
        if (members == null)
        {
            throw new IllegalArgumentException("Member list cannot be null");
        }
        this.course = course;
        this.members = new ArrayList<>();
        for (Student student : members)
        {
            addIfAbsent(student);
        }
    }

    //~ Methods...........................................................

    // -------------------------------------------------------------------
    /**
     * Gets the course this group was built around.
     *
     * @return          The group's Course object.
     */
    public Course getCourse()
    {
        return course;
    }

    // -------------------------------------------------------------------
    /**
     * Getter method to return the course name of this group.
     *
     * @return          The course name, such as "CS_2114".
     */
    public String getCourseName()
    {
        return course.getCourseName();
    }

    // -------------------------------------------------------------------
    /**
     * Getter method to return the meeting days of this group.
     *
     * @return          The meeting days, such as "MWF" or "TR".
     */
    public String getDays()
    {
        return course.getDays();
    }

    // -------------------------------------------------------------------
    /**
     * Getter method to return the meeting time of this group.
     *
     * @return          The meeting time, such as "3:30PM-4:20PM".
     */
    public String getTime()
    {
        return course.getTime();
    }

    // -------------------------------------------------------------------
    /**
     * Gets the students that are currently in this group.
     *
     * @return          An ArrayList containing the group's members.
     */
    public ArrayList<Student> getMembers()
    {
        return members;
    }

    // -------------------------------------------------------------------
    /**
     * Returns how many students are in this group.
     *
     * @return          The number of members.
     */
    public int size()
    {
        return members.size();
    }

    // -------------------------------------------------------------------
    /**
     * Checks whether this group has no members yet.
     *
     * @return          True if the group has no members.
     */
    public boolean isEmpty()
    {
        return members.isEmpty();
    }

    // -------------------------------------------------------------------
    /**
     * Adds a student to this group. A student who is already a member is
     * not added a second time.
     *
     * @param student   The student to add.
     * @return          True if the student was added, false if that
     *                  student was already in the group.
     */
    public boolean addMember(Student student)
    {
        return addIfAbsent(student);
    }

    // -------------------------------------------------------------------
    /**
     * Removes a student from this group.
     *
     * @param student   The student to remove.
     * @return          True if that student was in the group and got
     *                  removed, false otherwise.
     */
    public boolean removeMember(Student student)
    {
        if (student == null)
        {
            return false;
        }
        for (int i = 0; i < members.size(); i++)
        {
            if (sameName(members.get(i), student))
            {
                members.remove(i);
                return true;
            }
        }
        return false;
    }

    // -------------------------------------------------------------------
    /**
     * Checks whether a student is already in this group. Students are
     * compared by name.
     *
     * @param student   The student to look for.
     * @return          True if that student is a member.
     */
    public boolean contains(Student student)
    {
        return hasMember(student);
    }

    // -------------------------------------------------------------------
    /**
     * Checks whether another course is the same section as this group's
     * course. The course name, days, and time must all match, ignoring
     * upper and lower case. A scheduler uses this to decide which group
     * a student's course belongs to.
     *
     * @param other     The course to compare against.
     * @return          True if that course is the same section.
     */
    public boolean matchesSection(Course other)
    {
        if (other == null)
        {
            return false;
        }
        return course.getCourseName().equalsIgnoreCase(other.getCourseName())
            && course.getDays().equalsIgnoreCase(other.getDays())
            && course.getTime().equalsIgnoreCase(other.getTime());
    }

    // -------------------------------------------------------------------
    /**
     * Builds a printable description of this group. The first line is the
     * course, then each member is listed on its own line underneath.
     *
     * @return          A string describing the group and its members.
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(course.toString());
        builder.append(" (");
        builder.append(members.size());
        if (members.size() == 1)
        {
            builder.append(" student)\n");
        }
        else
        {
            builder.append(" students)\n");
        }

        for (Student member : members)
        {
            builder.append("  - ");
            builder.append(member.getName());
            builder.append("\n");
        }
        return builder.toString();
    }

    // -------------------------------------------------------------------
    /**
     * Helper that does the real work of adding a student, so that the
     * constructor does not have to call a public method.
     *
     * @param student   The student to add.
     * @return          True if the student was added, false if that
     *                  student was already in the group.
     */
    private boolean addIfAbsent(Student student)
    {
        if (student == null)
        {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (hasMember(student))
        {
            return false;
        }
        members.add(student);
        return true;
    }

    // -------------------------------------------------------------------
    /**
     * Helper that searches the roster for a student, so that the
     * constructor never has to call a public method.
     *
     * @param student   The student to look for.
     * @return          True if that student is already a member.
     */
    private boolean hasMember(Student student)
    {
        if (student == null)
        {
            return false;
        }
        for (Student member : members)
        {
            if (sameName(member, student))
            {
                return true;
            }
        }
        return false;
    }

    // -------------------------------------------------------------------
    /**
     * Helper that compares two students by name without breaking if a
     * name happens to be null.
     *
     * @param first     The first student.
     * @param second    The second student.
     * @return          True if both students have the same name.
     */
    private boolean sameName(Student first, Student second)
    {
        if (first.getName() == null || second.getName() == null)
        {
            return first.getName() == second.getName();
        }
        return first.getName().equals(second.getName());
    }
}
