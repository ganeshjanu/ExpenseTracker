package com.friends.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.friends.employee.beans.dto.Address;

public interface AddressRepository extends JpaRepository<Address, Long>  {

}