package cpe200;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {

    public Student() {
        this("John Doe", "560610000", 1990, false);
    }
    public Student(String n, String id) {
        this(n, id, 1995, true);
    }
    public Student(String n, String id, int yob) {
        this(n, id, yob, true);
    }
    public Student(String n, String id, int yob, boolean ia) {
        this.name = !n.equalsIgnoreCase("") ? n : "John Doe";
        this.student_id = isValidStudent_id(id) ? id : "560610000";
        this.year_of_birth = isValidYOB(yob) ? yob : 1990;
        this.isActive = ia;
        this.courses = new PList();
    }

    public boolean addCourse(Course c) {
        if (c.enrollStudent(this)) { // enroll the course with "this" student object
            courses.pushToTail(c);
            return true;
        } else
            return false; // if unable to enroll a student
    }

    public boolean dropCourse(Course c) {
        if (courses.found(c)) {
            courses.remove(c);
            c.removeStudent(this);
            System.out.println(student_id + " has been removed from " + c.getCourse_id() + " successfully.");
            return true;
        } else {
            System.out.println(student_id + " is NOT enrolled in " + c.getCourse_id() + ".");
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = !name.equalsIgnoreCase("") ? name : this.name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = isValidStudent_id(student_id) ? student_id : this.student_id;
    }

    public int getYearOfBirth() {
        return year_of_birth;
    }

    public void setYearOfBirth(int yob) {
        this.year_of_birth = isValidYOB(yob) ? yob : this.year_of_birth;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        String o = this.name + " (" + this.student_id + ") was born in " + this.year_of_birth + " ";

        if (isActive)
            o = o + "is an ACTIVE student.";
        else
            o = o + "is an INACTIVE student.";

        for (int i = 0; i < courses.getSize(); i++) {
            Course c = (Course) courses.elementAt(i);

            o += "\n\t" + c.getCourse_id() + " - " + c.getCourse_name();
        }

        return o;
    }

    private boolean isValidStudent_id(String id) {
        Pattern p = Pattern.compile(idREGEX);
        Matcher m = p.matcher(id);

        return m.matches();
    }

    private boolean isValidYOB(int yob) {
        return yob > 1989;
    }

    private static final String idREGEX = "5[6789]061[012]\\d{3}";

    private String name;
    private String student_id;
    private int year_of_birth;
    private boolean isActive;
    private PList courses;
}