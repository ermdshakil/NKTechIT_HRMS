package com.hrms.HRMSBySb.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.HRMSBySb.Entity.Ticket;
import com.hrms.HRMSBySb.Repository.TicketRepo;

@Service
public class TicketService {

	@Autowired
	private TicketRepo repo;
	public String saveTicket(Ticket ticket) {
		repo.save(ticket);
		return "Saved Ticket";
	}

	public List<Ticket> allTicketById(int id){
		return repo.findAllById(id);
	}
	
	public List<Ticket> allTicket(){
		return repo.findAll();
	}	
	
	public Ticket ticketByID(long id) {
	    return repo.findById(id).orElse(null);
	}
}
