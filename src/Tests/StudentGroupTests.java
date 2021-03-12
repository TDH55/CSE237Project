package Tests;

import static org.junit.jupiter.api.Assertions.*;

import StudentGroupApp.Student;
import StudentGroupApp.StudentGroup;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class StudentGroupTests {
	Student owner = new Student("Taylor", "email@wustl.edu", 2022, "password123");

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
}
