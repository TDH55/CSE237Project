//The student class contains all the information about a student that is created upon launching the app:

package StudentGroupApp;

public class Student {
	private String name;
	private String email;
	private Integer classYear;
	private String password;
	//TODO: add an array list of groups

	public Student(String name, String email, Integer classYear, String password) {
	    this.name = name;
	    this.email = email;
	    this.classYear = classYear;
	    this.password = password;
    }

//student class getters
    public String getName(){
		return name;
	}

	public String getEmail(){
		return email;
	}

	public Integer getClassYear() {
		return classYear;
	}

//Student class setters
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setClassYear(Integer classYear) {
		this.classYear = classYear;
	}

//Basic student functions
	public Boolean checkPassword(String passwordGuess) {
		return passwordGuess.equals(password);
	}

	public void changePassword(String oldPassword, String newPassword) {
		if (oldPassword.equals(password)) {
			password = newPassword;
		}
	}

}
