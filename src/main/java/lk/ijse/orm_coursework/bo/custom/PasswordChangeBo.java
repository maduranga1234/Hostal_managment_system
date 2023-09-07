package lk.ijse.orm_coursework.bo.custom;

import lk.ijse.orm_coursework.bo.SuperBo;
import lk.ijse.orm_coursework.dto.UserDto;

public interface PasswordChangeBo extends SuperBo {

    boolean updateUser(UserDto userDto);
}
