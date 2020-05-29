package com.friends.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.friends.employee.beans.dto.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
	
	@Query(value = "Select * from phone p where p.mobile_num = :mobileNo", nativeQuery = true)
	public Optional<Phone> findByMobileNum(@Param("mobileNo") String mobileNum);

}
