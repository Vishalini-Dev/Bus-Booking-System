package com.example.CapstoneProject.service;

import org.springframework.stereotype.Service;

import com.example.CapstoneProject.model.*;
import com.example.CapstoneProject.repository.*;

import java.time.LocalDate;
import java.util.List;
@Service
public class TicketService {
     private TicketRepository ticketRepository;
     private BusRepository busRepository;
     private UserRepository userRepository;
   
    public TicketService(TicketRepository ticketRepository, BusRepository busRepository,
			UserRepository userRepository) {
		this.ticketRepository = ticketRepository;
		this.busRepository = busRepository;
		this.userRepository = userRepository;
	}

	public List<Bus> searchBuses(String source, String destination) {
        return busRepository.findBySourceIgnoreCaseAndDestinationIgnoreCase(source.trim(), destination.trim());
    }

    public Ticket bookTicket(Long userId, Long busId, LocalDate date) {
    	User user = userRepository.findById(userId).orElseThrow();
        Bus bus = busRepository.findById(busId).orElseThrow();
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setBus(bus);
        ticket.setTravelDate(date);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getHistory(Long userId) {
        return ticketRepository.findByUserId(userId);
    }
}
