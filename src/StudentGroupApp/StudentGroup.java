package StudentGroupApp;

import java.util.ArrayList;

public class StudentGroup {
    //Student group member variables
    private String groupName;
    private String description;
    private Student owner;
    private ArrayList<Student> admins;
    private ArrayList<Student> members;
    private ArrayList<Student> invitedStudents;
    private boolean isPrivate;
    //TODO: add array list of events

    //public student group initializer
    public StudentGroup(String groupName, String description, Student owner) {
        this.groupName = groupName;
        this.description = description;
        this.owner = owner;
        this.admins = new ArrayList<Student>();
        this.admins.add(owner);
        this.members = new ArrayList<Student>();
        this.members.add(owner);
        this.isPrivate = false;
        this.invitedStudents = null;
    }

    //private/public student group initializer
    public StudentGroup(String groupName, String description, Student owner, boolean isPrivate) {
        this.groupName = groupName;
        this.description = description;
        this.owner = owner;
        this.admins = new ArrayList<Student>();
        this.admins.add(owner);
        this.members = new ArrayList<Student>();
        this.members.add(owner);
        this.isPrivate = isPrivate;
        if (isPrivate) {
            this.invitedStudents = new ArrayList<Student>();
            this.invitedStudents.add(owner);
        } else {
            this.invitedStudents = null;
        }
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
    
    public boolean getIsPrivate() {
    	return this.isPrivate;
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
    
    public void addMember(Student newMember) {
        if(!members.contains(newMember)){
            if(!isPrivate){
                this.members.add(newMember);
            } else {
                if(invitedStudents.contains(newMember)) {
                    this.members.add(newMember);
                }
            }
        }
    }
    
    public void removeMember(Student memberToRemove) {
    	if(!this.members.contains(memberToRemove)) {
    		return;
    	} else {
    		this.members.remove(memberToRemove);
    	}
    }

    public void addAdmin(Student student) {
        if(!admins.contains(student)){
            admins.add(student);
        }
    }

    public void inviteStudent(Student student) {
        if(!invitedStudents.contains(student)) {
            invitedStudents.add(student);
        }
    }

    public void kickStudent(Student student) {
        members.remove(student);
    }
}
