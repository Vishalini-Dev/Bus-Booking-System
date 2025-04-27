package com.example.CapstoneProject.controller;

import com.example.CapstoneProject.model.Bus;
import com.example.CapstoneProject.model.Ticket;
import com.example.CapstoneProject.service.TicketService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketController ticketController;

    public TicketControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchBuses_Success() {
        List<Bus> buses = Arrays.asList(new Bus(), new Bus());
        when(ticketService.searchBuses("A", "B")).thenReturn(buses);

        ResponseEntity<?> response = ticketController.searchBuses("A", "B");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(buses, response.getBody());
    }

    @Test
    void testSearchBuses_NoBusesFound() {
        when(ticketService.searchBuses("A", "B")).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = ticketController.searchBuses("A", "B");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("No buses found for the given route", response.getBody());
    }

    @Test
    void testBook_Success() {
        Map<String, String> request = new HashMap<>();
        request.put("userId", "1");
        request.put("busId", "2");
        request.put("date", "2025-05-01");

        when(ticketService.bookTicket(1L, 2L, LocalDate.of(2025, 5, 1))).thenReturn("Booking Successful");

        ResponseEntity<?> response = ticketController.book(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Booking Successful", response.getBody());
    }

    @Test
    void testHistory_Success() {
        List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket());
        when(ticketService.getHistory(1L)).thenReturn(tickets);

        ResponseEntity<?> response = ticketController.history(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(tickets, response.getBody());
    }

    @Test
    void testHistory_NoTickets() {
        when(ticketService.getHistory(1L)).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = ticketController.history(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("No booking history found", response.getBody());
    }
}

