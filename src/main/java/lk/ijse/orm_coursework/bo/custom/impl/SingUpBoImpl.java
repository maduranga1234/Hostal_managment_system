package lk.ijse.orm_coursework.bo.custom.impl;

import lk.ijse.orm_coursework.bo.custom.SingUpBo;
import lk.ijse.orm_coursework.config.SessionFactoryConfig;
import lk.ijse.orm_coursework.dao.DaoFactory;
import lk.ijse.orm_coursework.dao.custom.UserDao;
import lk.ijse.orm_coursework.dto.UserDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;

public class SingUpBoImpl implements SingUpBo {

    UserDao userDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.USER);


    @Override
    public String saveUser(UserDto userDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userDao.setSession(session);
            String userId = userDao.save(userDto.toEntity());
            transaction.commit();
            session.close();

            return userId;
        } catch (SQLException e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return null;
        }
    }


}
