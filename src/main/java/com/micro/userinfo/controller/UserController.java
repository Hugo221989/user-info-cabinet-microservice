package com.micro.userinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.micro.userinfo.entity.Student;
import com.micro.userinfo.model.StudentListDto;
import com.micro.userinfo.model.StudentsListFilter;
import com.micro.userinfo.model.StudentsPage;
import com.micro.userinfo.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/info")
@RefreshScope
@Api(value = "VALUE", description = "Información alumnos")
public class UserController {
	
	@Autowired
	private StudentService studentService; 
	
	@GetMapping("/allList")
	@ApiOperation(value = "Obtener toda la lista de los alumnos", notes = "Este servicio web obtiene una lista de todos los alumnos.", response = StudentListDto.class, responseContainer = "StudentListDto")
	public StudentListDto getAllStudentsList() {
		return this.studentService.getAllStudentsList();
	}
	
	@GetMapping("/allPage")
	@ApiOperation(value = "Obtener todos los alumnos paginada", notes = "Este servicio web obtiene una lista de todos los alumnos paginada.", response = Page.class, responseContainer = "Page")
	public StudentsPage getAllStudentsPage(@ApiParam(name ="page", example="1", value = "1", required = false)@RequestParam(required = false, defaultValue = "0") int page,
			@ApiParam(name ="size", example="1", value = "5", required = false)@RequestParam(required = false, defaultValue = "5") int size,
			@ApiParam(name ="textToSearch", example="Ramon", value = "Ramon", required = false)@RequestParam(required = false) String textToSearch) {
		StudentsListFilter studentsListFilter = new StudentsListFilter(page, size, textToSearch);
		return this.studentService.getAllStudentsPage(studentsListFilter);
	}

	@GetMapping("/")
	@ApiOperation(value = "Obtener un alumno", notes = "Este servicio web obtiene los datos de un alumno en particular.", response = Student.class, responseContainer = "Student")
	public ResponseEntity<Student> getStudentData(@ApiParam(name ="id", example="1", value = "id", required = false)@RequestParam(required = false) Long id) {
		Student student;
		try {
			student = this.studentService.findStudentById(id);
		}catch(Exception e) {
			return new ResponseEntity("No encontrado", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(student);
	}
	
	@GetMapping("/byName")
	@ApiOperation(value = "Obtener un alumno", notes = "Este servicio web obtiene los datos de un alumno en particular.", response = Student.class, responseContainer = "Student")
	public ResponseEntity<Student> getStudentDataByName(@ApiParam(name ="name", example="1", value = "nombre", required = false)@RequestParam(required = false) String name) {
		Student student;
		try {
			student = this.studentService.findStudentByName(name);
		}catch(Exception e) {
			return new ResponseEntity("No encontrado", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(student);
	}
	
	@PostMapping("/")
	@ApiOperation(value = "Crear alumno", notes = "Este servicio web crea un nuevo alumno en el registro.", response = Student.class, responseContainer = "Student")
	public ResponseEntity<Student> createStudent(@ApiParam(value = "student", required = false)@RequestBody Student student) {
		Student studentAfter = this.studentService.saveStudent(student);
		return ResponseEntity.ok(studentAfter);
	}
	
	@PutMapping("/")
	@ApiOperation(value = "Actualizar alumno", notes = "Este servicio web actualiza los datos personales de un alumno.", response = Student.class, responseContainer = "Student")
	public ResponseEntity<Student> updateStudent(@ApiParam(value = "student", required = false)@RequestBody Student student) {
		Student studentAfter =  this.studentService.updateStudent(student);
		return ResponseEntity.ok(studentAfter);
	}
	
	@DeleteMapping("/")
	@ApiOperation(value = "Eliminar alumno", notes = "Este servicio web elimina el alumno y su diagnostico asociado.", response = Void.class, responseContainer = "Void")
	public ResponseEntity<Void> deleteStudent(@ApiParam(name ="id", example="1", value = "id", required = false)@RequestParam(required = false) Long id) {
		this.studentService.deleteStudent(id);
		return ResponseEntity.noContent().build();
	}
	
}
