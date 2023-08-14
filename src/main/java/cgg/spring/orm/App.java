package cgg.spring.orm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cgg.spring.orm.dao.StudentDao;
import cgg.spring.orm.entities.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
    	StudentDao studentdao = context.getBean("studentdao",StudentDao.class);
    	
    	Student student = new Student();
    	student.setStudentId(2);
    	student.setStudentName("Farooq");
    	student.setStudentCity("HYD");
    	
    	
    	
    	int r= studentdao.insert(student);
    	System.out.println("done "+r);
    }
}
