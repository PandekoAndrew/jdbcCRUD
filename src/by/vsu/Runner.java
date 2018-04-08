package by.vsu;

import java.util.Scanner;

import by.vsu.dao.DepartmentDAO;
import by.vsu.dao.FacultyDAO;
import by.vsu.dao.TeacherDAO;
import by.vsu.entity.Department;
import by.vsu.entity.Faculty;
import by.vsu.entity.Teacher;

public class Runner {

	public static void main(String[] args) {
		TeacherDAO tDAO = new TeacherDAO();
		DepartmentDAO dDAO = new DepartmentDAO();
		FacultyDAO fDAO = new FacultyDAO();
		Scanner scanner = new Scanner(System.in);
		int choose = -1;
		int innerChoose = -1;

		while (choose != 0) {
			System.out.println("Choose an action: ");
			System.out.println("1. Show state");
			System.out.println("2. Teachers");
			System.out.println("3. Departments");
			System.out.println("4. Faculties");
			System.out.println("5. Generate data");
			System.out.println("0. Exit");

			choose = scanner.nextInt();
			switch (choose) {
			case 1:
				printState();
				break;
			case 2:
				innerChoose();
				innerChoose = scanner.nextInt();
				switch(innerChoose){
				case 1:
					System.out.println("Enter: <name> <department>");
					tDAO.insert(new Teacher(null, scanner.next(), scanner.next()));
					break;
				case 2:
					System.out.println("Enter: <id>");
					System.out.println(tDAO.getById(scanner.nextLong()));
					break;
				case 3:
					System.out.println("Enter: <id> <name> <department>");
					tDAO.update(new Teacher(scanner.nextLong(), scanner.next(), scanner.next()));
					break;
				case 4:
					System.out.println("Enter: <id>");
					tDAO.delete(tDAO.getById(scanner.nextLong()));
					break;
				case 5:
					tDAO.deleteAll();
					break;
				default:
					break;
				}
				break;
			case 3:
				innerChoose();
				innerChoose = scanner.nextInt();
				switch(innerChoose){
				case 1:
					System.out.println("Enter: <name> <faculty>");
					dDAO.insert(new Department(null, scanner.next(), scanner.next()));
					break;
				case 2:
					System.out.println("Enter: <id>");
					System.out.println(dDAO.getById(scanner.nextLong()));
					break;
				case 3:
					System.out.println("Enter: <id> <name> <faculty>");
					dDAO.update(new Department(scanner.nextLong(), scanner.next(), scanner.next()));
					break;
				case 4:
					System.out.println("Enter: <id>");
					dDAO.delete(dDAO.getById(scanner.nextLong()));
					break;
				case 5:
					dDAO.deleteAll();
					break;
				default:
					break;
				}
				break;
			case 4:
				innerChoose();
				innerChoose = scanner.nextInt();
				switch(innerChoose){
				case 1:
					System.out.println("Enter: <name>");
					fDAO.insert(new Faculty(null, scanner.next()));
					break;
				case 2:
					System.out.println("Enter: <id>");
					System.out.println(fDAO.getById(scanner.nextLong()));
					break;
				case 3:
					System.out.println("Enter: <id> <name>");
					fDAO.update(new Faculty(scanner.nextLong(), scanner.next()));
					break;
				case 4:
					System.out.println("Enter: <id>");
					fDAO.delete(fDAO.getById(scanner.nextLong()));
					break;
				case 5:
					fDAO.deleteAll();
					break;
				default:
					break;
				}
				break;
			case 5:
				init();
				break;
			default:
				break;
			}
		}

		System.out.println("\nBye!");
		scanner.close();
	}

	private static void init() {
		TeacherDAO tDAO = new TeacherDAO();
		DepartmentDAO dDAO = new DepartmentDAO();
		FacultyDAO fDAO = new FacultyDAO();

		Teacher teacher = new Teacher();
		Department department = new Department();
		Faculty faculty = new Faculty();

		teacher.setName("John");
		teacher.setDepartment("DP1");
		tDAO.insert(teacher);

		teacher.setName("Doe");
		teacher.setDepartment("DP1");
		tDAO.insert(teacher);

		teacher.setName("Alisa");
		teacher.setDepartment("DP2");
		tDAO.insert(teacher);

		teacher.setName("Bob");
		teacher.setDepartment("DP2");
		tDAO.insert(teacher);

		department.setName("DP1");
		department.setFaculty("FM&IT");
		dDAO.insert(department);

		department.setName("DP2");
		department.setFaculty("FM&IT");
		dDAO.insert(department);

		faculty.setName("FM&IT");
		fDAO.insert(faculty);
	}
	
	private static void innerChoose(){
		System.out.println("1. Add");
		System.out.println("2. Find by id");
		System.out.println("3. Update");
		System.out.println("4. Delete by id");
		System.out.println("5. Delete all");
		System.out.println("9. Return");
	}
	
	private static void printState() {
		TeacherDAO tDAO = new TeacherDAO();
		DepartmentDAO dDAO = new DepartmentDAO();
		FacultyDAO fDAO = new FacultyDAO();

		System.out.println("Teachers: ");
		tDAO.getAll().forEach(System.out::println);
		System.out.println();

		System.out.println("Departments: ");
		dDAO.getAll().forEach(System.out::println);
		System.out.println();

		System.out.println("Faculties: ");
		fDAO.getAll().forEach(System.out::println);
		System.out.println("________________________________________________");
	}
}
