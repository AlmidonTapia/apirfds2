package com.atapia.apirfds2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atapia.apirfds2.entity.TActividad;

@Repository
public interface ActividadRepository extends JpaRepository<TActividad, String> {


}