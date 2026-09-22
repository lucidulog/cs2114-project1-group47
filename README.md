# Welcome to the repository of HokieConnect!
HokieConnect is an application that allows you and your friends to put in your courses and course schedule to be grouped with other students for study sessions for courses you are taking! 

## Authors
Hannah Dai, Luci Dulog, Zongqi Nie, and Aneesh Pabolu


## An Important Note:

This README is divided into **two parts**:

1. **Compiling & Running the Actual Program**
2. **Compiling & Running the Test Cases**

All compiled `.class` files are placed in the `bin/` directory.

When running the program or tests, the `bin/` directory is included in the classpath so that Java can locate all of the compiled classes.

To run and test and run our program, use the following commands in the root directory of our repository.

---

# Part 1 — Compiling & Running the Actual Program

## 1. Compile the Program

Run the following command to compile all of our Classes:

```bash
javac -d bin Main.java Course.java Student.java Group.java CourseCatalog.java
```

## 2. Run our program!

Run the following command to run our program!

```bash
java -cp bin Main
```

## 3. Using the Program

When the program starts, type `help` to display the available commands.

### Adding a Student

To add a student, enter:

```text
add student
```

The program will ask you to enter the student's name.

For example:

```text
Enter student name: John Smith
```

### Adding Courses

To add courses to the most recently added student, enter:

```text
add course
```

There are two ways to enter a course.

#### Option 1: Enter a CRN

Enter `CRN` followed by the five-digit CRN.

For example:

```text
CRN83531
```

The program will retrieve the course information from the course catalog.

#### Option 2: Enter Course Information Manually

Enter the course information using the following format:

```text
COURSE_NAME DAYS TIME
```

For example:

```text
CS_2114 TR 3:30PM-4:20PM
```

The available day codes are:

| Code | Day       |
| ---- | --------- |
| M    | Monday    |
| T    | Tuesday   |
| W    | Wednesday |
| R    | Thursday  |
| F    | Friday    |
| S    | Saturday  |
| U    | Sunday    |

You can enter multiple courses for the current student.

When you are finished adding courses, enter:

```text
done
```

### Viewing a Student's Schedule

To view a student's schedule, enter:

```text
view schedule
```

The program will ask for the student's name and then display their courses.

You can also use:

```text
show schedule
```

as an alternative command.

### Viewing All Students

To display all students currently in the program, enter:

```text
students
```

### Creating Study Groups

After adding students and their courses, enter:

```text
make group
```

The program will compare students' schedules and identify students who share the same course, meeting days, and meeting time.

When two or more students share the same course schedule, the program will create a study group and suggest a common meeting time and location while attempting to avoid conflicts with the students' course schedules.

### Getting Help

At any time, enter:

```text
help
```

to display the available commands.

### Exiting the Program

To exit the program, enter either:

```text
exit
```

or:

```text
quit
```


# Part 2 - Running JUnit testing on our program

This project uses **JUnit 5** for testing. JUnit 5 has already been imported into this project and no extra installation of JUnit 5 is necessary.
> **Note:** There is no separate `CourseCatalogTest` class because the relevant
> `CourseCatalog` functionality is tested through `CourseTest`. When a CRN is
> passed to the `Course` constructor, `Course` accesses the `CourseCatalog` to
> retrieve the course information. Therefore, `CourseTest` also tests the
> `CourseCatalog` functionality needed by `Course`.

## 1. Compile all the Test Classes

```bash
javac -d bin -cp "bin:lib/*" StudentTest.java CourseTest.java MainTest.java GroupTest.java
```

## 2. Run the JUnit Tests:

```bash
java -jar lib/junit-platform-console-standalone-*.jar execute --class-path bin --scan-class-path
```

