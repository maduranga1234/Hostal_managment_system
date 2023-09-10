package lk.ijse.orm_coursework.dao.custom;

import lk.ijse.orm_coursework.entity.Room;
import lk.ijse.orm_coursework.dao.CrudDao;
import org.hibernate.Session;

import java.util.List;

public interface RoomDao extends CrudDao<Room,String> {


   void setSession(Session session);
   public List<Room> getDetailsToTableView();


   List<String> loadroomtIds();

    int getAvlRooms(String roomId);
}
