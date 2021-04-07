package Tests;

import static org.junit.jupiter.api.Assertions.*;

import StudentGroupApp.Student;
import StudentGroupApp.StudentGroup;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

class StudentGroupTests {
	Student owner = new Student("Taylor", "email@wustl.edu", 2022, "password123");
	Student firstGroupMember = new Student("John", "johndoe@wustl.edu", 2023, "password123");
	Student secondGroupMember = new Student("Jane", "janedoe@wustl.edu", 2021, "password123");

	@Test
	void testGetGroupName() {
		String groupName = "CSE 237 Final Project";
		StudentGroup group = new StudentGroup(groupName, "Group for final Project", owner);
		assertEquals(group.getGroupName(), groupName);
	}

	@Test
	void testGetDescription(){
		String description = "Group for final project";
		StudentGroup group = new StudentGroup("CSE 237 Final Project", description, owner);
		assertEquals(group.getDescription(), description);
	}

	@Test
	void testGetOwner(){
		StudentGroup group = new StudentGroup("Group name", "Group description", owner);
		assertEquals(group.getOwner(), owner);
	}

	@Test
	void testGetAdminsInit(){
		StudentGroup group = new StudentGroup("Group name", "Group description", owner);
		ArrayList<Student> adminList = new ArrayList<Student>();
		adminList.add(owner);
		assertEquals(group.getAdmins(), adminList);
	}

	@Test
	void testGetMembersInit(){
		StudentGroup group = new StudentGroup("Group name", "Group description", owner);
		ArrayList<Student> memberList = new ArrayList<Student>();
		memberList.add(owner);
		assertEquals(group.getMembers(), memberList);
	}
	
	@Test
	void testGetIsPrivate() {
		boolean isPrivate = true;
		StudentGroup group = new StudentGroup("Group name", "Group description", owner, isPrivate);
		assertEquals(group.getIsPrivate(), isPrivate);
	}

	@Test
	void testSetGroupName(){
		String newGroupName = "Group Name";
		StudentGroup group = new StudentGroup("Name", "Description", owner);
		group.setGroupName(newGroupName);
		assertEquals(group.getGroupName(), newGroupName);
	}

	@Test
	void testSetGroupDescription(){
		String newDescription = "Group description";
		StudentGroup group = new StudentGroup("Group Name", "Description", owner);
		group.setDescription(newDescription);
		assertEquals(group.getDescription(), newDescription);
	}

	@Test
	void testSetOwner() {
		Student newOwner = new Student("New Owner", "owner@wustl.edu", 2023, "Password");
		StudentGroup group = new StudentGroup("Group Name", "Description", owner);
		group.setOwner(newOwner);
		assertEquals(group.getOwner(), newOwner);
	}
	
	

	@Test
	void testAddGroupMembers(){
		StudentGroup group = new StudentGroup("Group Name", "Description", owner);
		group.addMember(firstGroupMember);
		group.addMember(secondGroupMember);
		ArrayList<Student> groupMembers = new ArrayList<Student>();
		groupMembers.add(owner);
		groupMembers.add(firstGroupMember);
		groupMembers.add(secondGroupMember);
		assertEquals(group.getMembers(), groupMembers);
	}
	
	@Test
	void testRemoveGroupMembers() {
		StudentGroup group = new StudentGroup("Group Name", "Description", owner);
		group.addMember(firstGroupMember);
		group.addMember(secondGroupMember);
		ArrayList<Student> groupMembers = group.getMembers();
		assertEquals(3, groupMembers.size());
		group.removeMember(secondGroupMember);
		assertEquals(2, groupMembers.size());
		assertEquals(groupMembers.contains(secondGroupMember), false);
	}

	@Test
	void testAddToPrivateGroupSuccess(){
		StudentGroup group = new StudentGroup("Group Name", "Description", owner, true);
		group.inviteStudent(firstGroupMember);
		group.addMember(firstGroupMember);
		ArrayList<Student> groupMembers = new ArrayList<Student>();
		groupMembers.add(owner);
		groupMembers.add(firstGroupMember);
		assertEquals(group.getMembers(), groupMembers);
	}

	@Test
	void testAddUninvitedStudentToPrivateGroup(){
		StudentGroup group = new StudentGroup("Group Name", "Description", owner, true);
		group.addMember(firstGroupMember);
		ArrayList<Student> groupMembers = new ArrayList<Student>();
		groupMembers.add(owner);
		assertEquals(group.getMembers(), groupMembers);
	}

	@Test
	void testKickStudent(){
		StudentGroup group = new StudentGroup("Group Name", "Description", owner);
		group.addMember(firstGroupMember);
		group.addMember(secondGroupMember);
		group.kickStudent(firstGroupMember);
		ArrayList<Student> groupMembers = new ArrayList<Student>();
		groupMembers.add(owner);
		groupMembers.add(secondGroupMember);
		assertEquals(group.getMembers(), groupMembers);
	}

	@Test
	void testAddAdmin(){
		StudentGroup group = new StudentGroup("Group Name", "Description", owner);
		group.addMember(firstGroupMember);
		group.addMember(secondGroupMember);
		group.addAdmin(secondGroupMember);
		ArrayList<Student> adminList = new ArrayList<Student>();
		adminList.add(owner);
		adminList.add(secondGroupMember);
		assertEquals(group.getAdmins(), adminList);
	}
}
