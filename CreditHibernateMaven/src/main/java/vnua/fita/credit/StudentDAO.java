package vnua.fita.credit;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.transaction.SystemException;


public class StudentDAO {
	@SuppressWarnings("unchecked")
	
	public static List<Student> getAllStudent() {
	    Transaction transaction = null;
	    List<Student> listOfStudent = null;
	    Session session = null;

	    try {
	        session = CreditHibernateUtil.getSessionFactory().openSession();
	        transaction = session.beginTransaction();

	        listOfStudent = session.createQuery("from Student", Student.class).getResultList();

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback(); // rollback trước khi close session
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close(); // đóng session sau rollback
	        }
	    }

	    return listOfStudent;
	}

	
	public static Student getStudent(int id) throws IllegalStateException, SystemException {
		Transaction transaction = null;
		Student student = null;
		
		try (Session session = CreditHibernateUtil.getSessionFactory().openSession()){
			//Star a transaction
			transaction = session.beginTransaction();
			
			//get an user object
			student = session.get(Student.class, id);
			
			//commit transaction
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		return student;
	}
	
	public static void main(String[] args) throws IllegalStateException, SystemException {
		List<Student> studentList = StudentDAO.getAllStudent();
	    if (studentList != null) {
	        studentList.forEach(std -> System.out.println(std));
	    } else {
	        System.out.println("Không thể lấy danh sách sinh viên. Kiểm tra lại truy vấn hoặc cấu hình Hibernate.");
	    }
	}
}
