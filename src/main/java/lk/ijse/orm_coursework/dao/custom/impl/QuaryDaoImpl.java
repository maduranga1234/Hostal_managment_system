package lk.ijse.orm_coursework.dao.custom.impl;

import lk.ijse.orm_coursework.dao.SuperDao;
import lk.ijse.orm_coursework.dao.custom.QuaryDao;
import lk.ijse.orm_coursework.projection.CustomProjection;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QuaryDaoImpl implements QuaryDao {
    private Session session;
    public  QuaryDaoImpl(){}

    @Override
    public List<String> loadStudentIds() {
        String sql = "SELECT student.Student_id " +
                "FROM student " +
                "LEFT JOIN reservation " +
                "ON student.Student_id= reservation.Student_id " +
                "WHERE reservation.Student_id IS NULL";
        Query query = session.createNativeQuery(sql);
        List list = query.list();
        //session.close();
        return list;
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public List<CustomProjection> getDetailsOfStudentsWithoutKeyMoney() {
        String hql = "select new lk.ijse.orm_coursework.projection.CustomProjection(" +
                "S.id,S.name,S.contact,RO.roomId,RO.keyMoney) " +
                "FROM Student AS S " +
                "INNER JOIN Reservation AS RE " +
                "ON S.id=RE.student.id " +
                "INNER JOIN Room AS RO " +
                "ON RE.room.roomId=RO.roomId " +
                "WHERE RE.status= :status ";
        Query query = session.createQuery(hql);
        query.setParameter("status", "NOT PAID");
        List<CustomProjection> studentWithoutKeyMoneyList = query.list();
        return studentWithoutKeyMoneyList;
    }
}
