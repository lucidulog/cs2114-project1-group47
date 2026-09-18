import java.util.regex.Pattern;

/**
 * Represents a single course offering: name, CRN, and meeting time.
 * Objects are immutable once created; invalid input is rejected in the
 * constructor so a Course can never exist in a bad state.
 */
public class Course {

    // Days (any combo of M,T,W,R,F,S,U) + space + h:mm + AM/PM, e.g. "MWF 9:00AM"
    private static final Pattern TIME_PATTERN =
            Pattern.compile("^[MTWRFSU]+\\s+\\d{1,2}:\\d{2}(AM|PM)$");

    private final String courseName;
    private final int courseNumber;
    private final String time;

    public Course(String courseName, int courseNumber, String time) {
        if (courseName == null || courseName.isBlank()) {
            throw new IllegalArgumentException("Invalid. Type help for correct formatting");
        }
        if (courseNumber <= 0) {
            throw new IllegalArgumentException("Invalid. Type help for correct formatting");
        }
        if (time == null || !TIME_PATTERN.matcher(time).matches()) {
            throw new IllegalArgumentException("Invalid. Type help for correct formatting");
        }

        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.time = time;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return courseName + " (CRN " + courseNumber + ") - " + time;
    }
}