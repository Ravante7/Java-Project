package Modelclasses;

import java.util.ArrayList;

public class Course
{
    private String code;
    private String nameCourse;
    private Teacher teacher;
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<AttendanceSession> sessions = new ArrayList<AttendanceSession>();

    public Course(String code , String nameCourse)
    {
        this.code=code;
        this.nameCourse=nameCourse;
    }

    public String getCode() {
        return code;
    }
    public String getNameCourse() {return nameCourse;}
    public ArrayList<AttendanceSession> getSessions() {
        return sessions;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    public ArrayList<Student> getStudents() {
        return students;
    }

public ArrayList<AttendanceSession> getSession() {return sessions;}

    public void enrollStudent(Student s)
    {
        if (students.contains(s)) {
           throw new InvalidDataException(s.getName() + "is already enrolled the course "+ code + ".");
        }
        students.add(s);
    }
    public void removeStudent(Student s)
    {

        if (!students.contains(s)) {
            throw new InvalidDataException(
                    s.getName() + " is not enrolled in the course " + code + ".");
        }
        students.remove(s);

    }

    public boolean isEnrolled(Student s)
    {
        if (!students.contains(s))
            return false;
            else
                return true;
    }

    public Student findStudent(int attendanceID)
    {
        for ( Student i:students )
        {
            if ( i.getStudentAttendanceID() == attendanceID)
                return i;
        }
        return null;
    }

    public void addSession(AttendanceSession session ){sessions.add(session);}//ورقه الحضور

    public String toString() {
        return code + " - " + nameCourse;
    }

}
