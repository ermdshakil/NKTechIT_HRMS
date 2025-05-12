package com.hrms.HRMSBySb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.HRMSBySb.Entity.Career;

@Repository
public interface CareerRepo extends JpaRepository<Career, Long>{

}
