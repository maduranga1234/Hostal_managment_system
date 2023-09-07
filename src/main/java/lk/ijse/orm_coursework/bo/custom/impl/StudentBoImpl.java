package lk.ijse.orm_coursework.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.orm_coursework.bo.custom.StudentBo;
import lk.ijse.orm_coursework.config.SessionFactoryConfig;
import lk.ijse.orm_coursework.dao.DaoFactory;
import lk.ijse.orm_coursework.dao.custom.StudentDao;
import lk.ijse.orm_coursework.dto.RoomDto;
import lk.ijse.orm_coursework.dto.StudentDto;
import lk.ijse.orm_coursework.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class StudentBoImpl implements StudentBo {

    StudentDao studentDao= DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.STUDENT);


    @Override
    public String saveStudent(StudentDto studentDto) {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();


        try {
            studentDao.setSession(session);
            String roomId= studentDao.save(studentDto.toEntity());
            transaction.commit();
            session.close();

            return roomId;
        }  catch (SQLException e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public ObservableList<StudentDto> getDetailsToTableView() {

            Session session = SessionFactoryConfig.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            try {
                studentDao.setSession(session);
                List<Student> studentList = studentDao.getDetailsToTableView();
                ObservableList<StudentDto> studentObList = FXCollections.observableArrayList();
                for (Student s : studentList) {
                    studentObList.add(
                            new StudentDto(
                                    s.getId(),
                                    s.getName(),
                                    s.getAddress(),
                                    s.getContact(),
                                    s.getDob(),
                                    s.getGender()
                            )
                    );
                }
                transaction.commit();
                session.close();
                return studentObList;
            } catch (Exception e) {
                transaction.rollback();
                session.close();
                System.out.println("getDetailsToTableView failed");
                System.out.println(e);
                return null;

            }
        }


    @Override
    public boolean updateStudent(StudentDto studentDto) {

        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();


        try {
            studentDao.setSession(session);
            studentDao.update(studentDto.toEntity());
            transaction.commit();
            session.close();
            return true;

        }  catch (Exception e) {
        transaction.rollback();
        session.close();
        e.printStackTrace();
        return false;
    }



    }

    @Override
    public boolean deleteStudent(StudentDto studentDto) {

        Session session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        try {
            studentDao.setSession(session);
            studentDao.delete(studentDto.toEntity());
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return true;
        }

        return false;
    }
}
