import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Holds and manages a collection of Course objects for a school. */
public class CourseCatalog {

    private final List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean removeByCourseNumber(int courseNumber) {
        return courses.removeIf(c -> c.getCourseNumber() == courseNumber);
    }

    public Optional<Course> findByCourseNumber(int courseNumber) {
        return courses.stream()
                .filter(c -> c.getCourseNumber() == courseNumber)
                .findFirst();
    }

    public List<Course> findByCourseName(String courseName) {
        List<Course> matches = new ArrayList<>();
        for (Course c : courses) {
            if (c.getCourseName().equalsIgnoreCase(courseName)) {
                matches.add(c);
            }
        }
        return matches;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses); // defensive copy
    }

    public int size() {
        return courses.size();
    }
}