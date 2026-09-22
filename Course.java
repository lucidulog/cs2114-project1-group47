import Course_Catalog_Folder.CourseCatalog;


/**
* Represents a single course offering: name, CRN, and meeting time.
* Objects are immutable once created; invalid input is rejected in the
* constructor so a Course can never exist in a bad state.
*/
public class Course {
    private String course_name; // In the format of "CS_2114"
    private String time; // In the format of "9:00AM-11:00AM"
    private String days; // In the format of "TR" or "MWF"
    private CourseCatalog course_catalog;

    private int startTime;    // minutes since midnight, e.g. 9:00AM -> 540
    private int endTime;      // minutes since midnight, e.g. 11:00AM -> 660


    public Course(String course_name, String time, String days) {
        if (course_name == null || course_name.isBlank()) {
            throw new IllegalArgumentException("Course Name format is invalid. Type help for correct formatting");
        }
        if (days == null || days.isBlank()) {
            throw new IllegalArgumentException("Days format is invalid. Type help for correct formatting");
        }
        if (time == null) {
            throw new IllegalArgumentException("Time format is invalid. Type help for correct formatting");
        }

        this.course_name = course_name;
        this.time = time;
        this.days = days;
        parseTime(time);
    }

    public Course(String CRN){
        if(course_catalog == null){
        course_catalog = new CourseCatalog();
        }
        if(course_catalog.getCatalog().get(CRN) == null){
            throw new IllegalArgumentException("CRN Number format is invalid. Type help for correct formatting");
        }
    
        this.course_name = course_catalog.getCatalog().get(CRN).getSubject() + "_" + course_catalog.getCatalog().get(CRN).getCourseNumber();
        this.days = course_catalog.getCatalog().get(CRN).getMeetings().get(0).getRaw().getDays().replace(" ", "");
        this.time = course_catalog.getCatalog().get(CRN).getMeetings().get(0).getRaw().getBegin() + "-" + course_catalog.getCatalog().get(CRN).getMeetings().get(0).getRaw().getEnd();
        parseTime(this.time);
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
        String str = this.getCourseName() + " " + this.getDays() + " " + this.getTime();
        return str;
    }

    // Call this from the constructor, after validating time is non-null/non-blank
    private void parseTime(String time) {
        String[] parts = time.split("-", 2);
        this.startTime = parseClockToMinutes(parts[0].trim());

        if (parts.length > 1) {
            this.endTime = parseClockToMinutes(parts[1].trim());
        } else {
            throw new IllegalArgumentException("Time format is invalid. Type help for correct formatting");
        }
    }

    // Converts a clock time like "9:00AM" or "11:15PM" into minutes since midnight
    private int parseClockToMinutes(String clock) {
        java.util.regex.Matcher m = java.util.regex.Pattern
                .compile("(?i)(\\d{1,2}):(\\d{2})\\s*(AM|PM)").matcher(clock);
        if (!m.matches()) {
            throw new IllegalArgumentException("Time format is invalid. Type help for correct formatting");
        }
        int hour = Integer.parseInt(m.group(1)) % 12;
        int minute = Integer.parseInt(m.group(2));
        if (m.group(3).equalsIgnoreCase("PM")) {
            hour += 12;
        }
        return hour * 60 + minute;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }
}
