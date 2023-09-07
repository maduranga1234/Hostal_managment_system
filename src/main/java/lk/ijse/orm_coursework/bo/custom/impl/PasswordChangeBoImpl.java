package lk.ijse.orm_coursework.bo.custom.impl;

import lk.ijse.orm_coursework.bo.custom.PasswordChangeBo;
import lk.ijse.orm_coursework.config.SessionFactoryConfig;
import lk.ijse.orm_coursework.dao.DaoFactory;
import lk.ijse.orm_coursework.dao.custom.UserDao;
import lk.ijse.orm_coursework.dto.UserDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PasswordChangeBoImpl implements PasswordChangeBo {

    UserDao userDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.USER);

    @Override
    public boolean updateUser(UserDto userDto) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            userDao.setSession(session);
            userDao.update(userDto.toEntity());
            transaction.commit();
            session.close();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }


}
