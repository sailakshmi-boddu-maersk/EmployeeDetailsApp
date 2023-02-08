package com.slb.EmployeeDetailsApp.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slb.EmployeeDetailsApp.model.Address1;

@Repository
public interface AddressRepo extends JpaRepository<Address1,Integer> {

}
