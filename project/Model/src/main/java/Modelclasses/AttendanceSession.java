package Modelclasses;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class AttendanceSession
{
    private static int nextID =0;
    private int sessionID;
    private Course course;
    private String date;
    private boolean closed;
    private ArrayList<AttendanceRecord> records = new ArrayList<AttendanceRecord>();

    public AttendanceSession( Course course)
    {
        this.closed = false;
        this.course = course;
        this.date =LocalDate.now().toString();
        this.sessionID = nextID;
        nextID++;
    }
    public AttendanceSession(int sessionId, Course course, String date, boolean closed) {
        this.sessionID = sessionId;
        this.course = course;
        this.date = date;
        this.closed = closed;
        if (sessionId >= nextID) {
            nextID = sessionId + 1;
        }
    }

    public abstract String getType();
    public abstract String getDetails();

    public int getSessionID() {
        return sessionID;
    }

    public String getDate() {
        return date;
    }

    public Course getCourse() {
        return course;
    }

    public boolean isClosed()
    {
        return closed;
    }

    public ArrayList<AttendanceRecord> getRecords() {
        return records;
    }

    public void addRecord(AttendanceRecord r) {
        records.add(r);
    }

    public void markPresent(Student s) throws InvalidDataException
    {
        if (isClosed())
        {
            throw new InvalidDataException("session is Closed");
        }
        if (isMarked(s))
        {
            throw new InvalidDataException(s.getName() + " is already marked present.");
        }
        records.add(new AttendanceRecord(true ,s));
    }
    public boolean isMarked(Student s)//بتمشي علي كل student يشوفه حاضر ولا لا
    {
        for (AttendanceRecord r: records)
        {
        if (r.getStudent()==s)
            return true;
        }

            return false;
    }
    public boolean isPresent(Student s)//  بتمشي علي كل student يشوفه حاضر ولا لا بترجع الحاضر
    {
        for (AttendanceRecord r : records) {
            if (r.getStudent() == s)
                return r.isPresent();
        }
        return false;
    }

    public void close()
    {
        if (closed)
        {return;}
for (Student s : course.getStudents())
{
 if (!isMarked(s))
 {
     records.add(new AttendanceRecord(false,s));

 }

}
closed=true;
    }

    public int countPresent()
    {
        int count=0;
        for (AttendanceRecord r: records)
        {
          if (r.isPresent())
              count++;
        }
        return count;
    }
    public int countAbsent()
    {
        int count=0;
        for (AttendanceRecord r: records)
        {
            if (!r.isPresent())
                count++;
        }
        return count;
    }

    @Override
    public String toString() {
        return "#" + sessionID + "  " + getType() + "  " + date + "  (" + getDetails() + ")";
    }
}
