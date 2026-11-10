package Modelclasses;

public abstract class Person
{
    private String name;
    private String email;

    public Person(String name, String email) {
        this.email = email;
        this.name = name;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public abstract int getId();
    public abstract String getRole();
@Override
    public String toString() {
        return "{" +getRole()+
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
