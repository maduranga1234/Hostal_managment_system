package lk.ijse.orm_coursework.view.tdm;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class StudentTm {

    private String id;
    private String name;
    private String address;
    private String contact;
    private LocalDate dob;
    private String gender;
}
