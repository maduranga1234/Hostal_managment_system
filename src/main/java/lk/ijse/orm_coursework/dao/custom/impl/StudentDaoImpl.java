package lk.ijse.orm_coursework.dao.custom.impl;

import lk.ijse.orm_coursework.dao.custom.StudentDao;
import lk.ijse.orm_coursework.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private Session session;

    public  StudentDaoImpl() {
    }


    @Override
    public String save(Student student) throws SQLException {
        return String.valueOf(session.save(student));


    }


    @Override
    public void update(Student student) {

         session.update(student);

    }

    @Override
    public void delete(Student student) {
        session.delete(student);
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public List<Student> getDetailsToTableView() {
        String hql = "SELECT C FROM Student AS C";
        Query query = session.createQuery(hql);
        List<Student> studentList = query.list();
        return studentList;
    }

    @Override
    public Student getStudentDetail(String studentId) {
        String sql = "SELECT s FROM Student s WHERE s.id = :student_id";

        Query query = session.createQuery(sql);
        query.setParameter("student_id", studentId);
        Student student= (Student) query.getSingleResult();

        return student;
    }
}
