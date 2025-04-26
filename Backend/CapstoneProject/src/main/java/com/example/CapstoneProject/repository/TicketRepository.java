package com.example.CapstoneProject.repository;
import com.example.CapstoneProject.model.Ticket;
import com.example.CapstoneProject.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	List<Ticket> findByUserId(Long userId);
}
