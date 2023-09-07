package lk.ijse.orm_coursework.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.orm_coursework.bo.custom.RoomBo;
import lk.ijse.orm_coursework.config.SessionFactoryConfig;
import lk.ijse.orm_coursework.dao.DaoFactory;
import lk.ijse.orm_coursework.dao.custom.RoomDao;
import lk.ijse.orm_coursework.dto.RoomDto;
import lk.ijse.orm_coursework.dto.StudentDto;
import lk.ijse.orm_coursework.entity.Room;
import lk.ijse.orm_coursework.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class RoomBoImpl implements RoomBo {

    RoomDao roomDao=DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.ROOM);


    @Override
    public String saveRoom(RoomDto roomDto) {





        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            roomDao.setSession(session);
            Room room=roomDto.toEntity();
            room.setAvl(roomDto.getQty());
            String roomId=roomDao.save(room);
            transaction.commit();
            session.close();
            return roomId;

        } catch (SQLException e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return null;
        }


    }


    @Override
    public boolean updateRoom(RoomDto roomDto) {

        Session session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();


        try {
            roomDao.setSession(session);
            roomDao.update(roomDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRoom(RoomDto roomDto) {

        Session session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            roomDao.setSession(session);
            roomDao.delete(roomDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }


    }




    @Override
    public ObservableList<RoomDto> getDetailsToTableView() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomDao.setSession(session);
            List<Room> roomtList = roomDao.getDetailsToTableView();
            ObservableList<RoomDto> roomObList = FXCollections.observableArrayList();
            for (Room r : roomtList) {
                roomObList.add(
                        new RoomDto(

                               r.getRoomId(),
                                r.getType(),
                                r.getKeyMoney(),
                                r.getQty(),
                                r.getMax()

                        )
                );
            }
            transaction.commit();
            session.close();
            return roomObList;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            System.out.println("getDetailsToTableView failed");
            System.out.println(e);
            return null;

        }
    }
}
