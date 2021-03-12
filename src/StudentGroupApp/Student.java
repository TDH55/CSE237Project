package StudentGroupApp;

public class Student {
	private String name;
	private String email;
	private Integer classYear;
	//TODO: add a list of events

	public Student(String name, String email, Integer classYear) {
	    this.name = name;
	    this.email = email;
	    this.classYear = classYear;
    }

    public String getName(){
		return name;
	}

	public String getEmail(){
		return email;
	}

	public Integer getClassYear() {
		return classYear;
	}
}
