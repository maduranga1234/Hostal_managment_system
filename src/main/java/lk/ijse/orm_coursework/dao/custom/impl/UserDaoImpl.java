package lk.ijse.orm_coursework.dao.custom.impl;

import lk.ijse.orm_coursework.dao.custom.UserDao;

import lk.ijse.orm_coursework.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private Session session;
    public UserDaoImpl(){

    }

    @Override
    public String save(User user) throws SQLException {
        return String.valueOf(session.save(user));
    }



    @Override
    public void update(User user) {
        session.update(user);

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public String checkPassword(String username) {

    String sql = "SELECT password FROM user WHERE user_name= :un ";
    Query query = session.createNativeQuery(sql);
    query.setParameter("un", username);
    String pass = (String) query.getSingleResult();
    return pass;

    }
}
