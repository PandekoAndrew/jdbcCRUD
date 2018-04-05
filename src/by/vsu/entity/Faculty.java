package by.vsu.entity;

public class Faculty {

	private Long id;
	private String name;

	public Faculty(Long id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return String.format("{\"id\": \"%d\", \"name\": \"%s\"}", this.id, this.name);
	}
}
