package Modelclasses;

public class Student extends Person implements  Comparable<Student>
{
    private int studentAttendanceID;

    public Student( int studentAttendanceID,String name,String email ) {
        super(name, email);
        this.studentAttendanceID = studentAttendanceID;
    }

    public int getStudentAttendanceID() //عشان في داتا هنعمل set ليها بشكل عشوائي
    {
        return studentAttendanceID;
    }

    @Override
    public int getId() {
        return studentAttendanceID;
    }

    @Override
    public String getRole() {
return "Student";
    }

    @Override
    public int compareTo(Student other)//بيقارن بالاسم
    {
        return this.getName().compareToIgnoreCase(other.getName());
    }
}
