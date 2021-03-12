package Tests;

import static org.junit.jupiter.api.Assertions.*;

import StudentGroupApp.Student;
import org.junit.jupiter.api.Test;

class StudentTests {

	@Test
	void testGetName() {
		String name = "Taylor";
		Student student = new Student(name, "email@email.com", 2022, "password123");
		assertEquals(student.getName(), name);
	}

	@Test
	void testGetEmail(){
		String email = "email@email.com";
		Student student = new Student("Taylor", email, 2022, "password123");
		assertEquals(student.getEmail(), email);
	}

	@Test
	void testGetClassYear(){
		Integer classYear = 2022;
		Student student = new Student("Taylor", "email@email.com", classYear, "password123");
		assertEquals(student.getClassYear(), classYear);
	}

	@Test
	void testSetName(){
		String newName = "Taylor";
		Student student = new Student("name", "email@email.com", 2022, "password123");
		student.setName(newName);
		assertEquals(student.getName(), newName);
	}

	@Test
	void testSetEmail(){
		String newEmail = "email@wustl.edu";
		Student student = new Student("Taylor", "email@email.com", 2022, "password123");
		student.setEmail(newEmail);
		assertEquals(student.getEmail(), newEmail);
	}

	@Test
	void testSetClassYear(){
		Integer newClassYear = 2023;
		Student student = new Student("Taylor", "email@wustl.edu", 2022, "password123");
		student.setClassYear(newClassYear);
		assertEquals(student.getClassYear(), newClassYear);
	}

	@Test
	void testCorrectPassword(){
		String passwordGuess = "Password123";
		Student student = new Student("Taylor", "email@wustl.edu", 2022, "Password123");
		assertTrue(student.checkPassword(passwordGuess));
	}

	@Test
	void testIncorrectPassword(){
		String passwordGuess = "badPass";
		Student student = new Student("Taylor", "email@wustl.edu", 2022, "Password123");
		assertFalse(student.checkPassword(passwordGuess));
	}

	@Test
	void testMismatchedCasePassword(){
		String passwordGuess = "password123";
		Student student = new Student("Taylor", "email@wustl.edu", 2022, "Password123");
		assertFalse(student.checkPassword(passwordGuess));
	}

	@Test
	void testChangePassword(){
		String newPassword = "newPassword123";
		Student student = new Student("Taylor", "email@wustl.edu", 2022, "Password123");
		student.changePassword("Password123", newPassword);
		assertTrue(student.checkPassword(newPassword));
	}

	@Test
	void testChangePasswordIncorrectPassword(){
		String newPassword = "newPassword123";
		Student student = new Student("Taylor", "email@wustl.edu", 2022, "Password123");
		student.changePassword("wrongPassword", newPassword);
		assertFalse(student.checkPassword(newPassword));
	}
}
