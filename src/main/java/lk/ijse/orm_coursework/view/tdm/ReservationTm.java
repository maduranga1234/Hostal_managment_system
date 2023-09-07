package lk.ijse.orm_coursework.view.tdm;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ReservationTm {


    private String reservationId;
    private LocalDate date;
    private String status;
    private String studentId;
    private String roomId;
}
