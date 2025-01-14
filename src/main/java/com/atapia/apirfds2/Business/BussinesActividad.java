package com.atapia.apirfds2.Business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atapia.apirfds2.Dto.DtoActividad;
import com.atapia.apirfds2.entity.TActividad;
import com.atapia.apirfds2.repository.ActividadRepository;

import jakarta.transaction.Transactional;

@Service
public class BussinesActividad {
    @Autowired
    private ActividadRepository actividadRepository;

    @Transactional
    public void insert(DtoActividad dtoActividad) {
        if (dtoActividad.getFecha_hora_inicio().compareTo(dtoActividad.getFecha_hora_termino()) >= 0) {
            throw new IllegalArgumentException("La fecha y hora de inicio no puede ser mayor o igual a la fecha y hora de término");
        }

        dtoActividad.setIdActividad(UUID.randomUUID().toString());

        TActividad tActividad = new TActividad();

        tActividad.setIdActividad(dtoActividad.getIdActividad());
        tActividad.setNombre(dtoActividad.getNombre());
        tActividad.setFecha_hora_inicio(dtoActividad.getFecha_hora_inicio());
        tActividad.setFecha_hora_termino(dtoActividad.getFecha_hora_termino());

        actividadRepository.save(tActividad);
    }
    
    public List<DtoActividad> getAll() {
        List<TActividad> listTActividad = actividadRepository.findAll();

        List<DtoActividad> listDtoActividad = new ArrayList<>();

        for (TActividad item : listTActividad) {
            DtoActividad dtoActividad = new DtoActividad();

            dtoActividad.setIdActividad(item.getIdActividad());
            dtoActividad.setNombre(item.getNombre());
            dtoActividad.setFecha_hora_inicio(item.getFecha_hora_inicio());
            dtoActividad.setFecha_hora_termino(item.getFecha_hora_termino());

            listDtoActividad.add(dtoActividad);
        }

        return listDtoActividad;
        }
        
        @Transactional
        public boolean delete(String idActividad) {
            Optional<TActividad> tActividad = actividadRepository.findById(idActividad);

            if (tActividad.isPresent()) {
                actividadRepository.deleteById(idActividad);
            }

            return true;
        }

        @Transactional
        public boolean update(DtoActividad dtoActividad) {
            Optional<TActividad> optionTActividad = actividadRepository.findById(dtoActividad.getIdActividad());
            if (optionTActividad.isEmpty()) {
                return false;
            }

            optionTActividad.get().setNombre(dtoActividad.getNombre());
            optionTActividad.get().setFecha_hora_inicio(dtoActividad.getFecha_hora_inicio());
            optionTActividad.get().setFecha_hora_termino(dtoActividad.getFecha_hora_termino());

            actividadRepository.save(optionTActividad.get());

            return true;
        }

}
