package com.atapia.apirfds2.Business;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atapia.apirfds2.Dto.DtoPerson;
import com.atapia.apirfds2.entity.TPerson;
import com.atapia.apirfds2.repository.PersonRepository;

@Service
public class BussinesPerson {
    @Autowired
    private PersonRepository personRepository;

    public void insert(DtoPerson dtoPerson) {
        dtoPerson.setIdPerson(UUID.randomUUID().toString());
        dtoPerson.setCreatedAt(new Date());
        dtoPerson.setUpdatedAt(new Date());

        TPerson tPerson = new TPerson();

        tPerson.setIdPerson(dtoPerson.getIdPerson());
        tPerson.setFirstName(dtoPerson.getFirstName());
        tPerson.setSurName(dtoPerson.getSurName());
        tPerson.setDni(dtoPerson.getDni());
        tPerson.setGender(dtoPerson.isGender());
        tPerson.setBirthDate(dtoPerson.getBirthDate());
        tPerson.setCreatedAt(dtoPerson.getCreatedAt());
        tPerson.setUpdatedAt(dtoPerson.getUpdatedAt());

        personRepository.save(tPerson);
    }
}
