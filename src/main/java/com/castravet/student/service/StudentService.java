package com.castravet.student.service;

import com.castravet.student.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    void addStudent(StudentDTO studentDTO);

    void updateStudent(Long id, StudentDTO studentDTO);

    void deleteStudent(Long id);
}
