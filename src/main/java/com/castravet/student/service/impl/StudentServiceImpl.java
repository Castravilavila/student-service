package com.castravet.student.service.impl;

import com.castravet.student.dto.StudentDTO;
import com.castravet.student.dto.mapper.StudentMapper;
import com.castravet.student.entity.Student;
import com.castravet.student.repository.StudentRepository;
import com.castravet.student.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public List<StudentDTO> getAllStudents() {
        log.info("fetching all students");
        List<Student> students = studentRepository.findAll();
        return studentMapper.studentsToStudentsDTO(students);
    }

    @Override
    @Transactional
    public StudentDTO getStudentById(Long id) {
        log.info("fetching student with id {}", id);
        return studentRepository.findById(id)
                .map(studentMapper::studentToStudentDTO)
                .orElseThrow(() -> new EntityNotFoundException("Student with id" + id + " not found"));
    }

    @Override
    @Transactional
    public void addStudent(StudentDTO studentDTO) {
        log.info("adding a new student  {} {}", studentDTO.getFirstName(), studentDTO.getLastName());
        Student student = studentMapper.studentDTOtoEntity(studentDTO);
        studentRepository.save(student);
        log.info("student added successfully with email: {}", student.getEmail());
    }

    @Override
    @Transactional
    public void updateStudent(Long id, StudentDTO studentDTO) {
        log.info("updating student with id {}",id);
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id" + id + " not found"));
        studentRepository.save(studentMapper.updateEntityFromDTO(existingStudent,studentDTO));
        log.info("student updated successfully with id: {}", existingStudent.getId());
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id" + id + " not found"));
        if (existingStudent != null) {
            log.info("deleting student with id: {}", id);
            studentRepository.deleteById(id);
            log.info("delete successful for student with id: {}", id);
        }
    }
}
