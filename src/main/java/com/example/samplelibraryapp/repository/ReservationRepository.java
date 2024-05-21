package com.example.samplelibraryapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samplelibraryapp.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	 List<Reservation> findByUsername(String username);

}
