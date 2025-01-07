package com.atapia.apirfds2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.atapia.apirfds2.entity.TPerson;

@Repository
public interface PersonRepository extends JpaRepository<TPerson, String> {

    @Query(value = "select * from tperson where email=:email and password=:password;", nativeQuery=true)
    TPerson getLogin(String email, String password);

}