package lk.ijse.orm_coursework.dao.custom;

import lk.ijse.orm_coursework.dao.SuperDao;
import org.hibernate.Session;

import java.util.List;

public interface QuaryDao extends SuperDao {
    public List<String> loadStudentIds();
    void setSession(Session session);
}
