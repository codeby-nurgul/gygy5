package com.turkcell.library_application.service;

import com.turkcell.library_application.dto.CreateStudentRequest;
import com.turkcell.library_application.dto.CreatedStudentResponse;
import com.turkcell.library_application.dto.ListStudentResponse;
import com.turkcell.library_application.dto.UpdateStudentRequest;
import com.turkcell.library_application.entity.Student;
import com.turkcell.library_application.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreatedStudentResponse create(CreateStudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setEmail(request.getEmail());
        student.setDepartment(request.getDepartment());

        student = studentRepository.save(student);
        return new CreatedStudentResponse(student.getId(), student.getName() + " " + student.getSurname());
    }

    public List<ListStudentResponse> getAll() {
        List<Student> students = studentRepository.findAll();
        List<ListStudentResponse> responseList = new ArrayList<>();
        for (Student student : students) {
            responseList.add(new ListStudentResponse(student.getId(), student.getName(), student.getSurname(), student.getEmail()));
        }
        return responseList;
    }

    public void update(int id, UpdateStudentRequest request) {
        Student student = studentRepository.findById(id).orElseThrow();
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setEmail(request.getEmail());
        student.setDepartment(request.getDepartment());
        studentRepository.save(student);
    }

    public void delete(int id) {
        Student student = studentRepository.findById(id).orElseThrow();
        studentRepository.delete(student);
    }
}
