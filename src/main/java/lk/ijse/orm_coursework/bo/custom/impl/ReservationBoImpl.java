package lk.ijse.orm_coursework.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.orm_coursework.bo.custom.ReservationBo;
import lk.ijse.orm_coursework.config.SessionFactoryConfig;
import lk.ijse.orm_coursework.dao.DaoFactory;
import lk.ijse.orm_coursework.dao.custom.QuaryDao;
import lk.ijse.orm_coursework.dao.custom.ReservationDao;
import lk.ijse.orm_coursework.dao.custom.RoomDao;
import lk.ijse.orm_coursework.dto.ReservationDto;
import lk.ijse.orm_coursework.dto.RoomDto;
import lk.ijse.orm_coursework.entity.Reservation;
import lk.ijse.orm_coursework.entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class ReservationBoImpl implements ReservationBo {

    ReservationDao reservationDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.RESERVATION);
    QuaryDao quaryDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.QUARY);
    RoomDao roomDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.ROOM);


    public void updateAvailableRooms(ReservationDto reservationDto) {
        String roomTypeId = reservationDto.toEntity().getRoom().getRoomId();

        int count = reservationDao.getReservationCount(roomTypeId);

        List<Object[]> list = reservationDao.getMaxPersonsPerRoom(roomTypeId);
        Object[] result = list.get(0);
        int perRoom = (Integer) result[0];
        int roomQuantity = (Integer) result[1];

        int unavailable_rooms = count / perRoom;
        int available_rooms = roomQuantity - unavailable_rooms;

        reservationDao.updateAvailableRooms(available_rooms, roomTypeId);

    }

    @Override
    public String saveReservation(ReservationDto reservationDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationDao.setSession(session);
            String reservationId = reservationDao.save(reservationDto.toEntity());
            updateAvailableRooms(reservationDto);
            transaction.commit();
            session.close();
            return reservationId;
        } catch (
                SQLException e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean updateReservation(ReservationDto reservationDto) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            reservationDao.setSession(session);
            reservationDao.update(reservationDto.toEntity());
            updateAvailableRooms(reservationDto);
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

    @Override
    public boolean deleteReservation(ReservationDto reservationDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            reservationDao.setSession(session);
            reservationDao.delete(reservationDto.toEntity());
            updateAvailableRooms(reservationDto);
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

    @Override
    public List<String> loadStudentIds() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            quaryDao.setSession(session);
            List<String> ids = quaryDao.loadStudentIds();
            transaction.commit();
            session.close();
            return ids;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("student id loading process failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<String> loadRoomTypeIds() {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomDao.setSession(session);
            List<String> ids = roomDao.loadroomtIds();
            transaction.commit();
            session.close();
            return ids;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("student id loading process failed");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ObservableList<ReservationDto> getDetailsToTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
        reservationDao.setSession(session);
        List<Reservation> reservationList = reservationDao.getDetailsToTableView();
        ObservableList<ReservationDto> reservatiObList = FXCollections.observableArrayList();

        for (Reservation r : reservationList) {
            reservatiObList.add(
                    new ReservationDto(

                            r.getReservationId(),
                            r.getDate(),
                            r.getStatus(),
                            r.getStudent().getId(),
                            r.getRoom().getRoomId()
                                       )

                    );

        }
        transaction.commit();
        session.close();
        return reservatiObList;
    } catch (Exception e) {
        transaction.rollback();
        session.close();
        System.out.println("getDetailsToTableView failed");
        System.out.println(e);
        return null;

    }

    }
}