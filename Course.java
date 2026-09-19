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

    private String course_name;
    private String time;
    private String days;

    public Course(String course_name, String time, String days) {
        if (course_name == null || course_name.isBlank()) {
            throw new IllegalArgumentException("Invalid. Type help for correct formatting");
        }
        if (days == null || days.isBlank()) {
            throw new IllegalArgumentException("Invalid. Type help for correct formatting");
        }
        if (time == null || !TIME_PATTERN.matcher(time).matches()) {
            throw new IllegalArgumentException("Invalid. Type help for correct formatting");
        }

        this.course_name = course_name;
        this.time = time;
        this.days = days;
    }

    public Course(int CRN) {
        
    }

    public String getCourseName() {
        return course_name;
    }

    public String getTime() {
        return time;
    }

    public String getDays() {
        return days;
    }

    @Override
    public String toString() {
        return course_name + " " + days + " " + time;
    }
}
