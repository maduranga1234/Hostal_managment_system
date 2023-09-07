package lk.ijse.orm_coursework.dto;

import jakarta.persistence.Column;
import lk.ijse.orm_coursework.entity.Student;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class StudentDto {

    private String id;
    private String name;
    private String address;
    private String contact;
    private LocalDate dob;
    private String gender;

    public Student toEntity(){

        Student student=new Student();
        student.setId(this.id);
        student.setName(this.name);
        student.setAddress(this.address);
        student.setContact(this.contact);
        student.setDob(this.dob);
        student.setGender(this.gender);

        return student;

    }


}
