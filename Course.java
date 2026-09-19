import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileNotFoundException;


/**
* Represents a single course offering: name, CRN, and meeting time.
* Objects are immutable once created; invalid input is rejected in the
* constructor so a Course can never exist in a bad state.
*/
public class Course {
   private String course_name; //In the format of "CS_2114"
   private String time; //In the format of "9:00AM-11:00AM"
   private String days; //In the format of "TR" or "MWF"
   private static HashMap<String, HashMap<String, Object>> course_dict;


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


   // public Course(int CRN) {
   //     String crn = String.valueOf(CRN); //Converts CRN to int
   //     if(course_dict == null){
   //         course_dict = loadCourseCatalog("course_catalog.json");
   //     }
   //     if(course_dict.get(crn) == null){
   //         throw new IllegalArgumentException("CRN is invalid. Type help for correct formatting");
   //     }
   //     HashMap<String, Object> specificCourse = course_dict.get(crn);


   //     this.course_name =
   //         ((String) specificCourse.get("subject"))
   //         + "_"
   //         + ((String) specificCourse.get("course_number"));


   //     HashMap<String, Object> lecture =
   //         (HashMap<String, Object>) ((java.util.List<?>) specificCourse.get("meetings")).get(0);


   //     HashMap<String, Object> raw =
   //         (HashMap<String, Object>) lecture.get("raw");


   //     this.days = ((String) raw.get("days")).replace(" ", "");


   //     this.time =
   //         (String) raw.get("begin")
   //         + "-"
   //         + (String) raw.get("end");
   // }


   public static void main(String[] args){
       Course CS_2114 = new Course(83531);
       System.out.println(CS_2114);
   }


   /**
    * This method is intended to read the course_catalog.json and return a
    * HashMap that matches the structure of the Course constructor
    *
    * @param filename This is the file that contains the course catalog
    * @return courseDict a HashMap dictionary with only the relevant information from the course catalog.
    */
   // private static HashMap<String, HashMap<String, Object>> loadCourseCatalog(String filename) {
   //     HashMap<String, HashMap<String, Object>> courseDict = new HashMap<>();
   //     try {
   //         Scanner scanner = new Scanner(new File(filename));
   //         scanner.close();
   //     } catch (FileNotFoundException e) {
   //         throw new RuntimeException("Could not find file: " + filename, e);
   //     }


   //     String currentCRN = null;
   //     HashMap<String, Object> currentCourse = null;


   //     String subject = null;
   //     String courseNumber = null;
   //     String days = null;
   //     String begin = null;
   //     String end = null;


   //     boolean insideMeetings = false;
   //     boolean foundFirstMeeting = false;


   //     while (scanner.hasNextLine()) {


   //         String line = scanner.nextLine().trim();


   //         // Find a CRN at the beginning of a course object
   //         if (line.matches("\"\\d+\": \\{")) {


   //             currentCRN = line.substring(1, line.indexOf('"', 1));
   //             currentCourse = new HashMap<>();


   //             subject = null;
   //             courseNumber = null;
   //             days = null;
   //             begin = null;
   //             end = null;


   //             insideMeetings = false;
   //             foundFirstMeeting = false;


   //             continue;
   //         }


   //         if (currentCourse == null) {
   //             continue;
   //         }


   //         // Get subject
   //         if (line.startsWith("\"subject\"")) {
   //             subject = line.substring(
   //                     line.indexOf("\"", line.indexOf(":") + 1) + 1,
   //                     line.lastIndexOf("\"")
   //             );
   //         }


   //         // Get course number
   //         else if (line.startsWith("\"course_number\"")) {
   //             courseNumber = line.substring(
   //                     line.indexOf("\"", line.indexOf(":") + 1) + 1,
   //                     line.lastIndexOf("\"")
   //             );
   //         }


   //         // We only want the first meeting
   //         else if (line.equals("\"meetings\": [")) {
   //             insideMeetings = true;
   //         }


   //         else if (insideMeetings && !foundFirstMeeting
   //                 && line.startsWith("\"days\": [")) {
   //             // The actual useful days are in raw, so don't do anything here.
   //         }


   //         // Get the values from the first meeting's raw object
   //         else if (insideMeetings && !foundFirstMeeting
   //                 && line.startsWith("\"begin\"")) {


   //             begin = line.substring(
   //                     line.indexOf("\"", line.indexOf(":") + 1) + 1,
   //                     line.lastIndexOf("\"")
   //             );
   //         }


   //         else if (insideMeetings && !foundFirstMeeting
   //                 && line.startsWith("\"end\"")) {


   //             end = line.substring(
   //                     line.indexOf("\"", line.indexOf(":") + 1) + 1,
   //                     line.lastIndexOf("\"")
   //             );
   //         }


   //         else if (insideMeetings && !foundFirstMeeting
   //                 && line.startsWith("\"days\": \"")) {


   //             days = line.substring(
   //                     line.indexOf("\"", line.indexOf(":") + 1) + 1,
   //                     line.lastIndexOf("\"")
   //             );


   //             days = days.replace(" ", "");
   //             foundFirstMeeting = true;
   //         }


   //         // Once we've gotten the first meeting information,
   //         // create the structure expected by Course.
   //         if (currentCRN != null
   //                 && subject != null
   //                 && courseNumber != null
   //                 && days != null
   //                 && begin != null
   //                 && end != null) {


   //             HashMap<String, Object> raw = new HashMap<>();
   //             raw.put("days", days);
   //             raw.put("begin", begin);
   //             raw.put("end", end);


   //             HashMap<String, Object> meeting = new HashMap<>();
   //             meeting.put("raw", raw);


   //             ArrayList<HashMap<String, Object>> meetings =
   //                     new ArrayList<>();
   //             meetings.add(meeting);


   //             currentCourse.put("subject", subject);
   //             currentCourse.put("course_number", courseNumber);
   //             currentCourse.put("meetings", meetings);


   //             courseDict.put(currentCRN, currentCourse);


   //             // Prevent adding this course again
   //             currentCRN = null;
   //         }
   //     }   
   //     return courseDict;
   // }
   // private static HashMap<String, HashMap<String, Object>> loadCourseCatalog(
   //     String filename) {


   //     HashMap<String, HashMap<String, Object>> courseDict = new HashMap<>();


   //     try {
   //         Scanner scanner = new Scanner(new File(filename));


   //         String currentCRN = null;
   //         HashMap<String, Object> currentCourse = null;


   //         String subject = null;
   //         String courseNumber = null;
   //         String days = null;
   //         String begin = null;
   //         String end = null;


   //         boolean insideMeetings = false;
   //         boolean foundFirstMeeting = false;


   //         while (scanner.hasNextLine()) {


   //             String line = scanner.nextLine().trim();


   //             // Find a CRN at the beginning of a course object
   //             if (line.matches("\"\\d+\": \\{")) {


   //                 currentCRN = line.substring(1, line.indexOf('"', 1));
   //                 currentCourse = new HashMap<>();


   //                 subject = null;
   //                 courseNumber = null;
   //                 days = null;
   //                 begin = null;
   //                 end = null;


   //                 insideMeetings = false;
   //                 foundFirstMeeting = false;


   //                 continue;
   //             }


   //             if (currentCourse == null) {
   //                 continue;
   //             }


   //             if (line.startsWith("\"subject\"")) {
   //                 subject = line.substring(
   //                         line.indexOf("\"", line.indexOf(":") + 1) + 1,
   //                         line.lastIndexOf("\"")
   //                 );
   //             }


   //             else if (line.startsWith("\"course_number\"")) {
   //                 courseNumber = line.substring(
   //                         line.indexOf("\"", line.indexOf(":") + 1) + 1,
   //                         line.lastIndexOf("\"")
   //                 );
   //             }


   //             else if (line.equals("\"meetings\": [")) {
   //                 insideMeetings = true;
   //             }


   //             else if (insideMeetings && !foundFirstMeeting
   //                     && line.startsWith("\"begin\"")) {


   //                 begin = line.substring(
   //                         line.indexOf("\"", line.indexOf(":") + 1) + 1,
   //                         line.lastIndexOf("\"")
   //                 );
   //             }


   //             else if (insideMeetings && !foundFirstMeeting
   //                     && line.startsWith("\"end\"")) {


   //                 end = line.substring(
   //                         line.indexOf("\"", line.indexOf(":") + 1) + 1,
   //                         line.lastIndexOf("\"")
   //                 );
   //             }


   //             else if (insideMeetings && !foundFirstMeeting
   //                     && line.startsWith("\"days\": \"")) {


   //                 days = line.substring(
   //                         line.indexOf("\"", line.indexOf(":") + 1) + 1,
   //                         line.lastIndexOf("\"")
   //                 );


   //                 days = days.replace(" ", "");
   //                 foundFirstMeeting = true;
   //             }


   //             if (currentCRN != null
   //                     && subject != null
   //                     && courseNumber != null
   //                     && days != null
   //                     && begin != null
   //                     && end != null) {


   //                 HashMap<String, Object> raw = new HashMap<>();
   //                 raw.put("days", days);
   //                 raw.put("begin", begin);
   //                 raw.put("end", end);


   //                 HashMap<String, Object> meeting = new HashMap<>();
   //                 meeting.put("raw", raw);


   //                 ArrayList<HashMap<String, Object>> meetings =
   //                         new ArrayList<>();


   //                 meetings.add(meeting);


   //                 currentCourse.put("subject", subject);
   //                 currentCourse.put("course_number", courseNumber);
   //                 currentCourse.put("meetings", meetings);


   //                 courseDict.put(currentCRN, currentCourse);


   //                 currentCRN = null;
   //             }
   //         }


   //         scanner.close();


   //     } catch (FileNotFoundException e) {
   //         throw new RuntimeException(
   //                 "Could not find file: " + filename, e);
   //     }


   //     return courseDict;
   // }
  


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