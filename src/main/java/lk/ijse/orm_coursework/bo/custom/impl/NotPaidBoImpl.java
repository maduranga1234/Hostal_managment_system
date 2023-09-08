package lk.ijse.orm_coursework.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.orm_coursework.bo.custom.NotPaidBo;
import lk.ijse.orm_coursework.config.SessionFactoryConfig;
import lk.ijse.orm_coursework.dao.DaoFactory;
import lk.ijse.orm_coursework.dao.custom.QuaryDao;
import lk.ijse.orm_coursework.projection.CustomProjection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class NotPaidBoImpl implements NotPaidBo {


    QuaryDao quaryDao= DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.QUARY);


    @Override
    public ObservableList<CustomProjection> getDetailsToTableView() {


        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            quaryDao.setSession(session);
            List<CustomProjection> customList = quaryDao.getDetailsOfStudentsWithoutKeyMoney();
            ObservableList<CustomProjection> customObList = FXCollections.observableArrayList(customList);

            transaction.commit();
            session.close();
            return customObList;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("getDetailsOfStudentsWithoutKeyMoney failed");
            System.out.println(e);
            return null;
        }

    }
}
