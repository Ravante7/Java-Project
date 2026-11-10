package Modelclasses;

public class LectureSession extends AttendanceSession
{
    private String topic;

    public LectureSession(Course course, String topic) {
        super(course);
        this.topic = topic;
    }
    public LectureSession(int id, Course course, String date, boolean closed, String topic) {
        super(id, course, date, closed);
        this.topic = topic;
    }

    @Override
    public String getType() {
        return "Session";
    }

    @Override
    public String getDetails() {
        return "Topic of lecture : "+ topic;
    }

    public String getTopic() {
        return topic;
    }
}
