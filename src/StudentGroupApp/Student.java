package StudentGroupApp;

//The student class contains all the information about a student that is created upon launching the app

public class Student {
	//member variables for student class
	private String name;
	private String email;
	private Integer classYear;
	private String password;
	//TODO: add an array list of groups

	//initializer for student class
	public Student(String name, String email, Integer classYear, String password) {
	    this.name = name;
	    this.email = email;
	    this.classYear = classYear;
	    this.password = password;
    }

    //getters for student classs
    public String getName(){
		return name;
	}

	public String getEmail(){
		return email;
	}

	public Integer getClassYear() {
		return classYear;
	}

	//Setters for student class
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setClassYear(Integer classYear) {
		this.classYear = classYear;
	}

	//student class functions
	public Boolean checkPassword(String passwordGuess) {
		return passwordGuess.equals(password);
	}

	public void changePassword(String oldPassword, String newPassword) {
		if (oldPassword.equals(password)) {
			password = newPassword;
		}
	}

}
