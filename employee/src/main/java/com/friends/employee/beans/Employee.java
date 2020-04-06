package com.friends.employee.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class Employee {
	
	@NotBlank(message = "Employee name shouldn't be empty")
	@Column(name="name", nullable = false)
	private String name;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Min(value = 20, message = "Employee's age should be atleast 20 years old ")
	@Column(name="age", nullable = false)
	private Integer age;
	
	@Column(name="salary", nullable = true)
	private Double salary;
	
	public Employee(@NotNull String name, @NotNull Long id, @Min(value = 20) Integer age, Double salary) {
		super();
		this.name = name;
		this.id = id;
		this.age = age;
		this.salary = salary;
	}
	
	public Employee(@NotNull String name,  @Min(value = 20) Integer age, Double salary) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public Employee() {}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(obj instanceof Employee) {
			Employee employee = (Employee) obj;
			return employee.getId() == this.id;
		} else {
			return false;
		}
	}

}
