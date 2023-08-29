package cgg.spring.orm.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cgg.spring.orm.entities.Student;

@Component("studentdao")
public class StudentDaoImpl implements StudentDao {

	
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//Save the student data
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insert(Student student) {
		//Insert query
		Integer i = (Integer)hibernateTemplate.save(student);
		return i;
	}
	
	//Updating data
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Student student) {
		this.hibernateTemplate.update(student);
		
	}
	
	//Deleting the data
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int studentId) {
		Student student = this.hibernateTemplate.get(Student.class, studentId);
		this.hibernateTemplate.delete(student);
		
	}
	
	//Getting single data(object)
	@Override
	public Student getStudent(int studentId) {
		Student student = this.hibernateTemplate.get(Student.class, studentId);
		return student;
	}
	
	//Getting all data(objects)
	@Override
	public List<Student> getAllStudents() {
		List<Student> students = this.hibernateTemplate.loadAll(Student.class);
		return students;
	}

}
