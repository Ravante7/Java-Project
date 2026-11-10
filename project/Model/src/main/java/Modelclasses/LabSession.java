package Modelclasses;

public class LabSession extends AttendanceSession
{
  private String experiment;

    public LabSession(Course course, String experiment) {
        super(course);
        this.experiment = experiment;
    }
    public LabSession(int id, Course course,String experiment , String date,boolean closed ) {
        super(id, course, date, closed);
        this.experiment = experiment;
    }
    @Override
    public String getType() {
        return "Lab";
    }

    @Override
    public String getDetails() {
        return "Experiment : "+ experiment;
    }

    public String getExperiment() {
        return experiment;
    }
}
