package com.gl.examples.serviceImpl;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gl.examples.dto.Student;
import com.gl.examples.repo.StudentRepo;
import com.gl.examples.service.StudentService;

public class StudentServiceImpl implements StudentService {


	Student student = new Student();
	StudentRepo studentRepo;

	@Override
	public Student addStudent(Student student) {
		try {
			//String query = "INSERT INTO student VALUES (?, ?, ?, ?)";
			Connection conn = studentRepo.getConnection();
			CallableStatement ps = conn.prepareCall("{call insert_student(?,?,?,?)}");
			ps.setString(1, student.getStudentId());
			ps.setString(2, student.getStudentName());
			ps.setString(3, student.getStudentAddress());
			ps.setString(4, student.getStudentEmail());

			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("student added successfully...!");
			} else
				System.out.println("Failed to add a record........!");
			ps.close();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void getAllStudent() {
		try {
			//String query = "select * from student";
			Connection conn = studentRepo.getConnection();

			CallableStatement ps = conn.prepareCall("{CALL get_all_student}");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String contactId = rs.getString(1);
				student.setStudentId(contactId);
				String name = rs.getString(2);
				student.setStudentName(name);

				String address = rs.getString(3);
				student.setStudentAddress(address);
				String email = rs.getString(4);
				student.setStudentEmail(email);

				String newline = System.lineSeparator();

				System.out.printf(contactId + " " + name + " " + email + " " + address + " " + newline);
			}

			rs.close();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Student getStudentById(String studentId) {
		try {
//			String query = "select * from student where studentId='" + studentId + "'";

			Connection conn = StudentRepo.getConnection();
			CallableStatement ps = conn.prepareCall("{CALL find_student_by_id (?)}");
			ps.setString(1, studentId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String studentId1 = rs.getString(1);
				student.setStudentId(studentId1);
				String name = rs.getString(2);
				student.setStudentName(name);
				String address = rs.getString(3);
				student.setStudentAddress(address);
				String email = rs.getString(4);
				student.setStudentEmail(email);

				System.out.printf(studentId1 + " " + name + " " + address + " " + email);
			}

			rs.close();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public Student deleteStudent(String studentId) {
		try {

			Connection conn = StudentRepo.getConnection();
//			String sql = "DELETE FROM student WHERE studentId=?";
			CallableStatement ps = conn.prepareCall("{CALL delete_student(?)}");
			ps.setString(1, studentId);

			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("Data deleted successfully!");
			} else
				System.out.println("Failed to delete data..!");

			ps.close();
			conn.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Student updateStudent(String studentId, String studentName, String studentAddress, String studentEmail) {
		try {

			Connection conn = StudentRepo.getConnection();
//			String sql = "UPDATE student SET studentName=?,studentAddress=?,studentEmail=? WHERE studentId=?";
			CallableStatement ps = conn.prepareCall("{CALL update_student(?,?,?,?)}");
			ps.setString(1, studentName);
			ps.setString(2, studentAddress);
			ps.setString(3, studentEmail);
			ps.setString(4, studentId);

			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("An existing student was updated successfully!");
			} else
				System.out.println("Failed to update data..!");

			ps.close();
			conn.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

}
