package StudentGroupApp;

import java.util.ArrayList;
import java.util.Scanner;

public class Prompt {
	private ArrayList<Student> students;
	private ArrayList<StudentGroup> groups;
	private Student currentStudent;
	private StudentGroup currentGroup;
	private Scanner keyboardIn;

	public Prompt() {
		students = new ArrayList<Student>();
		groups = new ArrayList<StudentGroup>();
		keyboardIn = new Scanner(System.in);
		currentStudent = new Student("", "", 0, "");
		currentGroup = new StudentGroup("", "", null, Tag.None);
	}

	public static void main(String[] args) {
		
		Prompt studentGroupPrompts = new Prompt();
		
		//Creating test students and groups
		Student testStudentOne = new Student("Kedar", "kedar.kedar@wustl.edu", 2021, "password");
		Student testStudentTwo = new Student("Karl", "karl@outlook.com", 2022, "secret");
		studentGroupPrompts.addStudentToList(testStudentOne);
		studentGroupPrompts.addStudentToList(testStudentTwo);
		studentGroupPrompts.addStudentGroupToList(
				new StudentGroup("CSE 237 Study Group", "A study group for CSE 237", testStudentOne, Tag.Academic));
		studentGroupPrompts.addStudentGroupToList(
				new StudentGroup("Anime Fans", "A group to talk about all things anime", testStudentOne, Tag.Recreational));
		studentGroupPrompts.addStudentGroupToList(
				new StudentGroup("Gamers", "A group to talk about all things gaming", testStudentOne, Tag.Recreational));
		
		
		studentGroupPrompts.runMenu();
	}

	private void addStudentToList(Student studentToAdd) {
		this.students.add(studentToAdd);
	}

	// I changed this to public to write the tests for search - Taylor
	public void addStudentGroupToList(StudentGroup groupToAdd) {
		this.groups.add(groupToAdd);
	}

	private void setCurrentStudent(Student newCurrentStudent) {
		this.currentStudent = newCurrentStudent;
	}

	private void runMenu() {
		loginMenu();
		rootMenu();
	}

	private void displayLoginMenu() {
		System.out.println("Welcome! Please enter your name to sign/log in: ");
	}

	private void executeLoginMenu() {

		String userName = this.keyboardIn.nextLine();
		Student studentToBeLoggedIn = this.findStudentByName(userName);

		// If the student is already in the system, ask for their password,
		// otherwise create a new student
		if (studentToBeLoggedIn != null) {
			this.executePasswordMenu(studentToBeLoggedIn);
			
		} else {
			studentToBeLoggedIn = this.createStudent(userName);
			this.loginStudent(studentToBeLoggedIn);
		}
	}

	private Student findStudentByName(String name) {
		Student foundStudent;
		for (Student s : this.students) {
			if (s.getName() != null && s.getName().equals(name)) {
				int foundStudentIndex = this.students.indexOf(s);
				foundStudent = this.students.get(foundStudentIndex);
				return foundStudent;
			}
		}

		return null;
	}
	
	private void executePasswordMenu(Student studentToBeLoggedIn) {
		// Check user input against the student's password, call recursively if wrong
		System.out.println("Please enter your password: ");
		String password = this.keyboardIn.nextLine();
		if(studentToBeLoggedIn.checkPassword(password)) {
			this.loginStudent(studentToBeLoggedIn);
		}
		else {
			System.out.println();
			executePasswordMenu(studentToBeLoggedIn);
		}
	}
	
	private void loginStudent(Student student) {
		int currentStudentIndex = this.students.indexOf(student);
		this.setCurrentStudent(this.students.get(currentStudentIndex));
	}

	private Student createStudent(String name) {
		System.out.println("Please input an email for the student: ");
		String email = this.keyboardIn.next();
		this.keyboardIn.nextLine();
		System.out.println("Please input a class year for the student: ");
		int classYear = this.keyboardIn.nextInt();
		this.keyboardIn.nextLine();
		System.out.println("Please input a password for the student: ");
		String password = this.keyboardIn.next();
		this.keyboardIn.nextLine();


		Student newStudent = new Student(name, email, classYear, password);
		this.addStudentToList(newStudent);

		return newStudent;
	}

	private void displayRootMenu() {
		System.out.println("Current student: " + this.currentStudent.getName());
		System.out.println();

		System.out.println("0. Create a group");
		System.out.println("1. See groups");
		System.out.println("2. See groups by tags");
		System.out.println("3. Search groups by name");
		System.out.println("4. View a group");
		System.out.println("5. Log out");
		System.out.println("6. Quit");
	}

	private void executeRootMenu() {
		int inputChoice = this.keyboardIn.nextInt();
		this.keyboardIn.nextLine();

		
		if (inputChoice == 0) {
			// Create a group
			currentGroup = createStudentGroup();
			viewGroupMenu();
		}
		else if (inputChoice == 1) {
			// See groups
			printAllGroups();
			rootMenu();
		} else if (inputChoice == 2) {
			// See groups by tag
			selectTagMenu();
			rootMenu();
		} else if (inputChoice == 3) {
			// Search groups by name
			searchMenu();
			rootMenu();
		} else if (inputChoice == 4) {
			// View a group
			selectGroupMenu();
		} else if (inputChoice == 5) {
			// Log out
			loginMenu();
			rootMenu();
		} else if (inputChoice == 6) {
			// Quit
			return;
		} else {
			System.out.println("Please enter a valid option");
			rootMenu();
		}
	}

	private void printAllGroups() {
		for (StudentGroup group : groups) {
			System.out.println(group.getGroupName());
		}
	}

	private void loginMenu() {
		displayLoginMenu();
		executeLoginMenu();
	}

	private void searchMenu() {
		displaySearch();
		executeSearch();
	}

	private void selectGroupMenu() {
		displaySelectGroupMenu();
		executeSelectGroupMenu();
	}

	private void viewGroupMenu() {
		displayViewGroupMenu();
		executeViewGroupMenu();
	}

	private void rootMenu() {
		displayRootMenu();
		executeRootMenu();
	}

	public ArrayList<StudentGroup> getGroupByTag(Tag tag) {
		ArrayList<StudentGroup> groupsWithTag = new ArrayList<>();
		for (StudentGroup group : groups) {
			if(group.getTag() == tag) {
				groupsWithTag.add(group);
			}
		}
		return groupsWithTag;
	}

	private void displaySelectGroupMenu() {
		System.out.println("Please select from the list of groups below: ");
		System.out.println();
		for (int i = 0; i < this.groups.size(); i++) {
			System.out.println(i + ". " + groups.get(i).getGroupName());
		}
		System.out.println("-1. Back to main menu");
	}

	private void executeSelectGroupMenu() {
		int inputChoice = this.keyboardIn.nextInt();
		keyboardIn.nextLine();
		if (inputChoice == -1) {
			rootMenu();
		} else if (inputChoice >= this.groups.size() || inputChoice < -1) {
			System.out.println("Please enter a valid option");
			System.out.println();
			selectGroupMenu();
		} else {
			this.currentGroup = this.groups.get(inputChoice);
			viewGroupMenu();
		}

	}

	private void displayViewGroupMenu() {
		System.out.println("Current group: " + this.currentGroup.getGroupName());
		System.out.println();
		
		ArrayList<Student> members = this.currentGroup.getMembers();
		
		if(members.contains(this.currentStudent)) {
			System.out.println("0. Leave Group");
		}
		else {
			if(!this.currentGroup.getIsPrivate()) {
				System.out.println("0. Join Group");
			} else {
				if(this.currentGroup.isInvitedStudent(this.currentStudent)) {
					System.out.println("0. Join Group");
				}
			}
		}
		System.out.println("1. See group description");
		System.out.println("2. View group owner");
		System.out.println("3. View group admins");
		System.out.println("4. View group members");
		System.out.println("5. Back to group menu");
		System.out.println("6. Back to main menu");
		
		if(this.currentGroup.isAdmin(currentStudent)) {
			System.out.println("7. View admin options");
		}
		
	}

	private void executeViewGroupMenu() {
		int inputChoice = this.keyboardIn.nextInt();
		keyboardIn.nextLine();
		ArrayList<Student> members = this.currentGroup.getMembers();
		
		if (inputChoice == 0) {
			joinOrLeaveGroup(members);
		} else if (inputChoice == 1) {
			System.out.println(this.currentGroup.getDescription());
			System.out.println();
			viewGroupMenu();
		} else if (inputChoice == 2) {
			Student owner = this.currentGroup.getOwner();
			System.out.println(this.currentGroup.getGroupName() + " Owner: " + owner.getName());
			System.out.println();
			viewGroupMenu();

		} else if (inputChoice == 3) {
			System.out.println(this.currentGroup.getGroupName() + " Admins: ");
			displayListOfStudents(this.currentGroup.getAdmins());
			viewGroupMenu();

		} else if (inputChoice == 4) {
			System.out.println(this.currentGroup.getGroupName() + " Members: ");
			displayListOfStudents(this.currentGroup.getMembers());
			viewGroupMenu();
		} else if (inputChoice == 5){
			selectGroupMenu();
		} else if (inputChoice == 6){

			rootMenu();
		} else if (inputChoice == 7) {
			adminOrInvalid();

		} else {
			System.out.println("Please enter a valid option");
			viewGroupMenu();
		}
	}

	private void adminOrInvalid() {
		if(this.currentGroup.isAdmin(currentStudent)) {
			displayAdminMenu();
			executeAdminMenu();
		} else {
			System.out.println("Please enter a valid option");
			viewGroupMenu();
		}
	}

	private void joinOrLeaveGroup(ArrayList<Student> members) {
		if(members.contains(this.currentStudent)) {
			this.currentGroup.removeMember(currentStudent);
			System.out.println("Successfully removed. ");
			System.out.println();
			viewGroupMenu();
		} else {
			if(!this.currentGroup.getIsPrivate()) {
				this.currentGroup.addMember(currentStudent);
				System.out.println("Successfully added. ");
				System.out.println();
				viewGroupMenu();
			} else {
				if(this.currentGroup.isInvitedStudent(this.currentStudent)) {
					this.currentGroup.addMember(currentStudent);
					System.out.println("Successfully added. ");
					System.out.println();
					viewGroupMenu();
				}
				else {
					System.out.println("Please enter a valid option");
					viewGroupMenu();
				}
			}
		}
	}

	private void selectTagMenu() {
		System.out.println("Enter the tag you would like to see groups for");
		printTagChoices();
		int tagChoice = this.keyboardIn.nextInt();
		keyboardIn.nextLine();
		Tag groupTag = Tag.None;
		for (Tag tag : Tag.values()) {
			if((tag.ordinal() + 1) == tagChoice) {
				groupTag = tag;
			}
		}
		//TODO: Allow users to view group from here? (next iteration?)
		ArrayList<StudentGroup> groupsToDisplay = getGroupByTag(groupTag);
		if(groupsToDisplay.isEmpty()) {
			System.out.println("No groups with this tag");
		} else {
			for (StudentGroup group : groupsToDisplay) {
				System.out.println(group.getGroupName());
			}
		}
	}

	
	private void displayAdminMenu() {
		System.out.println("Current group: " + this.currentGroup.getGroupName());
		System.out.println();
		System.out.println("1. Invite student to group");
		System.out.println("2. Change group tag");
		System.out.println("3. Back to member menu");
	}
	
	private void executeAdminMenu() {
		int inputChoice = this.keyboardIn.nextInt();
		keyboardIn.nextLine();
		if(inputChoice == 1) {
			displayInviteStudentMenu();
			executeInviteStudentMenu();
		} else if (inputChoice == 2) {
			changeTagMenu();
			displayAdminMenu();
			executeAdminMenu();
		} else if(inputChoice == 3) {
			System.out.println();
			viewGroupMenu();
		} else {
			System.out.println("Please enter a valid option");
			displayAdminMenu();
			executeAdminMenu();
		}
	}
	
	private void displayInviteStudentMenu() {
		System.out.println();
		System.out.println("Please enter the name of the student you'd like to invite: ");
	}
	
	private void executeInviteStudentMenu() {
		String studentToInviteName = this.keyboardIn.next();
		this.keyboardIn.nextLine();
		Student studentToInvite = this.findStudentByName(studentToInviteName);
		
		if(studentToInvite != null) {
			if(this.currentGroup.isInvitedStudent(studentToInvite)) {
				System.out.println();
				System.out.println("Student already invited.");
				System.out.println();
				displayAdminMenu();
				executeAdminMenu();
			} else {
				this.currentGroup.inviteStudent(studentToInvite);
				System.out.println();
				System.out.println("Student successfully invited.");
				System.out.println();
				displayAdminMenu();
				executeAdminMenu();
			}
			
		} else {
			System.out.println();
			System.out.println("No student by that name found.");
			System.out.println();
			displayAdminMenu();
			executeAdminMenu();
		}
	}

	private void changeTagMenu() {
		printTagChoices();
		Tag groupTag = chooseTag();

		this.currentGroup.setTag(groupTag);
	}

	private Tag chooseTag() {
		int tagChoice = keyboardIn.nextInt();
		keyboardIn.nextLine();
		Tag groupTag = Tag.None;
		for (Tag tag : Tag.values()) {
			if((tag.ordinal() + 1) == tagChoice) {
				groupTag = tag;
			}
		}
		return groupTag;
	}

	private void displaySearch() {
		System.out.println("Enter a search term for the group you want");
	}
	
	private void executeSearch() {
		String searchQuery = keyboardIn.nextLine();
		ArrayList<StudentGroup> searchResults = searchGroups(searchQuery);
		if (searchResults.isEmpty()) {
			System.out.println("No such group");
		}
		for (StudentGroup group : searchResults) {
			System.out.println(group.getGroupName());
		}
	}
	
	private void displayListOfStudents(ArrayList<Student> students) {
		for (int i = 0; i < students.size(); i++) {
			System.out.println(i + ". " + students.get(i).getName());
		}
	}

	private void createStudent() {
		System.out.println("Please input a name for the student: ");
		String name = this.keyboardIn.nextLine();
		System.out.println("Please input an email for the student: ");
		String email = this.keyboardIn.next();
		this.keyboardIn.nextLine();
		System.out.println("Please input a class year for the student: ");
		int classYear = this.keyboardIn.nextInt();
		this.keyboardIn.nextLine();
		System.out.println("Please input a password for the student: ");
		String password = this.keyboardIn.next();
		this.keyboardIn.nextLine();

		Student newStudent = new Student(name, email, classYear, password);
		this.addStudentToList(newStudent);

	}

	public ArrayList<StudentGroup> searchGroups(String query) {
		//TODO: Implement search
		ArrayList<StudentGroup> results = new ArrayList<StudentGroup>();
		for (StudentGroup group : this.groups) {
			if (group.getGroupName().toLowerCase().contains(query.toLowerCase())) {
				results.add(group);
			}
		}
		return results;
	}
	
	//create a new student group with current user as owner of group
	private StudentGroup createStudentGroup() {
		System.out.println("Please input a name for the group you would like to create:");
		String groupName = this.keyboardIn.nextLine();
		System.out.println("Please input a description of this student group: ");
		String groupDescription = this.keyboardIn.next();
		keyboardIn.nextLine();
		System.out.println("Please select a tag for your group");
		printTagChoices();
		//TODO: refactor this into a function, it is used multiple times
		Tag groupTag = chooseTag();

		StudentGroup newGroup = new StudentGroup(groupName, groupDescription, currentStudent, groupTag);

		this.addStudentGroupToList(newGroup);

		return newGroup;
	}

	private void printTagChoices() {
		for (Tag tag : Tag.values()) {
			System.out.print((tag.ordinal() + 1) + ". ");
			System.out.println(tag.toString());
		}
	}
}


