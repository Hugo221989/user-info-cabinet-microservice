package com.micro.userinfo.model;

import java.util.List;

import com.micro.userinfo.entity.Student;

public class StudentListDto {
	List<Student> studentList;

	public StudentListDto() {
		
	}

	public StudentListDto(List<Student> studentList) {
		super();
		this.studentList = studentList;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
	
}
