package StudentGroupApp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Event {
    private String title;
    private String description;
    private Date date;
    private ArrayList<Student> rsvpedStudents;

    public Event(String title, String description, Calendar date, Student owner) {
        this.title = title;
        this.description = description;
        this.date = date.getTime();
        this.rsvpedStudents = new ArrayList<Student>();
        this.rsvpedStudents.add(owner);
    }
    
    public void addStudent(Student student) {
        this.rsvpedStudents.add(student);
    }

    public void removeStudent(Student student) {
        this.rsvpedStudents.remove(student);
    }
    
//Student event getters: 
    public String getTitle() {
        return title;
    }

    public String getDescription() { return description; }

    public Date getDate() {
        return date;
    }

    public ArrayList<Student> getRsvpedStudents() {
        return rsvpedStudents;
    }
    
//Student event setters: 
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) { this.description = description; }

    public void setDate(Calendar date) {
        this.date = date.getTime();
    }

}
