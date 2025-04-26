package com.example.CapstoneProject.repository;
import com.example.CapstoneProject.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Long> {
    List<Bus> findBySourceIgnoreCaseAndDestinationIgnoreCase(String source, String destination);

}


