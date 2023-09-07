package lk.ijse.orm_coursework.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.orm_coursework.bo.SuperBo;
import lk.ijse.orm_coursework.dto.RoomDto;
import lk.ijse.orm_coursework.dto.StudentDto;

public interface RoomBo extends SuperBo {

    String saveRoom(RoomDto roomDto);



    boolean updateRoom(RoomDto roomDto);

    boolean deleteRoom(RoomDto roomDto);
    public ObservableList<RoomDto> getDetailsToTableView();
}
