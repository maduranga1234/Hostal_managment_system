package lk.ijse.orm_coursework.bo;

import lk.ijse.orm_coursework.bo.custom.impl.*;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory(){}


    public static BoFactory getBoFactory() {

        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BoType{
        ROOM_BO,STUDENT_BO,SING_UP,PASSWORD_CHANGE_BO,LOGING_BO,RESERVATION_BO
    }

    public <T extends SuperBo> T getBo(BoType boType){
        switch (boType){
            case ROOM_BO:
                return (T) new RoomBoImpl();

            case STUDENT_BO:
                return (T) new StudentBoImpl();

            case SING_UP:
                return (T) new SingUpBoImpl();

            case PASSWORD_CHANGE_BO:
                return (T) new PasswordChangeBoImpl();

            case LOGING_BO:
                return (T) new LogingBoImpl();

            case RESERVATION_BO:
                return (T) new ReservationBoImpl();

            default:
                return null;
        }

    }
}
