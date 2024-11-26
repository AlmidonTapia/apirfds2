package com.atapia.apirfds2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atapia.apirfds2.entity.TPerson;

@Repository
public interface PersonRepository extends JpaRepository<TPerson, String> {

    TPerson findByDni(String dni);

}