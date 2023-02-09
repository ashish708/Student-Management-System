package com.gl.examples.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private String studentId;
	private String studentName;
	private String studentAddress;
	private String studentEmail;

}
