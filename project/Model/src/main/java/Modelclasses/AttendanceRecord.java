package Modelclasses;

public class AttendanceRecord
{
    private Student student;
    private boolean present;

    public AttendanceRecord(boolean present, Student student)
    {
        this.present = present;
        this.student = student;
    }

    public boolean isPresent() {return present;}

    public void setPresent(boolean present) {this.present = present;}

    public Student getStudent() {return student;}

    public void setStudent(Student student) {this.student = student;}

    @Override
    public String toString() {
        return "Student is name : " + student.getName() + " " + " ID : "+ student.getId() + (present ? "Present" : "Absent") ;}
}
