package lk.ijse.orm_coursework.dto;

import jakarta.persistence.Column;
import lk.ijse.orm_coursework.entity.Room;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class RoomDto {

    private String roomId;
    private String type;
    private String keyMoney;
    private int qty;
    private int maxNumber;

    public Room toEntity(){
        Room room=new Room();
        room.setRoomId(this.roomId);
        room.setType(this.type);
        room.setKeyMoney(this.keyMoney);
        room.setQty(this.qty);
        room.setMax(this.maxNumber);

        return room;
    }
}
