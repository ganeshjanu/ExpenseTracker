package com.friends.employee.beans.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class EmployeeForm {


	private Long id;
	
	@Min(value = 20, message = "Employee's age should be atleast 20 years old ")
	private Integer age;
	
	@NotBlank(message = "Employee name shouldn't be empty")
	private String name;
	
	private Address address;
	
	private Phone phone;
	

	
}
