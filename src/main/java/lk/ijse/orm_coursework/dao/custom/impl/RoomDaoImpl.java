package lk.ijse.orm_coursework.dao.custom.impl;

import lk.ijse.orm_coursework.entity.Room;
import lk.ijse.orm_coursework.dao.custom.RoomDao;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class RoomDaoImpl implements RoomDao {

    private Session session;

    public RoomDaoImpl(){}

    @Override
    public void setSession(Session session) {
    this.session=session;
    }

    @Override
    public List<Room> getDetailsToTableView() {
        String hql = "SELECT C FROM Room AS C";
        Query query = session.createQuery(hql);
        List<Room> roomList = query.list();
        return roomList;
    }

    @Override
    public List<String> loadroomtIds() {
        String sql = "SELECT room_id FROM room";
        Query query = session.createNativeQuery(sql);
        List list = query.list();
        //session.close();
    return list;
    }

    @Override
    public int getAvlRooms(String roomId) {
        String sql="SELECT available_room FROM room WHERE room_id=:id ";
        Query query = session.createNativeQuery(sql);
        query.setParameter("id",roomId);
        int count= (int) query.getSingleResult();
        return count;
    }


    @Override
    public String save(Room room) throws SQLException {
        return String.valueOf(session.save(room));
    }




    @Override
    public void  update(Room room) {
      session.update(room);
    }

    @Override
    public void delete(Room room) {
      session.delete(room);
    }


}
