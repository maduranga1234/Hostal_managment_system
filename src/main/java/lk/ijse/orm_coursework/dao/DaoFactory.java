package lk.ijse.orm_coursework.dao;

import lk.ijse.orm_coursework.dao.custom.impl.*;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() {

    }

    public static DaoFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DaoFactory() : daoFactory;
    }

    public enum DaoType {
        ROOM,STUDENT,USER,RESERVATION,QUARY
    }

    public <T extends SuperDao> T getDao(DaoType res) {
        switch (res) {

            case ROOM:
                return (T) new RoomDaoImpl();

            case STUDENT:
                return (T)new StudentDaoImpl();

            case USER:
                return (T) new UserDaoImpl();

            case RESERVATION:
                return (T) new ReservationDaoImpl();

            case QUARY:
                return (T) new QuaryDaoImpl();

            default:
                return null;
        }
    }
}
