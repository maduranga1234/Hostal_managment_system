package lk.ijse.orm_coursework.dto;

import lk.ijse.orm_coursework.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {

    private String userName;
    private String password;


    public User toEntity(){
        User user=new User();
        user.setUserName(this.userName);
        user.setPassword(this.password);

        return user;
    }
}
