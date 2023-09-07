package lk.ijse.orm_coursework.bo.custom;

import lk.ijse.orm_coursework.bo.SuperBo;
import lk.ijse.orm_coursework.dto.StudentDto;
import lk.ijse.orm_coursework.dto.UserDto;

public interface SingUpBo extends SuperBo {

    String saveUser(UserDto userDto);


}
