package com.castravet.student.dto.mapper;

import com.castravet.student.dto.StudentDTO;
import com.castravet.student.entity.Student;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//TODO: SA CITESC DESPRE MAPSTRUCT
@Data
@Component
public class StudentMapper {
    public StudentDTO studentToStudentDTO(Student student){

        return StudentDTO.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .titleBachelor(student.getTitleBachelor())
                .build();
    }

    public Student studentDTOtoEntity(StudentDTO studentDTO){
        Student studentEntity = new Student();
        studentEntity.setFirstName(studentDTO.getFirstName());
        studentEntity.setLastName(studentDTO.getLastName());
        studentEntity.setEmail(studentDTO.getEmail());
        studentEntity.setTitleBachelor(studentDTO.getTitleBachelor());
        return studentEntity;
    }

    public Student updateEntityFromDTO(Student student, StudentDTO studentDTO){
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setTitleBachelor(studentDTO.getTitleBachelor());
        return student;
    }

    public List<StudentDTO> studentsToStudentsDTO(List<Student> students){
        return students.stream()
                .map(s->StudentDTO.builder()
                        .firstName(s.getFirstName())
                        .lastName(s.getLastName())
                        .email(s.getEmail())
                        .titleBachelor(s.getTitleBachelor())
                        .build())
                .collect(Collectors.toList());
    }
}
