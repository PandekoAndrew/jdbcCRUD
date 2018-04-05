package by.vsu.entity;

public class Teacher {

	private Long id;
	private String name;
	private String department;

	public Teacher(Long id, String name, String department) {
		this.id = id;
		this.name = name;
		this.department = department;
	}

	public Teacher() {
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return String.format("{\"id\": \"%d\", \"name\": \"%s\", \"department\": \"%s\"}", this.id, this.name,
				this.department);
	}
}
