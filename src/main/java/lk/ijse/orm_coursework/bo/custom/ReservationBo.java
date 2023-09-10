package lk.ijse.orm_coursework.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.orm_coursework.bo.SuperBo;
import lk.ijse.orm_coursework.dto.ReservationDto;
import lk.ijse.orm_coursework.dto.RoomDto;
import lk.ijse.orm_coursework.dto.StudentDto;
import lk.ijse.orm_coursework.entity.Student;

import java.util.List;

public interface ReservationBo extends SuperBo {

    String saveReservation(ReservationDto reservationDto);



    boolean updateReservation(ReservationDto reservationDto);

    boolean deleteReservation(ReservationDto reservationDto);

    List<String> loadStudentIds();

    List<String> loadRoomTypeIds();

    public ObservableList<ReservationDto> getDetailsToTableView();
    public StudentDto getStudentDetail(String studentId);

    int getAvlRooms(String roomId);

}


