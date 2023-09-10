package lk.ijse.orm_coursework.bo.custom.impl;

import lk.ijse.orm_coursework.bo.custom.LogingBo;
import lk.ijse.orm_coursework.config.SessionFactoryConfig;
import lk.ijse.orm_coursework.dao.DaoFactory;
import lk.ijse.orm_coursework.dao.custom.UserDao;
import lk.ijse.orm_coursework.dto.UserDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;


public class LogingBoImpl implements LogingBo {

    UserDao userDao= DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.USER);

    @Override
    public String searchUser(String userName) throws SQLException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {

            userDao.setSession(session);
            String id=  userDao.checkPassword(userName);
            transaction.commit();
            session.close();

            return id;

        }catch (Exception e) {
            transaction.rollback();
            session.close();
            return null;
        }

    }
}
