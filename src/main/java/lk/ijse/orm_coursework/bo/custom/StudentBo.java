package lk.ijse.orm_coursework.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.orm_coursework.bo.SuperBo;
import lk.ijse.orm_coursework.dto.RoomDto;
import lk.ijse.orm_coursework.dto.StudentDto;
import lk.ijse.orm_coursework.entity.Student;

import java.util.List;

public interface StudentBo extends SuperBo {


    String saveStudent(StudentDto studentDto);


    public ObservableList<StudentDto> getDetailsToTableView();

    boolean updateStudent(StudentDto studentDto);

    boolean deleteStudent(StudentDto studentDto);
}
