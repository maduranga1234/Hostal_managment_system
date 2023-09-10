package lk.ijse.orm_coursework.dao.custom;

import lk.ijse.orm_coursework.dao.CrudDao;
import lk.ijse.orm_coursework.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface StudentDao extends CrudDao<Student,String> {
    void setSession(Session session);
    public List<Student> getDetailsToTableView();
    public Student getStudentDetail(String studentId);

}
