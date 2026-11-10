package Modelclasses;

import data.DataStore;
import data.IdGenerator;

public class Admin extends Person implements Authenticatable
{
private int adiminId;
private String password;

    public Admin(int adiminId, String name,String email , String password)
    {
        super(name, email);
        this.adiminId = adiminId;
        this.password = password;
    }

    public String getPassword() {return password;}

    @Override
    public int getId() {
        return adiminId;
    }

    @Override
    public String getRole() {
        return "Admin";
    }


    @Override
    public boolean login(String email, String password) {
        if (getEmail().equalsIgnoreCase(email) && getPassword().equalsIgnoreCase(password))
        {
            return true;
        }
        else
            return false;
    }

    public Student createStudent(String name , String email) throws InvalidDataException
    {
        checkNameAndEmail(name, email);
        Student s=new Student(IdGenerator.newStudentId(),name.trim(),email.trim());
         DataStore.addStudent(s);
         return s;
    }

    public void removeStudent(Student s)
    {
        DataStore.removeStudent(s);
    }

public Teacher createTeacher(String name , String email, String password) throws InvalidDataException
{
    if (password == null || password.trim().length() < 4) {
        throw new InvalidDataException("Password must be at least 4 characters.");
    }
    checkNameAndEmail(name, email);
    Teacher t=new Teacher(IdGenerator.newEmployeeId(), name.trim(), email.trim(), password.trim());
    DataStore.addTeacher(t);
    return t;
}
public void removeTeacher(Teacher t)
    {
        DataStore.removeTeacher(t);
    }
public Course createCourse(String code, String nameCode , Teacher t) throws InvalidDataException
{
if (code == null || code.trim().isEmpty() || nameCode==null || nameCode.trim().isEmpty() )
{
    throw new InvalidDataException("Course code and name are required.");
}
checkText(code);
checkText(nameCode);
    if (DataStore.findCourse(code.trim()) != null) {
        throw new InvalidDataException("Course code " + code + " already exists.");
    }
    Course c=new Course(code.trim().toUpperCase(),nameCode.trim());
     c.setTeacher(t);
   DataStore.addCourse(c);
    return c;
}

    public void removeCourse(Course c) {
        DataStore.removeCourse(c);
    }

    public void assignTeacher(Course c, Teacher t) {
        c.setTeacher(t);
        DataStore.save();
    }

    public void enrollStudent(Course c , Student s) throws InvalidDataException
    {
    c.enrollStudent(s);
    DataStore.save();
    }

    public void unenrollStudent(Course c, Student s)
    {
        c.removeStudent(s);
        DataStore.save();
    }



    private void checkNameAndEmail(String name, String email) throws InvalidDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Name is required.");
        }
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new InvalidDataException("Please enter a valid email.");
        }
        checkText(name);
        checkText(email);
        if (DataStore.emailExists(email.trim())) {
            throw new InvalidDataException("The email " + email + " is already used.");
        }
    }

    public void checkText(String name) throws InvalidDataException
    {
     if (name.contains(";") || name.contains(","))
     {
         throw new InvalidDataException("The characters ; and , are not allowed.");
     }
    }


}
