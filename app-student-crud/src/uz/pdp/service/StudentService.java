package uz.pdp.service;

import uz.pdp.payload.StudentDTO;

import java.util.List;

public interface StudentService {
    //1
    List<StudentDTO> getByGroupId(Integer groupId);

    //2
    StudentDTO getById(Integer id);

    //3
    StudentDTO add(StudentDTO studentDTO);

    //4
    StudentDTO myEdit(Integer id,StudentDTO studentDTO);

    //5
    boolean myRemove(Integer id);

    //6
    String myRead(Integer id);

    //7
    boolean serialize();

    //8
    boolean desialize();

}
