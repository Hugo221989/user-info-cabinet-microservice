package com.micro.userinfo.service;

import com.micro.userinfo.entity.Student;
import com.micro.userinfo.model.StudentListDto;
import com.micro.userinfo.model.StudentsListFilter;
import com.micro.userinfo.model.StudentsPage;

public interface StudentService {
	
	StudentListDto getAllStudentsList();
	StudentsPage getAllStudentsPage(StudentsListFilter studentsListFilter);
	Student findStudentById(long id);
	Student findStudentByName(String name);
	Student saveStudent(Student student);
	Student updateStudent(Student student);
	void deleteStudent(Long id);

}
