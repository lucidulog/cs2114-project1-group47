import java.util.ArrayList;

/**
 * Stores one study group and its course.
 *
 * @author Zongqi Nie
 * @version (2026.09.22)
 */
public class Group
{
    private Course course;
    private ArrayList<Student> members;

    /**
     * Creates a group for one course.
     *
     * @param course the course for this group
     * @param members the students in this group
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
            addMember(student);
        }
    }

    public Course getCourse()
    {
        return course;
    }

    public String getCourseName()
    {
        return course.getCourseName();
    }

    public String getDays()
    {
        return course.getDays();
    }

    public String getTime()
    {
        return course.getTime();
    }

    public ArrayList<Student> getMembers()
    {
        return members;
    }

    public ArrayList<Student> getStudents()
    {
        return members;
    }

    public int size()
    {
        return members.size();
    }

    public boolean isEmpty()
    {
        return members.isEmpty();
    }

    /**
     * Adds a student if that name is not already in the group.
     *
     * @param student the student to add
     * @return true when the student is added
     */
    public boolean addMember(Student student)
    {
        if (student == null)
        {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (contains(student))
        {
            return false;
        }

        members.add(student);
        return true;
    }

    public boolean removeMember(Student student)
    {
        if (student == null)
        {
            return false;
        }

        for (int i = 0; i < members.size(); i++)
        {
            if (sameStudent(members.get(i), student))
            {
                members.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean contains(Student student)
    {
        if (student == null)
        {
            return false;
        }

        for (Student member : members)
        {
            if (sameStudent(member, student))
            {
                return true;
            }
        }
        return false;
    }

    public boolean matchesSection(Course other)
    {
        if (other == null)
        {
            return false;
        }

        return getCourseName().equalsIgnoreCase(other.getCourseName())
            && getDays().equalsIgnoreCase(other.getDays())
            && getTime().equalsIgnoreCase(other.getTime());
    }

    @Override
    public String toString()
    {
        String text = course.toString() + " (" + members.size();

        if (members.size() == 1)
        {
            text += " student)\n";
        }
        else
        {
            text += " students)\n";
        }

        for (Student member : members)
        {
            text += "  - " + member.getName() + "\n";
        }
        return text;
    }

    private boolean sameStudent(Student first, Student second)
    {
        if (first.getName() == null || second.getName() == null)
        {
            return first.getName() == second.getName();
        }
        return first.getName().equals(second.getName());
    }
}
