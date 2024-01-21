package com.jdbl63.JDBCTemplate.service;

import com.jdbl63.JDBCTemplate.model.Student;
import com.jdbl63.JDBCTemplate.repo.StudentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public int createNewStudent(Student student){
        log.info("Student request for:"+student.toString());
        return studentRepo.createNewStudent(student);
    }

    public List<Student> fetchAllStudents(){
        List<Student> studentList = studentRepo.fetchAllStudents();
        if(studentList.isEmpty()) {
            log.info("Table is empty");
            throw new RuntimeException("No Students Exist");
        }
        return studentList;
    }

    public Student fetchStudentById(Integer id) {
        Student student = studentRepo.fetchStudentById(id);
        if(student == null) {
            log.error("Student do not exists!!");
            throw new RuntimeException("No Student Exist");
        }
        return student;
    }
    public int deleteStudent(Integer id){
        log.info("Student Delete request for:"+ id);
        return studentRepo.deleteStudent(id);
    }

    public int updateStudentById(Student student){
        log.info("Student request for:"+student.toString());
        return studentRepo.updateStudentById(student);
    }

}
