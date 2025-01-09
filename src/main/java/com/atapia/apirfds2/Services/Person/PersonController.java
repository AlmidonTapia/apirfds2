package com.atapia.apirfds2.Services.Person;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atapia.apirfds2.Business.BussinesPerson;
import com.atapia.apirfds2.Dto.DtoPerson;
import com.atapia.apirfds2.Services.Person.RequestObject.RequestInsert;
import com.atapia.apirfds2.Services.Person.RequestObject.RequestUpdate;
import com.atapia.apirfds2.Services.Person.ResponseObject.ReponseUpdate;
import com.atapia.apirfds2.Services.Person.ResponseObject.ResponseDelete;
import com.atapia.apirfds2.Services.Person.ResponseObject.ResponseGetAll;
import com.atapia.apirfds2.Services.Person.ResponseObject.ResponseInsert;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("actividades")
public class PersonController {

    @Autowired
    private BussinesPerson businessPerson;


    @PostMapping(path = "insert", consumes = "multipart/form-data")
    public ResponseEntity<ResponseInsert> insert(@Valid @ModelAttribute RequestInsert request,
            BindingResult bindingResult) {
        ResponseInsert response = new ResponseInsert();

		try {
			if (bindingResult.hasErrors()) {
				bindingResult.getAllErrors().forEach(error -> {
					response.mo.addMessage(error.getDefaultMessage());
				});

                return new ResponseEntity<>(response, HttpStatus.OK);
			}

			DtoPerson dtoPerson = new DtoPerson();

			dtoPerson.setNombre(request.getNombre());
			dtoPerson.setFecha_hora_inicio(new SimpleDateFormat("yyyy-mm-dd").parse(request.getFecha_hora_inicio()));
			dtoPerson.setFecha_hora_termino(new SimpleDateFormat("yyyy-mm-dd").parse(request.getFecha_hora_termino()));

			businessPerson.insert(dtoPerson);

			response.mo.addMessage("Registro realizado correctamente");
			response.mo.setSuccess();
		} catch (Exception e) {
			response.mo.addMessage("Ocurrió un error inesperado, estamos trabajando para resolvero. Gracias por su paciencia.");
			response.mo.setException();
		}

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

        	@GetMapping(path = "getall")
	public ResponseEntity<ResponseGetAll> getAll() {
		ResponseGetAll response = new ResponseGetAll();

		try {
			List<DtoPerson> listDtoPerson = businessPerson.getAll();

			for (DtoPerson item : listDtoPerson) {
				Map<String, Object> map = new HashMap<>();

				map.put("idActividad", item.getIdActividad());
				map.put("nombre", item.getNombre());
				map.put("fecha_hora_inicio", item.getFecha_hora_inicio());
				map.put("fecha_hora_termino", item.getFecha_hora_termino());
				response.dto.listPerson.add(map);

				response.mo.setSuccess();
			}
		} catch (Exception e) {
			response.mo.addMessage("Ocurrió un error inesperado, estamos trabajando para resolvero. Gracias por su paciencia.");
			response.mo.setException();
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(path = "delete/{idActividad}")
	public ResponseEntity<ResponseDelete> delete(@PathVariable String idActividad) {
		ResponseDelete response = new ResponseDelete();

		try {
			businessPerson.delete(idActividad);

			response.mo.addMessage("Eliminación realizada correctamente");
			response.mo.setSuccess();
		} catch (Exception e) {
			response.mo.addMessage("Ocurrió un error inesperado, estamos trabajando para resolvero. Gracias por su paciencia.");
			response.mo.setException();
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

    @PostMapping(path = "update", consumes = { "multipart/form-data" })
	public ResponseEntity<ReponseUpdate> actionUpdate(@ModelAttribute RequestUpdate requestUpdate,
			BindingResult bindingResult) {
		ReponseUpdate response = new ReponseUpdate();

		try {
			if (bindingResult.hasErrors()) {
				bindingResult.getAllErrors().forEach(error -> {
					response.mo.addMessage(error.getDefaultMessage());
				});

				return new ResponseEntity<>(response, HttpStatus.OK);
			}
            DtoPerson dtoPerson = new DtoPerson();
            dtoPerson.setIdActividad(requestUpdate.getIdActividad());
            dtoPerson.setNombre(requestUpdate.getNombre());
            dtoPerson.setFecha_hora_inicio(new SimpleDateFormat("yyyy-MM-dd").parse(requestUpdate.getFecha_hora_inicio()));
			dtoPerson.setFecha_hora_termino(new SimpleDateFormat("yyyy-MM-dd").parse(requestUpdate.getFecha_hora_termino()));

            businessPerson.update(dtoPerson);

       response.mo.addMessage("Cambios realizados correctamente");
			response.mo.setSuccess();
		} catch (Exception e) {
			response.mo.addMessage("Ocurrió un error inesperado, estamos trabajando para resolvero. Gracias por su paciencia.");
			response.mo.setException();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	

}
