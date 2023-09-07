package lk.ijse.orm_coursework.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    private String reservationId;
    @Column(name="date")
    private LocalDate date;
    @Column(name ="status")
    private String status;
    @ManyToOne
    @JoinColumn(name ="Student_id",nullable = false,unique = true)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "room_id",nullable = false)
    private Room room;


}
