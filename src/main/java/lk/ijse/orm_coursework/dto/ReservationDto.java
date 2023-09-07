package lk.ijse.orm_coursework.dto;


import lk.ijse.orm_coursework.entity.Reservation;
import lk.ijse.orm_coursework.entity.Room;
import lk.ijse.orm_coursework.entity.Student;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ReservationDto {

    private String reservationId;
    private LocalDate date;
    private String status;
    private String studentId;
    private String roomId;



    public Reservation toEntity(){
        Reservation reservation=new Reservation();
        reservation.setReservationId(this.reservationId);
        reservation.setDate(this.date);
        reservation.setStatus(this.status);
        Student student = new Student();
        student.setId(this.studentId);
        reservation.setStudent(student);

        Room room = new Room();
        room.setRoomId(this.roomId);
        reservation.setRoom(room);
        return reservation;
    }


}
