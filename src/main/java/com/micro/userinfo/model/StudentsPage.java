package com.micro.userinfo.model;

import org.springframework.data.domain.Page;

import com.micro.userinfo.entity.Student;

public class StudentsPage {
	Page<Student> studentsPage;

	public StudentsPage() {

	}

	public StudentsPage(Page<Student> studentsPage) {
		super();
		this.studentsPage = studentsPage;
	}

	public Page<Student> getStudentsPage() {
		return studentsPage;
	}

	public void setStudentsPage(Page<Student> studentsPage) {
		this.studentsPage = studentsPage;
	}
	
}
