package by.vsu.entity;

public class Department {

	private Long id;
	private String name;
	private String faculty;

	public Department(Long id, String name, String faculty) {
		this.id = id;
		this.name = name;
		this.faculty = faculty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	@Override
	public String toString() {
		return String.format("{\"id\": \"%d\", \"name\": \"%s\", \"faculty\": \"%s\"}", this.id, this.name,
				this.faculty);
	}
}
