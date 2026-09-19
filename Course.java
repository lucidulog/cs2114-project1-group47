import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Represents a single course offering: name, CRN, and meeting time.
 * Objects are immutable once created; invalid input is rejected in the
 * constructor so a Course can never exist in a bad state.
 */
public class Course {
    private String course_name; //In the format of "CS_2114"
    private String time; //In the format of "9:00AM-11:00AM"
    private String days; //In the format of "TR" or "MWF"
    private HashMap<String, HashMap<String, Object>> course_dict;

    public Course(String course_name, String time, String days) {
        if (course_name == null || course_name.isBlank()) {
            throw new IllegalArgumentException("Course Name format is invalid. Type help for correct formatting");
        }
        if (days == null || days.isBlank()) {
            throw new IllegalArgumentException("Days format is invalid. Type help for correct formatting");
        }
        if (time == null || !TIME_PATTERN.matcher(time).matches()) {
            throw new IllegalArgumentException("Time format is invalid. Type help for correct formatting");
        }

        this.course_name = course_name;
        this.time = time;
        this.days = days;
    }

    public Course(int CRN) {
        int crn = (String) CRN;
        if(course_dict == null){
            ObjectMapper mapper = new ObjectMapper(); 
            course_dict = mapper.readValue( 
                new File("course_catlog.json"),
                new TypeReference<HashMap<String, HashMap<String, Object>>>() {}
            );
        }
        if(course_dict.get(crn) == null){
            throw new IllegalArgumentException("CRN is invalid. Type help for correct formatting");
        }
        HashMap<String, Object> specificCourse = course_dict.get(crn);
        this.course_name = ((String) specificCourse.get("subject")) + "_" + ((String) specificCourse.get("course_number"));


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
