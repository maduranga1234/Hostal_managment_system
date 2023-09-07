package lk.ijse.orm_coursework.bo.custom;

import lk.ijse.orm_coursework.bo.SuperBo;
import lk.ijse.orm_coursework.dto.UserDto;

import java.sql.SQLException;

public interface LogingBo extends SuperBo {

    public String searchUser(String userName) throws SQLException;
}
