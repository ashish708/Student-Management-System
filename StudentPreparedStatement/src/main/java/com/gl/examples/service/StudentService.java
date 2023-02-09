package com.gl.examples.service;

import com.gl.examples.dto.Student;

public interface StudentService {
	public Student addStudent(Student student);

	public void getAllStudent();

	public Student getStudentById(String studentId);

	public Student deleteStudent(String studentId);

	public Student updateStudent(String studentId, String studentName, String studentAddress, String studentEmail);

}
