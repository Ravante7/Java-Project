package Modelclasses;
import data.DataStore;

public class Main {

    public static void main(String[] args) {
        DataStore.load();
        long t = System.currentTimeMillis();

        try {
//login admin
            Person p = DataStore.login("admin@ams.com", "admin123");
            System.out.println("Admin login: " + (p instanceof Admin));
            System.out.println("Wrong password: " + DataStore.login("admin@ams.com", "wrong"));   // null
            Admin admin = (Admin) p;

            //  Admin creates teacher, student, course
            Teacher teacher = admin.createTeacher("Dr Test", "dr" + t + "@ams.com", "1234");
            System.out.println("Teacher: " + teacher.getName() + " (ID " + teacher.getId() + ")");
            System.out.println("Teacher login: " + (DataStore.login("dr" + t + "@ams.com", "1234") instanceof Teacher));

            Student ali = admin.createStudent("Ali Hassan", "ali" + t + "@ams.com");
            int id = ali.getStudentAttendanceID();
            System.out.println("Student: " + ali.getName() + " (ID " + id + ")");

            Course course = admin.createCourse("T" + (t % 100000), "Test Course", teacher);
            System.out.println("Course: " + course.getCode() + " teacher = " + course.getTeacher().getName());
            System.out.println("Teacher courses: " + teacher.getCourses().size());   // must be 1

            // Enroll
            admin.enrollStudent(course, ali);
            System.out.println("Ali enrolled: " + course.isEnrolled(ali));

            //  Start a Lab session
            AttendanceSession session = teacher.startSession(course, "Lab", "Swing");
            System.out.println("Session: " + session.getType() + " - " + session.getDetails());

            // Mark attendance
            teacher.markAttendance(session, id);
            System.out.println("Ali present: " + session.isPresent(ali));

            //Errors (each one must print "Expected error")
            try {
                teacher.markAttendance(session, id);
            } catch (InvalidDataException e) {
                System.out.println("Expected error 1: " + e.getMessage());
            }
            try {
                teacher.markAttendance(session, 1);
            } catch (InvalidDataException e) {
                System.out.println("Expected error 2: " + e.getMessage());
            }
            try {
                admin.createStudent("", "x@y.com");
            } catch (InvalidDataException e) {
                System.out.println("Expected error 3: " + e.getMessage());
            }
            try {
                admin.createStudent("Test", "admin@ams.com");
            } catch (InvalidDataException e) {
                System.out.println("Expected error 4: " + e.getMessage());
            }

            //  End the session
            teacher.endSession(session);
            System.out.println("Closed: " + session.isClosed());
            System.out.println("Present: " + session.countPresent() + "  Absent: " + session.countAbsent());

            try {
                session.markPresent(ali);
            } catch (InvalidDataException e) {
                System.out.println("Expected error 5: " + e.getMessage());
            }

            //  Reports
            System.out.println();
            System.out.println(AttendanceReport.sessionReport(session));
            System.out.println(AttendanceReport.courseReport(course));
            System.out.println("Ali rate: " + AttendanceReport.attendanceRate(course, ali) + "%");

            //  Save and reload
            DataStore.save();
            DataStore.load();
            Student again = DataStore.findStudent(id);
            System.out.println("Student after reload: " + (again == null ? "NOT FOUND" : again.getName()));

        } catch (InvalidDataException e) {
            System.out.println("UNEXPECTED ERROR: " + e.getMessage());
        }
    }
}