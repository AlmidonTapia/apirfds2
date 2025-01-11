package com.atapia.apirfds2.Services.Actividad;

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

import com.atapia.apirfds2.Business.BussinesActividad;
import com.atapia.apirfds2.Dto.DtoActividad;
import com.atapia.apirfds2.Services.Actividad.RequestObject.RequestInsert;
import com.atapia.apirfds2.Services.Actividad.RequestObject.RequestUpdate;
import com.atapia.apirfds2.Services.Actividad.ResponseObject.ReponseUpdate;
import com.atapia.apirfds2.Services.Actividad.ResponseObject.ResponseDelete;
import com.atapia.apirfds2.Services.Actividad.ResponseObject.ResponseGetAll;
import com.atapia.apirfds2.Services.Actividad.ResponseObject.ResponseInsert;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("actividad/")
public class PersonController {

    @Autowired
    private BussinesActividad businessActividad;


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

			DtoActividad dtoActividad = new DtoActividad();

			dtoActividad.setNombre(request.getNombre());
			dtoActividad.setFecha_hora_inicio(new SimpleDateFormat("yyyy-mm-dd").parse(request.getFecha_hora_inicio()));
			dtoActividad.setFecha_hora_termino(new SimpleDateFormat("yyyy-mm-dd").parse(request.getFecha_hora_termino()));

			businessActividad.insert(dtoActividad);

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
			List<DtoActividad> listDtoActividad = businessActividad.getAll();

			for (DtoActividad item : listDtoActividad) {
				Map<String, Object> map = new HashMap<>();

				map.put("idActividad", item.getIdActividad());
				map.put("nombre", item.getNombre());
				map.put("fecha_hora_inicio", item.getFecha_hora_inicio());
				map.put("fecha_hora_termino", item.getFecha_hora_termino());
				response.dto.listActividad.add(map);

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
			businessActividad.delete(idActividad);

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
			DtoActividad dtoActividad = new DtoActividad();

            dtoActividad.setIdActividad(requestUpdate.getIdActividad());
            dtoActividad.setNombre(requestUpdate.getNombre());
            dtoActividad.setFecha_hora_inicio(new SimpleDateFormat("yyyy-MM-dd").parse(requestUpdate.getFecha_hora_inicio()));
			dtoActividad.setFecha_hora_termino(new SimpleDateFormat("yyyy-MM-dd").parse(requestUpdate.getFecha_hora_termino()));

           	businessActividad.update(dtoActividad);

       response.mo.addMessage("Cambios realizados correctamente");
			response.mo.setSuccess();
		} catch (Exception e) {
			response.mo.addMessage("Ocurrió un error inesperado, estamos trabajando para resolvero. Gracias por su paciencia.");
			response.mo.setException();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	

}
