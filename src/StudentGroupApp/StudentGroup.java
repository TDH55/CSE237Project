package StudentGroupApp;

import java.util.ArrayList;
import java.util.Objects;

public class StudentGroup {
    //Student group member variables
    private String groupName;
    private String description;
    private Student owner;
    private ArrayList<Student> admins;
    private ArrayList<Student> members;
    private ArrayList<Student> invitedStudents;
    private ArrayList<Student> blacklistStudents;
    private boolean isPrivate;
    private Tag tag;
    //TODO: add array list of events

    //public student group initializer
    public StudentGroup(String groupName, String description, Student owner, Tag tag) {
        this.groupName = groupName;
        this.description = description;
        this.owner = owner;
        this.admins = new ArrayList<Student>();
        this.admins.add(owner);
        this.members = new ArrayList<Student>();
        this.members.add(owner);
        this.isPrivate = false;
        this.invitedStudents = null;
        this.blacklistStudents = new ArrayList<Student>();
        this.tag = Objects.requireNonNullElse(tag, Tag.None);
    }

    //private/public student group initializer
    public StudentGroup(String groupName, String description, Student owner, boolean isPrivate, Tag tag) {
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
        this.blacklistStudents = new ArrayList<Student>();
        this.tag = Objects.requireNonNullElse(tag, Tag.None);
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
    
    public ArrayList<Student> getDisallowedMembers(){
    	return this.blacklistStudents;
    }

    public Tag getTag() {
        return this.tag;
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

    public void setTag(Tag tag) {
        this.tag = tag;
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
    
    public void disallowMember(Student newMember) {
    	if (!blacklistStudents.contains(newMember)) {
    		if(!isPrivate) {
    			this.blacklistStudents.add(newMember);
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
    
    public void disallowStudent(Student student) {
    	if (!blacklistStudents.contains(student)) {
    		blacklistStudents.add(student);
    	}
    }

    public void kickStudent(Student student) {
        members.remove(student);
    }
    
    public boolean isInvitedStudent(Student student) {
    	return invitedStudents.contains(student);
    }
    
    public boolean isDisallowedStudent(Student student) {
    	return blacklistStudents.contains(student);
    }
    
    public boolean isAdmin(Student student) {
    	return admins.contains(student);
    }
}
