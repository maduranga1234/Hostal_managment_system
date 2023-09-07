package lk.ijse.orm_coursework.dao.custom.impl;

import lk.ijse.orm_coursework.dao.custom.ReservationDao;
import lk.ijse.orm_coursework.entity.Reservation;
import lk.ijse.orm_coursework.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ReservationDaoImpl implements ReservationDao {


    private Session session;
     public ReservationDaoImpl(){}

    @Override
    public String save(Reservation reservation) throws SQLException {

        return String.valueOf(session.save(reservation));

    }

    @Override
    public void update(Reservation reservation) {

        session.update(reservation);

    }

    @Override
    public void delete(Reservation reservation) {

        session.delete(reservation);

    }

    @Override
    public List<Reservation> getDetailsToTableView() {
        String hql = "SELECT C FROM Reservation AS C";
        Query query = session.createQuery(hql);
        List<Reservation> reservationsList = query.list();
        return reservationsList;
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public int getReservationCount(String roomId) {
        String sql = "SELECT COUNT(r) FROM Reservation r WHERE r.room.roomId = :room_id";
        Query query = session.createQuery(sql);
        query.setParameter("room_id", roomId);
        long count = (long) query.getSingleResult();

        return (int) count;
    }

    @Override
    public List<Object[]> getMaxPersonsPerRoom(String roomId) {
        String sql = "SELECT r.max,r.qty FROM Room r WHERE r.roomId = :room_id";
        Query query = session.createQuery(sql);
        query.setParameter("room_id", roomId);
        List list = (List) query.list();
        return list;
    }

    @Override
    public void updateAvailableRooms(int available_rooms, String roomTypeId) {
        Room room = session.get(Room.class,roomTypeId);
        System.out.println(room);
        room.setAvl(available_rooms);
        System.out.println(room);
        session.merge(room);
    }
}
