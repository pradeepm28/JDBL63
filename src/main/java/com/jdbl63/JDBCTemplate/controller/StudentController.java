package com.jdbl63.JDBCTemplate.controller;

import com.jdbl63.JDBCTemplate.model.Student;
import com.jdbl63.JDBCTemplate.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<String> addNewStudent(@RequestBody Student student) {
        if (studentService.createNewStudent(student) == 1)
            return new ResponseEntity<>(String.format("New Student with Name:%s is Added", student.getStudName()), HttpStatus.CREATED);
        return new ResponseEntity<>("Insertion Failed", HttpStatus.BAD_REQUEST);

    }

    @GetMapping
    public ResponseEntity<List<Student>> fetchAllStudents() {
        try {
            return new ResponseEntity<>(studentService.fetchAllStudents(), HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error("Exception occured: ", e.getMessage());
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> fetchStudentById(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(studentService.fetchStudentById(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error("Exception occured: ", e.getMessage());

        }
        return null;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Integer id) {
        if (studentService.deleteStudent(id) == 1)
            return new ResponseEntity<>(String.format("Student with id:%d is deleted", id), HttpStatus.CREATED);
        return new ResponseEntity<>("Insertion Failed", HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<String> updateStudentById(@RequestBody Student student) {
        if (studentService.updateStudentById(student) == 1)
            return new ResponseEntity<>(String.format("Student with Id:%d is updated", student.getStudId()), HttpStatus.OK);
        return new ResponseEntity<>("Update Failed", HttpStatus.BAD_REQUEST);

    }
}
