package cgg.spring.orm.dao;

import java.util.List;

import cgg.spring.orm.entities.Student;

public interface StudentDao {
	
	int insert(Student student);
	void update(Student student);
	void delete(int studentId);
	Student getStudent(int studentId);
	List<Student> getAllStudents();
	
}
