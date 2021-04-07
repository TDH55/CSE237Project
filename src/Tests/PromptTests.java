package Tests;

import static org.junit.jupiter.api.Assertions.*;

import StudentGroupApp.Prompt;
import StudentGroupApp.Student;
import StudentGroupApp.StudentGroup;
import StudentGroupApp.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class PromptTests {

	@Test
	void testSearch() {
		Prompt prompt = new Prompt();
		Student owner = new Student("John", "john@email.com", 2022, "password");
		StudentGroup group1 = new StudentGroup("group 1", "This is the first group", owner, Tag.None);
		StudentGroup group2 = new StudentGroup("group 2", "This is the second group", owner, Tag.None);
		prompt.addStudentGroupToList(group1);
		prompt.addStudentGroupToList(group2);
		ArrayList<StudentGroup> correctResult = new ArrayList<StudentGroup>();
		correctResult.add(group2);
		String query = "group 2";
		assertEquals(prompt.searchGroups(query), correctResult);
	}

	@Test
	void testSearchMismatchedCase() {
		Prompt prompt = new Prompt();
		Student owner = new Student("John", "john@email.com", 2022, "password");
		StudentGroup group1 = new StudentGroup("group 1", "This is the first group", owner, Tag.None);
		StudentGroup group2 = new StudentGroup("grOup 2", "This is the second group", owner, Tag.None);
		prompt.addStudentGroupToList(group1);
		prompt.addStudentGroupToList(group2);
		ArrayList<StudentGroup> correctResult = new ArrayList<StudentGroup>();
		correctResult.add(group2);
		String query = "Group 2";
		assertEquals(prompt.searchGroups(query), correctResult);
	}

	@Test
	void testGetGroupByTag(){
		Prompt prompt = new Prompt();
		Student owner = new Student("John", "john@email.com", 2022, "password");
		StudentGroup group1 = new StudentGroup("group 1", "This is the first group", owner, Tag.Academic);
		StudentGroup group2 = new StudentGroup("group 2", "This is the first group", owner, Tag.Academic);
		StudentGroup group3 = new StudentGroup("group 3", "This is the first group", owner, Tag.Recreational);
		StudentGroup group4 = new StudentGroup("grOup 4", "This is the second group", owner, Tag.None);
		prompt.addStudentGroupToList(group1);
		prompt.addStudentGroupToList(group2);
		prompt.addStudentGroupToList(group3);
		prompt.addStudentGroupToList(group4);
		ArrayList<StudentGroup> correctResult = new ArrayList<StudentGroup>();
		correctResult.add(group1);
		correctResult.add(group2);
		assertEquals(correctResult, prompt.getGroupByTag(Tag.Academic));
	}
}
