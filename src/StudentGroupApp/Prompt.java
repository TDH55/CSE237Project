package StudentGroupApp;

import java.util.ArrayList;
import java.util.Scanner;

public class Prompt {
	private ArrayList<Student> students; 
	private Student currentStudent;
	private Scanner keyboardIn;
	
	public Prompt() {
		students  = new ArrayList<Student>();
		keyboardIn = new Scanner(System.in);
		currentStudent = new Student("", "", 0, "");
	}
	public static void main(String[] args) {
		Prompt studentGroupPrompts = new Prompt();
		studentGroupPrompts.addStudentToList(new Student("Kedar", "kedar.kedar@wustl.edu", 2021, "password"));
		studentGroupPrompts.addStudentToList(new Student("Karl", "karl@outlook.com", 2022, "secret"));
		studentGroupPrompts.runMenu();
	}
	
	private void addStudentToList(Student studentToAdd) {
		this.students.add(studentToAdd);
	}
	
	private void setCurrentStudent(Student newCurrentStudent) {
		this.currentStudent = newCurrentStudent;
	}
	
	private void runMenu() {
		this.displayLoginMenu();
		String userName = this.keyboardIn.next();
		Student studentToBeLoggedIn = this.findStudentByName(userName);
		
		
		
		if(studentToBeLoggedIn != null) {
			this.loginStudent(studentToBeLoggedIn);
		}
		else {
			studentToBeLoggedIn = this.createStudent(userName);
			this.loginStudent(studentToBeLoggedIn);
		}
		
		System.out.println("Current student: " + this.currentStudent.getName());
	}
	
	private Student findStudentByName(String name) {
		Student foundStudent;
		for(Student s : this.students) {
			if(s.getName() != null && s.getName().equals(name)) {
				int foundStudentIndex = this.students.indexOf(s);
				foundStudent = this.students.get(foundStudentIndex);
				return foundStudent;
			}
		}
		
		return null;
	}
	
	private void displayLoginMenu() {
		System.out.println("Welcome! Please enter your name to sign/log in: ");
	}
	
	
	private void loginStudent(Student student) {
		int currentStudentIndex = this.students.indexOf(student);
		this.setCurrentStudent(this.students.get(currentStudentIndex));
	}
	
	private Student createStudent(String name) {
		System.out.println("Please input an email for the student: ");
		String email = this.keyboardIn.next();
		System.out.println("Please input a class year for the student: ");
		int classYear = this.keyboardIn.nextInt();
		System.out.println("Please input a password for the student: ");
		String password = this.keyboardIn.next();
		
		Student newStudent = new Student(name, email, classYear, password);
		this.addStudentToList(newStudent);
		
		return newStudent;
	}
	
	private void createStudent() {
		System.out.println("Please input a name for the student: ");
		String name = this.keyboardIn.next();
		System.out.println("Please input an email for the student: ");
		String email = this.keyboardIn.next();
		System.out.println("Please input a class year for the student: ");
		int classYear = this.keyboardIn.nextInt();
		System.out.println("Please input a password for the student: ");
		String password = this.keyboardIn.next();
		
		Student newStudent = new Student(name, email, classYear, password);
		this.addStudentToList(newStudent);
		
	}
}
