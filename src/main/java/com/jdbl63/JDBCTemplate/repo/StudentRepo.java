package com.jdbl63.JDBCTemplate.repo;

import com.jdbl63.JDBCTemplate.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createNewStudent(Student student){
        String insertquery = "Insert into student (studID, studName, department, marks) values(?,?,?,?)";
        return this.jdbcTemplate.update(insertquery,student.getStudId(),student.getStudName(),student.getDepartment(),student.getMarks());
    }

    public List<Student> fetchAllStudents() {
        String fetchQuery = "Select * from student";
        return this.jdbcTemplate.query(fetchQuery, (resultSet,rowNo) -> Student.builder()
                .studId(resultSet.getInt("studId"))
                .studName(resultSet.getString("studName"))
                .department(resultSet.getString("department"))
                .marks(resultSet.getDouble("marks"))
                .build());
    }

    public Student fetchStudentById(Integer id) {
        String fetchStudentQuery = "Select * from Student where studId = ?";
        return this.jdbcTemplate.queryForObject(fetchStudentQuery,(resultSet,rowNo) -> Student.builder()
                .studId(resultSet.getInt("studId"))
                .studName(resultSet.getString("studName"))
                .department(resultSet.getString("department"))
                .marks(resultSet.getDouble("marks"))
                .build(),id);
    }
    public int deleteStudent(Integer id){
        String deletequery = "Delete from student where studId = ?";
        return this.jdbcTemplate.update(deletequery,id);
    }

    public int updateStudentById(Student student) {
        String updatequery = "update student set studName = ? where studId=?";
        return this.jdbcTemplate.update(updatequery,student.getStudName(),student.getStudId());
    }

}
