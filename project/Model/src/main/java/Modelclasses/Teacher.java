package Modelclasses;

import data.DataStore;

import java.util.ArrayList;

public class Teacher extends Person implements Authenticatable
{
    private int employeeId;
    private String password;

    public Teacher(int employeeId, String name, String email, String password) {
        super(name, email);
        this.employeeId = employeeId;
        this.password = password;
    }

    @Override
    public int getId() {
        return employeeId;
    }

    @Override
    public String getRole() {
        return "Teacher";
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean login(String email, String password) {
        return getEmail().equalsIgnoreCase(email) && getPassword().equalsIgnoreCase(password);
    }

    public ArrayList<Course> getCourses()
    {
return DataStore.getCoursesOf(this);
    }
    public AttendanceSession startSession(Course course , String type ,String details) throws InvalidDataException
    {
        if (course.getTeacher() != this)
        {
        throw new InvalidDataException("You are not the teacher of this course. ");
        }
        AttendanceSession session;
        if (type.equals("Lab"))
        {
            session =new LabSession(course,details);
        } else if (type.equals("Section"))
        {
            int group;
            try {
                group = Integer.parseInt(details.trim());
            } catch (NumberFormatException e) {
                throw new InvalidDataException("Group number must be a number.");
            }
            session =new SectionSession(course,group);
        }
        else
        {
            session=new LectureSession(course,details);
        }
        course.addSession(session);
return session;
    }

    public Student markAttendance(AttendanceSession session , int attendanceId) throws InvalidDataException
    {
        Student s= session.getCourse().findStudent(attendanceId);
        if (s==null)
        {
            if(DataStore.findStudent(attendanceId)==null)//بيشوف لو id مش موجود
            {
            throw new InvalidDataException("No student has the ID " + attendanceId + ".");
            }//هنا الطالب مش موجود في كورس ده
            throw new InvalidDataException("Student " + attendanceId + " is not enrolled in this course.");
        }
        session.markPresent(s);
return s;
    }
    public void endSession(AttendanceSession session)
    {
        session.close();//هنا بيقفل السيشن لو طالب موجود هيبقا حاضر لو مش موجود هيبقا غايب
        DataStore.save();
    }
}
