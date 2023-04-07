package lk.ijse.hms.bo;

import lk.ijse.hms.dao.StudentDAO;
import lk.ijse.hms.dto.StudentDTO;
import lk.ijse.hms.entity.Student;

import java.util.ArrayList;

public class StudentBO {
    public static Boolean addStudent(StudentDTO studentDTO) {
        Student student = new Student(
                studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContact_no(),
                studentDTO.getDob(),
                studentDTO.getGender());

        return StudentDAO.add(student);
    }

    public static Boolean deleteStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());

        return StudentDAO.delete(student);
    }

    public static ArrayList<StudentDTO> getStudentData() {
        ArrayList<StudentDTO> StudentDTOs = new ArrayList<>();
        ArrayList<Student> studentData = StudentDAO.getData();

        for (Student std : studentData) {
            StudentDTOs.add(new StudentDTO(std.getId(),
                    std.getName(), std.getAddress(),
                    std.getContact_no(),
                    std.getDob(),
                    std.getGender()));
        }
        return StudentDTOs;
    }

}
