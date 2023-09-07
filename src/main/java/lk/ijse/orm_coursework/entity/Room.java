package lk.ijse.orm_coursework.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "room_id")
    private String roomId;
    @Column(name="type")
    private String type;
    @Column(name = "key_money")
    private String keyMoney;
    @Column(name = "qty")
    private int qty;
    @Column(name="available_room")
    private int avl;
    @Column(name="max_number")
    private int max;

    @OneToMany(cascade =CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "room")
    private List<Reservation>reservations=new ArrayList<>();

    public Room(String roomId, String type, String keyMoney, int qty) {
        this.roomId = roomId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }

    public Room() {
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(String keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQty() {
        return qty;
    }

    public int getAvl() {
        return avl;
    }

    public void setAvl(int avl) {
        this.avl = avl;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", type='" + type + '\'' +
                ", keyMoney='" + keyMoney + '\'' +
                ", qty=" + qty +
                ", avl=" + avl +
                ", max=" + max +
                '}';
    }
}
