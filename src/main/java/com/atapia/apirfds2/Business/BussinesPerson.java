package com.atapia.apirfds2.Business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atapia.apirfds2.Dto.DtoPerson;
import com.atapia.apirfds2.entity.TPerson;
import com.atapia.apirfds2.repository.PersonRepository;

import jakarta.transaction.Transactional;

@Service
public class BussinesPerson {
    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void insert(DtoPerson dtoPerson) {
        if (dtoPerson.getFecha_hora_inicio().compareTo(dtoPerson.getFecha_hora_termino()) >= 0) {
            throw new IllegalArgumentException("La fecha y hora de inicio no puede ser mayor o igual a la fecha y hora de término");
        }

        dtoPerson.setIdActividad(UUID.randomUUID().toString());

        TPerson tPerson = new TPerson();

        tPerson.setIdActividad(dtoPerson.getIdActividad());
        tPerson.setNombre(dtoPerson.getNombre());
        tPerson.setFecha_hora_inicio(dtoPerson.getFecha_hora_inicio());
        tPerson.setFecha_hora_termino(dtoPerson.getFecha_hora_termino());

        personRepository.save(tPerson);
    }
    
    public List<DtoPerson> getAll() {
        List<TPerson> listTPerson = personRepository.findAll();

        List<DtoPerson> listDtoPerson = new ArrayList<>();

        for (TPerson item : listTPerson) {
            DtoPerson dtoPerson = new DtoPerson();

            dtoPerson.setIdActividad(item.getIdActividad());
            dtoPerson.setNombre(item.getNombre());
            dtoPerson.setFecha_hora_inicio(item.getFecha_hora_inicio());
            dtoPerson.setFecha_hora_termino(item.getFecha_hora_termino());

            listDtoPerson.add(dtoPerson);
        }

        return listDtoPerson;
        }
        
        @Transactional
        public boolean delete(String idActividad) {
            Optional<TPerson> tPerson = personRepository.findById(idActividad);

            if (tPerson.isPresent()) {
                personRepository.deleteById(idActividad);
            }

            return true;
        }

        @Transactional
        public boolean update(DtoPerson dtoPerson) {
            Optional<TPerson> optionTPeson = personRepository.findById(dtoPerson.getIdActividad());
            if (optionTPeson.isEmpty()) {
                return false;
            }

            optionTPeson.get().setNombre(dtoPerson.getNombre());
            optionTPeson.get().setFecha_hora_inicio(dtoPerson.getFecha_hora_inicio());
            optionTPeson.get().setFecha_hora_termino(dtoPerson.getFecha_hora_termino());

            personRepository.save(optionTPeson.get());

            return true;
        }

}
