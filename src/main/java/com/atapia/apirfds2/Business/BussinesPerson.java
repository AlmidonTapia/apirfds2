package com.atapia.apirfds2.Business;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import java.util.List;
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
    
    public void deleteByDni(String dni) {
        TPerson tPerson = personRepository.findByDni(dni);
        if (tPerson != null) {
            personRepository.delete(tPerson);
        }
    }

    public void update(DtoPerson dtoPerson) {
        TPerson tPerson = personRepository.findByDni(dtoPerson.getDni());
        if (tPerson != null) {
            tPerson.setFirstName(dtoPerson.getFirstName());
            tPerson.setSurName(dtoPerson.getSurName());
            tPerson.setGender(dtoPerson.isGender());
            tPerson.setBirthDate(dtoPerson.getBirthDate());
            tPerson.setUpdatedAt(new Date());
            personRepository.save(tPerson);
        }
    }
    
    public DtoPerson getByDni(String dni) {
        TPerson tPerson = personRepository.findByDni(dni);
        if (tPerson != null) {
            DtoPerson dtoPerson = new DtoPerson();
            dtoPerson.setIdPerson(tPerson.getIdPerson());
            dtoPerson.setFirstName(tPerson.getFirstName());
            dtoPerson.setSurName(tPerson.getSurName());
            dtoPerson.setDni(tPerson.getDni());
            dtoPerson.setGender(tPerson.isGender());
            dtoPerson.setBirthDate(tPerson.getBirthDate());
            dtoPerson.setCreatedAt(tPerson.getCreatedAt());
            dtoPerson.setUpdatedAt(tPerson.getUpdatedAt());
            return dtoPerson;
        }
        return null;
    }

    public List<DtoPerson> getAll() {
        List<TPerson> tPersons = personRepository.findAll();
        List<DtoPerson> dtoPersons = new ArrayList<>();
        for (TPerson tPerson : tPersons) {
            DtoPerson dtoPerson = new DtoPerson();
            dtoPerson.setIdPerson(tPerson.getIdPerson());
            dtoPerson.setFirstName(tPerson.getFirstName());
            dtoPerson.setSurName(tPerson.getSurName());
            dtoPerson.setDni(tPerson.getDni());
            dtoPerson.setGender(tPerson.isGender());
            dtoPerson.setBirthDate(tPerson.getBirthDate());
            dtoPerson.setCreatedAt(tPerson.getCreatedAt());
            dtoPerson.setUpdatedAt(tPerson.getUpdatedAt());
            dtoPersons.add(dtoPerson);
        }
        return dtoPersons;
    }
}
