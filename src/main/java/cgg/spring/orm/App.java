package cgg.spring.orm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cgg.spring.orm.dao.StudentDao;
import cgg.spring.orm.entities.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		StudentDao studentdao = context.getBean("studentdao", StudentDao.class);

//    	Student student = new Student();
//    	student.setStudentId(3);
//    	student.setStudentName("Raju");
//    	student.setStudentCity("NZB");
//    	
//    	int r= studentdao.insert(student);
//    	System.out.println("done "+r);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean go = true;
		while (go) {
			System.out.println("Press 1 to add new student");
			System.out.println("Press 2 to display all Students");
			System.out.println("Press 3 to get details of Single Student");
			System.out.println("Press 4 for Deleting student");
			System.out.println("Press 5 to Update student");
			System.out.println("Press 6 for Exit");

			try {
				int input = Integer.parseInt(br.readLine());
				switch (input) {
				case 1: // add student
					System.out.println("Eneter student Id: ");
					int uId = Integer.parseInt(br.readLine());

					System.out.println("Enter Student Name");
					String name = br.readLine();

					System.out.println("Enter Student City");
					String city = br.readLine();

					Student student = new Student();
					student.setStudentId(uId);
					student.setStudentName(name);
					student.setStudentCity(city);

					int r = studentdao.insert(student);
					System.out.println(r + "student added");

					System.out.println("*****************************");
					System.out.println();
					break;

				case 2:// Display all students
					List<Student> students = studentdao.getAllStudents();
					for (Student st : students) {
						System.out.println("Id:   " + st.getStudentId());
						System.out.println("Name: " + st.getStudentName());
						System.out.println("City: " + st.getStudentCity());
						System.out.println("____________________________________");
					}
					System.out.println();
					break;

				case 3:// Get single student data
					System.out.println("Enter student id: ");
					int studId = Integer.parseInt(br.readLine());

					Student stud = studentdao.getStudent(studId);
					System.out.println("Id:   " + stud.getStudentId());
					System.out.println("Name: " + stud.getStudentName());
					System.out.println("City: " + stud.getStudentCity());
					System.out.println("____________________________________");
					break;

				case 4:// deleting student
					System.out.println("Enter student id: ");
					int Id = Integer.parseInt(br.readLine());

					studentdao.delete(Id);
					System.out.println("Student deleted");
					break;

				case 5://updating
					System.out.println("Enter student id: ");
					Id = Integer.parseInt(br.readLine());

					System.out.println("Enter Student Name");
					name = br.readLine();

					System.out.println("Enter Student City");
					city = br.readLine();

					Student student1 = new Student();
					student1.setStudentId(Id);
					student1.setStudentName(name);
					student1.setStudentCity(city);

					studentdao.update(student1);
					System.out.println("Student updated");
					break;

				case 6:// exit
					go = false;
					break;
				default:
					System.out.println("Invalid input");
						
				}

			} catch (NumberFormatException | IOException e) {
				System.out.println("Invalid input try with another one");
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Thank you for using my application");
		System.out.println("See you soon");

	}
}
