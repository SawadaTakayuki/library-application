package com.example.samplelibraryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samplelibraryapp.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);

}
