package StudentGroupApp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.spi.CalendarNameProvider;

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
		int classYear = getInt("Please enter a valid integer value for the class year");
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
		int inputChoice = getInt("Please enter a valid integer value");
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
		int inputChoice = getInt("Please enter a valid integer value");
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
		System.out.println("5. View group events");
		System.out.println("6. Back to group menu");
		System.out.println("7. Back to main menu");
		
		if(this.currentGroup.isAdmin(currentStudent)) {
			System.out.println("8. View admin options");
		}
		
	}

	private void executeViewGroupMenu() {
		int inputChoice = getInt("Please enter a valid integer value");
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
		} else if (inputChoice == 5) {
			displayEventMenu();
			executeEventMenu();
		} else if (inputChoice == 6){
			selectGroupMenu();
		} else if (inputChoice == 7){

			rootMenu();
		} else if (inputChoice == 8) {
			adminOrInvalid();

		} else {
			System.out.println("Please enter a valid option");
			viewGroupMenu();
		}
	}
	
	private void displayEventMenu() {
		System.out.println("Please select from the list of events below: ");
		System.out.println();
		ArrayList<Event> groupEvents = currentGroup.getEvents();
		for (int i = 0; i < groupEvents.size(); i++) {
			System.out.println(i + ". " + groupEvents.get(i).getTitle());
		}
		System.out.println("-1. Back to group view");
	}

	private void executeEventMenu() {
		int inputChoice = getInt("Please enter a valid integer value");
		keyboardIn.nextLine();
		ArrayList<Event> groupEvents = currentGroup.getEvents();
		if (inputChoice == -1) {
			viewGroupMenu();
		} else if (inputChoice >= groupEvents.size() || inputChoice < -1) {
			System.out.println("Please enter a valid option");
			System.out.println();
			displayEventMenu();
			executeEventMenu();
		} else {
			displayEvent(groupEvents.get(inputChoice));
		}

	}
	
	private void displayEvent(Event current) {
		System.out.println(current.getTitle());
		System.out.println(current.getDescription());
		System.out.println(current.getDate().toString());
		System.out.println();
		for (Student rsvped : current.getRsvpedStudents()) {
			System.out.println(rsvped.getName());
		}
		System.out.println();
		
		displayViewGroupMenu();
		executeViewGroupMenu();
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
				if(this.currentGroup.isDisallowedStudent(this.currentStudent)) {
					System.out.println("This member is not allowed in this group. Please enter a valid student");
					System.out.println();
					viewGroupMenu();
				} else {
					this.currentGroup.addMember(currentStudent);
					System.out.println("Successfully added. ");
					System.out.println();
					viewGroupMenu();
				}
			} else {
				if(this.currentGroup.isInvitedStudent(this.currentStudent)) {
					this.currentGroup.addMember(currentStudent);
					System.out.println("Successfully added. ");
					System.out.println();
					viewGroupMenu();
				}
				if(this.currentGroup.isDisallowedStudent(this.currentStudent)) {
					System.out.println("This member is not allowed in this group. Please enter a valid student");
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
		int tagChoice = getMenuChoice(Tag.values().length);
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
		
		Student owner = this.currentGroup.getOwner();
		if(this.currentStudent.equals(owner)) {
			System.out.println("0. View owner options");
		}
		System.out.println("1. Invite student to group");
		System.out.println("2. Bar student from joining group");
		System.out.println("3. Kick a student from the group");
		System.out.println("4. Change group tag");
		System.out.println("5. Create an event");
		System.out.println("6. Back to member menu");
	}
	
	private void executeAdminMenu() {
		int inputChoice = getInt("Please enter a valid integer value");
		keyboardIn.nextLine();
		
		if(inputChoice == 0) {
			Student owner = this.currentGroup.getOwner();
			if(this.currentStudent.equals(owner)) {
				displayOwnerMenu();
				executeOwnerMenu();
			} else {
				System.out.println("Please enter a valid option");
			}
			
		} else if(inputChoice == 1) {
			displayInviteStudentMenu();
			executeInviteStudentMenu();
		} else if(inputChoice == 2) {
			displayDisallowedStudentMenu();
			executeDisallowStudentMenu();
		} else if (inputChoice == 3) {
			displayKickStudentMenu();
		} else if (inputChoice == 4) {
			changeTagMenu();
			displayAdminMenu();
			executeAdminMenu();
		} else if (inputChoice == 5) {
			createEventMenu();
			displayEventMenu();
			executeEventMenu();
		} else if(inputChoice == 6) {
			System.out.println();
			viewGroupMenu();
		} else {
			System.out.println("Please enter a valid option");
			displayAdminMenu();
			executeAdminMenu();
		}
	}
	
	private void displayOwnerMenu() {
		System.out.println("Current group: " + this.currentGroup.getGroupName());
		System.out.println();
		
		System.out.println("1. Promote group member to admin.");
		System.out.println("2. Remove admin.");
		System.out.println("3. Transfer ownership to a group member.");
		System.out.println("4. Back to admin menu.");
	}
		
	private void executeOwnerMenu() {
		int inputChoice = getInt("Please enter a valid integer value");
		keyboardIn.nextLine();
		
		if(inputChoice == 1) {
			displayAdminPromoteMenu();
		} else if(inputChoice == 2) {
			displayAdminDemoteMenu();
		} else if (inputChoice == 3) {
			displayOwnershipTransferMenu();
		} else if(inputChoice == 4) {
			displayAdminMenu();
			executeAdminMenu();
		} else {
			System.out.println("Please enter a valid option");
			displayAdminMenu();
			executeAdminMenu();
		}
		
	}

	private void displayAdminPromoteMenu() {
		//finding owner and removing them from list of possible students to be kicked
		ArrayList<Student> studentsInGroup = new ArrayList<Student>(this.currentGroup.getMembers());
		studentsInGroup.remove(this.currentStudent);
		ArrayList<Student> admins = new ArrayList<Student>(this.currentGroup.getAdmins());
		for(Student admin: admins) {
			studentsInGroup.remove(admin);
		}
		
		if(studentsInGroup.size() > 0) {
			System.out.println();
			System.out.println("Please enter digit corresponding to student you wish to promote: ");
			this.displayListOfStudents(studentsInGroup);
			System.out.println(studentsInGroup.size() + ". Exit");
			executeAdminPromoteMenu(studentsInGroup);
		} else {
			System.out.println();
			System.out.println("No students for you to promote. Exiting.");
			System.out.println();
			displayOwnerMenu();
			executeOwnerMenu();
		}
	}
	
	private void executeAdminPromoteMenu(ArrayList<Student> studentsInGroup) {
		int inputChoice = getInt("Please enter a valid integer value");
		keyboardIn.nextLine();
		
		if(inputChoice >= 0 && inputChoice < studentsInGroup.size()) {
			Student studentToPromote = studentsInGroup.get(inputChoice);
			this.currentGroup.addAdmin(studentToPromote);
			System.out.println("Student successfully promoted.");
			System.out.println();
			displayAdminPromoteMenu();
		} else if(inputChoice == studentsInGroup.size()) {
			displayOwnerMenu();
			executeOwnerMenu();
		} else {
			System.out.println("Please input a valid choice.");
			displayAdminPromoteMenu();
		}
	}
	
	private void displayAdminDemoteMenu() {
		ArrayList<Student> admins = new ArrayList<Student>(this.currentGroup.getAdmins());
		admins.remove(this.currentStudent);
		
		if(admins.size() > 0) {
			System.out.println();
			System.out.println("Please enter digit corresponding to admin you wish to demote: ");
			this.displayListOfStudents(admins);
			System.out.println(admins.size() + ". Exit");
			executeAdminDemoteMenu(admins);
		} else {
			System.out.println();
			System.out.println("No students for you to demote. Exiting.");
			System.out.println();
			displayOwnerMenu();
			executeOwnerMenu();
		}
	}
	
	private void executeAdminDemoteMenu(ArrayList<Student> admins) {
		int inputChoice = getInt("Please enter a valid integer value");
		keyboardIn.nextLine();
		
		if(inputChoice >= 0 && inputChoice < admins.size()) {
			Student studentToDemote = admins.get(inputChoice);
			this.currentGroup.removeAdmin(studentToDemote);
			System.out.println("Student successfully demoted.");
			System.out.println();
			displayAdminDemoteMenu();
		} else if(inputChoice == admins.size()) {
			displayOwnerMenu();
			executeOwnerMenu();
		} else {
			System.out.println("Please input a valid choice.");
			displayAdminDemoteMenu();
		}
	}
	
	private void displayOwnershipTransferMenu() {
		//finding owner and removing them from list of possible students to be kicked
		ArrayList<Student> studentsInGroup = new ArrayList<Student> (this.currentGroup.getMembers());
		studentsInGroup.remove(this.currentStudent);
		
		if(studentsInGroup.size() > 0) {
			System.out.println();
			System.out.println("Please enter digit corresponding to student you wish to promote: ");
			this.displayListOfStudents(studentsInGroup);
			System.out.println(studentsInGroup.size() + ". Exit");
			executeOwnershipTransferMenu(studentsInGroup);
		} else {
			System.out.println();
			System.out.println("No students for you to promote. Exiting.");
			System.out.println();
			displayOwnerMenu();
			executeOwnerMenu();
		}
	}
	
	private void executeOwnershipTransferMenu(ArrayList<Student> studentsInGroup) {
		int inputChoice = getInt("Please enter a valid integer value");
		keyboardIn.nextLine();

		if (inputChoice >= 0 && inputChoice < studentsInGroup.size()) {
			Student studentToPromote = studentsInGroup.get(inputChoice);
			this.currentGroup.setOwner(studentToPromote);
			System.out.println("Ownership successfully transferred to " + studentToPromote.getName() + ".");
			System.out.println();
			displayAdminMenu();
			executeAdminMenu();
		} else if (inputChoice == studentsInGroup.size()) {
			displayOwnerMenu();
			executeOwnerMenu();
		} else {
			System.out.println("Please input a valid choice.");
			displayAdminPromoteMenu();
		}
	}
	private void createEventMenu() {
		System.out.println("Please input a name for the event: ");
		String name = this.keyboardIn.nextLine();
		System.out.println("Please input a description of the event: ");
		String description = this.keyboardIn.nextLine();
		//TODO: more in depth error handling here
		Calendar date = getDate();
		this.keyboardIn.nextLine();

		//TODO: change constructor to take in a date object, not individual value
		Event newEvent = new Event(name, description, date, currentGroup.getOwner());
		currentGroup.addEvent(newEvent);

	}
	
	private void displayInviteStudentMenu() {
		System.out.println();
		System.out.println("Please enter the name of the student you'd like to invite: ");
	}
	
	private void displayDisallowedStudentMenu() {
		System.out.println();
		System.out.println("Please enter the name of the student you'd like to bar from the group: ");
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
	
	private void executeDisallowStudentMenu() {
		String studentToDisallowName = this.keyboardIn.next();
		this.keyboardIn.nextLine();
		Student studentToDisallow = this.findStudentByName(studentToDisallowName);
		
		if(studentToDisallow != null) {
			if(this.currentGroup.isDisallowedStudent(studentToDisallow)) {
				System.out.println();
				System.out.println("Student already barred from group.");
				System.out.println();
				displayAdminMenu();
				executeAdminMenu();
			} else {
				this.currentGroup.disallowStudent(studentToDisallow);
				System.out.println();
				System.out.println("Student successfully barred from group.");
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
	
	private void displayKickStudentMenu() {
		//finding owner and removing them from list of possible students to be kicked
		ArrayList<Student> studentsInGroup = new ArrayList<Student>(this.currentGroup.getMembers());
		Student owner = this.currentGroup.getOwner();
		studentsInGroup.remove(owner);
		
		if(!this.currentStudent.equals(owner)) {
			ArrayList<Student> admins = this.currentGroup.getAdmins();
			for(Student admin: admins) {
				studentsInGroup.remove(admin);
			}
		}
		if(studentsInGroup.size() > 0) {
			System.out.println();
			System.out.println("Please enter digit corresponding to student you wish to kick: ");
			this.displayListOfStudents(studentsInGroup);
			System.out.println(studentsInGroup.size() + ". Exit");
			executeKickStudentMenu(studentsInGroup);
		} else {
			System.out.println();
			System.out.println("No students for you to kick. Exiting.");
			System.out.println();
			displayAdminMenu();
			executeAdminMenu();
		}
		
	}
	
	private void executeKickStudentMenu(ArrayList<Student> studentsInGroup) {
		int inputChoice = getInt("Please enter a valid integer value");
		keyboardIn.nextLine();
		
		if(inputChoice >= 0 && inputChoice < studentsInGroup.size()) {
			Student studentToKick = studentsInGroup.get(inputChoice);
			this.currentGroup.kickStudent(studentToKick);
			this.currentGroup.disallowStudent(studentToKick);
			System.out.println("Student successfully kicked.");
			System.out.println();
			displayKickStudentMenu();
		} else if(inputChoice == studentsInGroup.size()) {
			displayAdminMenu();
			executeAdminMenu();
		} else {
			System.out.println("Please inpute a valid choice.");
			displayKickStudentMenu();
		}
	}
	
	
	private void changeTagMenu() {
		printTagChoices();
		Tag groupTag = chooseTag();

		this.currentGroup.setTag(groupTag);
	}

	private Tag chooseTag() {
		int tagChoice = getMenuChoice(Tag.values().length);
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
		int classYear = getInt("Please enter a valid integer value for the year");
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
		boolean isPrivate = false;
		System.out.println("Please input a name for the group you would like to create:");
		String groupName = this.keyboardIn.nextLine();
		System.out.println("Please input a description of this student group: ");
		String groupDescription = this.keyboardIn.nextLine();
		System.out.println("Please select a tag for your group");
		printTagChoices();
		Tag groupTag = chooseTag();
		System.out.println("Is this group private? (Y/N)");
		String isPrivateQuery = this.keyboardIn.nextLine();
		
		if(isPrivateQuery.equals("Y") || isPrivateQuery.equals("y")) {
			isPrivate = true;
		}

		StudentGroup newGroup = new StudentGroup(groupName, groupDescription, currentStudent, isPrivate, groupTag);

		this.addStudentGroupToList(newGroup);

		return newGroup;
	}

	private void printTagChoices() {
		for (Tag tag : Tag.values()) {
			System.out.print((tag.ordinal() + 1) + ". ");
			System.out.println(tag.toString());
		}
	}

	private int getInt(String errorMessage) {
		int val;
		try {
			val = this.keyboardIn.nextInt();
		} catch(InputMismatchException e) {
			System.out.println(errorMessage);
			this.keyboardIn.nextLine();
			return getInt(errorMessage);
		}
		return val;
	}

	private int getMenuChoice(int maxValue) {
		int choice;
		try {
			choice = this.keyboardIn.nextInt();
			if(choice > maxValue) {
				throw new Exception("Invalid menu option");
			}
		} catch (Exception e) {
			System.out.println("Please select a valid menu option");
			this.keyboardIn.nextLine();
			return getMenuChoice(maxValue);
		}
		return choice;
	}

	private Calendar getDate() {
		Calendar date = Calendar.getInstance();
		System.out.println("Please enter the year of the event as an integer");
		int year = getInt("Please enter a valid integer value for the year");
		System.out.println("Please enter the month of the event as an integer");
		int month = getInt("Please enter a valid integer value for the month");
		System.out.println("Please enter the day of the event as an integer");
		int day = getInt("Please enter a valid integer value for the day");
		System.out.println("Please enter the hour of the event as an integer");
		int hour = getInt("Please enter a valid integer value for the hour");
		System.out.println("Please enter the minute of the event as an integer");
		int minute = getInt("Please enter a valid integer value for the minute");
		try {
			date.set(Calendar.YEAR, year);
			date.set(Calendar.MONTH, month);
			date.set(Calendar.DAY_OF_MONTH, day);
			date.set(Calendar.HOUR, hour);
			date.set(Calendar.MINUTE, minute);
		} catch(Exception e) {

			System.out.println("Please enter a valid date in the Year Month Day Hour Minute format");
			this.keyboardIn.nextLine();
			return getDate();
		}

		return date;
	}
}


