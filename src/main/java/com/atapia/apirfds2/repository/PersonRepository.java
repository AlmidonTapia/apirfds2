package com.atapia.apirfds2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atapia.apirfds2.entity.TPerson;

public interface PersonRepository extends JpaRepository<TPerson, String> {
    
}