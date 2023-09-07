package lk.ijse.orm_coursework.dao.custom;

import lk.ijse.orm_coursework.dao.CrudDao;
import lk.ijse.orm_coursework.entity.Reservation;
import lk.ijse.orm_coursework.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public interface ReservationDao extends CrudDao<Reservation,String> {

    void setSession(Session session);

    int getReservationCount(String roomTypeId);

    List<Object[]> getMaxPersonsPerRoom(String roomTypeId);

    void updateAvailableRooms(int available_rooms, String roomTypeId);

    public List<Reservation> getDetailsToTableView();

}
