package Modelclasses;

public class SectionSession extends AttendanceSession
{
    private int groupNumber;

    public SectionSession(Course course, int groupNumber) {
        super(course);
        this.groupNumber = groupNumber;
    }
    public SectionSession(int id, Course course, String date, boolean closed, int groupNumber) {
        super(id, course, date, closed);
        this.groupNumber = groupNumber;
    }//ده عشان ملفات بتاعت داتا

    @Override
    public String getType() {
        return "Section";
    }

    @Override
    public String getDetails() {
        return "Group of Section : "+groupNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }
}
