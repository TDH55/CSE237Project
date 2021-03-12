package StudentGroupApp;

//The student class contains all the information about a student that is created upon launching the app

public class Student {
	//member variables for student class
	private String name;
	private String email;
	private Integer classYear;
	//TODO: add a list of events

	//initializer for student class
	public Student(String name, String email, Integer classYear) {
	    this.name = name;
	    this.email = email;
	    this.classYear = classYear;
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
}
