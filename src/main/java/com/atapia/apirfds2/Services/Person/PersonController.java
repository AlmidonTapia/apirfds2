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
import com.atapia.apirfds2.Services.Person.ResponseObject.ResponseGetData;
import com.atapia.apirfds2.Services.Person.ResponseObject.ResponseInsert;

//import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private BussinesPerson businessPerson;

    @GetMapping(path = "getdata")
    public ResponseEntity<ResponseGetData> getdData() {
        ResponseGetData responseGetData = new ResponseGetData();

        try{
        responseGetData.firstName = "Saul";
        responseGetData.surName = "Tapia Almdion";
        responseGetData.dni = "73534380";
        }catch(Exception e){
            e.printStackTrace();
            
        }
        return new ResponseEntity<>(responseGetData, HttpStatus.OK);
    }

    @PostMapping(path = "insert", consumes = "multipart/form-data")
    public ResponseEntity<ResponseInsert> insert(/*@Valid*/ @ModelAttribute RequestInsert request,
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

			dtoPerson.setFirstName(request.getFirstName());
			dtoPerson.setSurName(request.getSurName());
			dtoPerson.setDni(request.getDni());
			dtoPerson.setGender(request.isGender());
			dtoPerson.setBirthDate(new SimpleDateFormat("yyyy-mm-dd").parse(request.getBirthDate()));

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

				map.put("idPerson", item.getIdPerson());
				map.put("firstName", item.getFirstName());
				map.put("surName", item.getSurName());
				map.put("dni", item.getDni());
				map.put("gender", item.isGender());
				map.put("birthDate", item.getBirthDate());
				map.put("createdAt", item.getCreatedAt());
				map.put("updatedAt", item.getUpdatedAt());

				response.dto.listPerson.add(map);

				response.mo.setSuccess();
			}
		} catch (Exception e) {
			response.mo.addMessage("Ocurrió un error inesperado, estamos trabajando para resolvero. Gracias por su paciencia.");
			response.mo.setException();
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(path = "delete/{idPerson}")
	public ResponseEntity<ResponseDelete> delete(@PathVariable String idPerson) {
		ResponseDelete response = new ResponseDelete();

		try {
			businessPerson.delete(idPerson);

			response.mo.addMessage("Eliminación realizada correctamente");
			response.mo.setSuccess();
		} catch (Exception e) {
			response.mo.addMessage("Ocurrió un error inesperado, estamos trabajando para resolvero. Gracias por su paciencia.");
			response.mo.setException();
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

    @PostMapping(path = "update", consumes = { "multipart/form-data" })
	public ResponseEntity<ReponseUpdate> actionUpdate(/* @Valid */@ModelAttribute RequestUpdate requestUpdate,
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
            dtoPerson.setIdPerson(requestUpdate.getIdPerson()); // por modificar en principio no se deberian actualizar los id xde
            dtoPerson.setFirstName(requestUpdate.getFirstName());
            dtoPerson.setSurName(requestUpdate.getSurName());
            dtoPerson.setDni(requestUpdate.getDni());
            dtoPerson.setGender(requestUpdate.isGender());
            dtoPerson.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(requestUpdate.getBirthDate()));

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
