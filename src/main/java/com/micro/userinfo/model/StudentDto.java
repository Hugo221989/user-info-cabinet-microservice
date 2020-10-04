package com.micro.userinfo.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class StudentDto {

	private Long id;
	private String name;
	private String lastName;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date birth;
	private String aditionalInfo;
	private String diagnosisTitle;
	private String diagnosisDescription;
	
	public StudentDto() {

	}
	
	public StudentDto(Long id, String name, String lastName, Date birth, String aditionalInfo, String diagnosisTitle,
			String diagnosisDescription) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.birth = birth;
		this.aditionalInfo = aditionalInfo;
		this.diagnosisTitle = diagnosisTitle;
		this.diagnosisDescription = diagnosisDescription;
	}

	public String getDiagnosisTitle() {
		return diagnosisTitle;
	}

	public void setDiagnosisTitle(String diagnosisTitle) {
		this.diagnosisTitle = diagnosisTitle;
	}

	public String getDiagnosisDescription() {
		return diagnosisDescription;
	}

	public void setDiagnosisDescription(String diagnosisDescription) {
		this.diagnosisDescription = diagnosisDescription;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getAditionalInfo() {
		return aditionalInfo;
	}
	public void setAditionalInfo(String aditionalInfo) {
		this.aditionalInfo = aditionalInfo;
	}

}
