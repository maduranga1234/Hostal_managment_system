package lk.ijse.orm_coursework.view.tdm;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class RoomTm {

    private String roomId;
    private String type;
    private String keyMoney;
    private int qty;
    private int maxNumber;

}
