package lk.ijse.orm_coursework.dao.custom;

import lk.ijse.orm_coursework.dao.SuperDao;
import lk.ijse.orm_coursework.projection.CustomProjection;
import org.hibernate.Session;

import java.util.List;

public interface QuaryDao extends SuperDao {
    public List<String> loadStudentIds();
    void setSession(Session session);

    List<CustomProjection> getDetailsOfStudentsWithoutKeyMoney();
}
