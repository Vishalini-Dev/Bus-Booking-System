package com.example.CapstoneProject.controller;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.CapstoneProject.model.*;
import com.example.CapstoneProject.service.TicketService;

@CrossOrigin(origins ="http://localhost:5500" ) 
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/search")
    public ResponseEntity<?> searchBuses(
            @RequestParam("source") String source,
            @RequestParam("destination") String destination) {
        
        try {
            List<Bus> buses = ticketService.searchBuses(source, destination);
            
            if (buses.isEmpty()) {
                return ResponseEntity.ok().body("No buses found for the given route");
            }
            
            return ResponseEntity.ok(buses);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error occurred while searching for buses: " + e.getMessage());
        }
    }

    @PostMapping("/book")
    public ResponseEntity<?> book(@RequestBody Map<String, String> data) {
        try {
            Long userId = Long.parseLong(data.get("userId"));
            Long busId = Long.parseLong(data.get("busId"));
            LocalDate date = LocalDate.parse(data.get("date"));
            return ResponseEntity.ok(ticketService.bookTicket(userId, busId, date));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid booking data");
            return ResponseEntity.badRequest().body(error);
        }
    }


    @GetMapping("/history/{userId}")
    public ResponseEntity<?> history(@PathVariable("userId") Long userId) {
        try {
            List<Ticket> tickets = ticketService.getHistory(userId);
            if (tickets.isEmpty()) {
                return ResponseEntity.ok().body("No booking history found");
            }
            return ResponseEntity.ok(tickets);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching history: " + e.getMessage());
        }
    }
}
