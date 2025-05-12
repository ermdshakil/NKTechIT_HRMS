package com.hrms.HRMSBySb.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrms.HRMSBySb.Entity.Ticket;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long>{
	@Query("select t from Ticket t where t.empId=?1")
	public List<Ticket> findAllById(int id);
}
