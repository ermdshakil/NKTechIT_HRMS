package com.hrms.HRMSBySb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.HRMSBySb.Entity.Events;

@Repository
public interface EventRepo extends JpaRepository<Events, Integer>{

}
