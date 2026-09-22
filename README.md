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

# NEED TO ADD INSTRUCTIONS OF HOW TO USE OUR PROGRAM HERE

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

