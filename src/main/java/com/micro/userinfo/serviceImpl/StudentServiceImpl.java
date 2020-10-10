package com.micro.userinfo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.micro.userinfo.entity.Diagnosis;
import com.micro.userinfo.entity.Student;
import com.micro.userinfo.exception.NotFoundException;
import com.micro.userinfo.model.StudentListDto;
import com.micro.userinfo.model.StudentsListFilter;
import com.micro.userinfo.model.StudentsPage;
import com.micro.userinfo.repository.StudentRepository;
import com.micro.userinfo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public StudentListDto getAllStudentsList() {
		List<Student> studentsList = this.studentRepository.findAll();
		StudentListDto StudentListDto = new StudentListDto(studentsList);
		return StudentListDto;
	}
	
	@Override
	public StudentsPage getAllStudentsPage(StudentsListFilter studentsListFilter) {
		PageRequest pageable = PageRequest.of(studentsListFilter.getPage(), studentsListFilter.getSize());
		Page<Student> studentsPage;
		if(null != studentsListFilter.getTextToSearch() && !"".equals(studentsListFilter.getTextToSearch()) && !"null".equals(studentsListFilter.getTextToSearch()))
			studentsPage = this.studentRepository.findAllProductsFilter(pageable, studentsListFilter.getTextToSearch());
		else
			studentsPage = this.studentRepository.findAllProducts(pageable);
		StudentsPage studentsPageWrap = new StudentsPage(studentsPage);
		return studentsPageWrap;
	}

	@Override
	public Student findStudentById(long id) {
		return this.studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
	}
	
	@Override
	public Student findStudentByName(String name) {
		return this.studentRepository.findStudentByName(name);
	}

	@Override
	public Student saveStudent(Student student) {
		Student newStudent = new Student();
		if(null != student) {
			newStudent = fillStudentInfo(newStudent, student);
		}
		return this.studentRepository.save(newStudent);
	}

	@Override
	public Student updateStudent(Student student) {
		Student newStudent = this.findStudentById(student.getId());
		if(null != student) {
			newStudent = fillStudentInfo(newStudent, student);
		}
		return this.studentRepository.save(newStudent);
	}
	
	private Student fillStudentInfo(Student newStudent, Student student) {
		newStudent.setName(student.getName());
		newStudent.setLastName(student.getLastName());
		newStudent.setBirth(student.getBirth());
		newStudent.setAditionalInfo(student.getAditionalInfo());
		
		Diagnosis newDiagnosis = newStudent.getDiagnosis();
		if(null == newDiagnosis)newDiagnosis = new Diagnosis();
		newDiagnosis.setTitle(student.getDiagnosis().getTitle());
		newDiagnosis.setDescription(student.getDiagnosis().getDescription());
		newDiagnosis.setStudent(newStudent);
		newStudent.setDiagnosis(newDiagnosis);
		return newStudent;
	}
	
	@Override
	public void deleteStudent(Long id) {
		this.studentRepository.deleteById(id);
	}

}
