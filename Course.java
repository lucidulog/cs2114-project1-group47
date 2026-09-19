import Course_Catalog_Folder.CourseCatalog;


/**
* Represents a single course offering: name, CRN, and meeting time.
* Objects are immutable once created; invalid input is rejected in the
* constructor so a Course can never exist in a bad state.
*/
public class Course {
   private String course_name; //In the format of "CS_2114"
   private String time; //In the format of "9:00AM-11:00AM"
   private String days; //In the format of "TR" or "MWF"
   private CourseCatalog course_catalog;


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


}