package lk.ijse.orm_coursework.dao.custom;

import lk.ijse.orm_coursework.dao.CrudDao;
import lk.ijse.orm_coursework.entity.User;
import org.hibernate.Session;

public interface UserDao extends CrudDao<User,String> {

    void setSession(Session session);

    public String checkPassword(String username);

}
