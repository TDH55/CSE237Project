package Tests;

import static org.junit.jupiter.api.Assertions.*;

import StudentGroupApp.Student;
import org.junit.jupiter.api.Test;

class StudentTests {

	@Test
	void testGetName() {
		String name = "Taylor";
		Student student = new Student(name, "email@email.com", 2022);
		assertEquals(student.getName(), name);
	}

	@Test
	void testGetEmail(){
		String email = "email@email.com";
		Student student = new Student("Taylor", email, 2022);
		assertEquals(student.getEmail(), email);
	}

	@Test
	void testGetClassYear(){
		Integer classYear = 2022;
		Student student = new Student("Taylor", "email@email.com", classYear);
		assertEquals(student.getClassYear(), classYear);
	}

	@Test
	void testSetName(){
		String newName = "Taylor";
		Student student = new Student("name", "email@email.com", 2022);
		student.setName(newName);
		assertEquals(student.getName(), newName);
	}

	@Test
	void testSetEmail(){
		String newEmail = "email@wustl.edu";
		Student student = new Student("Taylor", "email@email.com", 2022);
		student.setEmail(newEmail);
		assertEquals(student.getEmail(), newEmail);
	}

	@Test
	void testSetClassYear(){
		Integer newClassYear = 2023;
		Student student = new Student("Taylor", "email@wustl.edu", 2022);
		student.setClassYear(newClassYear);
		assertEquals(student.getClassYear(), newClassYear);
	}
}
