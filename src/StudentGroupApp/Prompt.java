package StudentGroupApp;

import java.util.ArrayList;
import java.util.Scanner;

public class Prompt {
	private ArrayList<Student> students; 
	private ArrayList<StudentGroup> groups;
	private Student currentStudent;
	private Scanner keyboardIn;
	
	
	
	public Prompt() {
		students  = new ArrayList<Student>();
		groups = new ArrayList<StudentGroup>();
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
		executeLoginMenu();
		
		displayRootMenu();
		executeRootMenu();
		
	}
	
	
	
	private void displayLoginMenu() {
		System.out.println("Welcome! Please enter your name to sign/log in: ");
	}
	
	
	private void executeLoginMenu() {
		
		String userName = this.keyboardIn.next();
		if (this.keyboardIn.hasNext()) {
			userName += " " + this.keyboardIn.next();
		}
		Student studentToBeLoggedIn = this.findStudentByName(userName);
		
		
		
		if(studentToBeLoggedIn != null) {
			this.loginStudent(studentToBeLoggedIn);
		}
		else {
			studentToBeLoggedIn = this.createStudent(userName);
			this.loginStudent(studentToBeLoggedIn);
		}
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
	
	
	private void displayRootMenu() {
		System.out.println("Current student: " + this.currentStudent.getName());
		System.out.println();
		
		System.out.println("1. See groups");
		System.out.println("2. Search groups by name");
		System.out.println("3. View a group");
		System.out.println("4. Log out");
		System.out.println("5. Quit");
	}
	
	
	private void executeRootMenu() {
		int inputChoice = this.keyboardIn.nextInt();
		if (inputChoice == 1) {
			for (int i = 0; i < groups.size(); i++) {
				System.out.println(groups.get(i).getGroupName());
			}
			displayRootMenu();
			executeRootMenu();
		}
		else if (inputChoice == 2) {
			//Search groups by name
		}
		else if (inputChoice == 3) {
			//View a group
		}
		else if (inputChoice == 4) {
			this.displayLoginMenu();
			executeLoginMenu();
			
			displayRootMenu();
			executeRootMenu();
		}
		else if (inputChoice == 5) {
			return;
		}
		else {
			System.out.println("Please enter a valid option");
			displayRootMenu();
			executeRootMenu();
		}
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
