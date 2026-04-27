package com.turkcell.library_application.controller;

import com.turkcell.library_application.dto.CreateStudentRequest;
import com.turkcell.library_application.dto.CreatedStudentResponse;
import com.turkcell.library_application.dto.ListStudentResponse;
import com.turkcell.library_application.dto.UpdateStudentRequest;
import com.turkcell.library_application.service.StudentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentsController {
    private final StudentServiceImpl studentService;

    public StudentsController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public CreatedStudentResponse create(@RequestBody CreateStudentRequest request) {
        return studentService.create(request);
    }

    @GetMapping
    public List<ListStudentResponse> getAll() {
        return studentService.getAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody UpdateStudentRequest request) {
        studentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        studentService.delete(id);
    }
}
