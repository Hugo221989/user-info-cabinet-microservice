package com.micro.userinfo.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.micro.userinfo.entity.Student;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student ,Long>{
	
	//final String SELECT = "SELECT new com.micro.userinfo.model.StudentDto(s.id, s.name, s.lastName, s.birth, s.aditionalInfo, " +
//			"d.title, d.description) " +
//	 		"FROM Student s " +
//	 		"LEFT JOIN Diagnosis d ON d.student.id = s.id ";
	final String SELECT = "select s from Student s ";
	final String WHERE = "where ";
	final String BUSCADOR = "s.name LIKE CONCAT('%',?1,'%') or s.lastName LIKE CONCAT('%',?1,'%')";
	
	@Query("select s from Student s where s.name LIKE %?1%")
	Student findStudentByName(String name);
	
	//@Cacheable("all_students")
	@Query(SELECT)
	public Page<Student> findAllProducts(Pageable pageable);
	
	@Cacheable("all_students_filter")
	@Query(SELECT+WHERE+BUSCADOR)
	public Page<Student> findAllProductsFilter(Pageable pageable, String textToSearch);
}
