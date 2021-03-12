package StudentGroupApp;

import java.util.ArrayList;

public class StudentGroup {
    //Student group member variables
    private String groupName;
    private String description;
    private Student owner;
    private ArrayList<Student> admins;
    private ArrayList<Student> members;
    //TODO: add array list of events

    //Student group initializer
    public StudentGroup(String groupName, String description, Student owner) {
        this.groupName = groupName;
        this.description = description;
        this.owner = owner;
        this.admins = new ArrayList<Student>();
        this.admins.add(owner);
        this.members = new ArrayList<Student>();
        this.members.add(owner);
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getDescription() {
        return this.description;
    }

    public Student getOwner(){
        return this.owner;
    }

    public ArrayList<Student> getAdmins(){
        return this.admins;
    }

    public ArrayList<Student> getMembers(){
        return this.members;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwner(Student owner) {
        this.owner = owner;
        if(!members.contains(owner)) {
            members.add(owner);
        }
        if(!admins.contains(owner)) {
            members.add(owner);
        }
    }
}
