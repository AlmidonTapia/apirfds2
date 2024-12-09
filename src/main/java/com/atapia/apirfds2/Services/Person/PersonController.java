package com.atapia.apirfds2.Services.Person;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atapia.apirfds2.Business.BussinesPerson;
import com.atapia.apirfds2.Dto.DtoPerson;
import com.atapia.apirfds2.Services.Person.RequestObject.RequestInsert;
import com.atapia.apirfds2.Services.Person.RequestObject.RequestUpdate;
import com.atapia.apirfds2.Services.Person.ResponseObject.ResponseGetAll;
import com.atapia.apirfds2.Services.Person.ResponseObject.ResponseGetData;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private BussinesPerson bussinesPerson;

    @GetMapping(path = "getdata")
    public ResponseEntity<ResponseGetData> getdData() {
        ResponseGetData responseGetData = new ResponseGetData();
        responseGetData.firstName = "Saul";
        responseGetData.surName = "Tapia Almdion";
        responseGetData.dni = "73534380";
        return new ResponseEntity<>(responseGetData, HttpStatus.OK);
    }

    @PostMapping(path = "insert", consumes = "multipart/form-data")
    public ResponseEntity<String> insert(@ModelAttribute RequestInsert request) {
        try {
            DtoPerson dtoPerson = new DtoPerson();

            dtoPerson.setFirstName(request.getFirstName());
            dtoPerson.setSurName(request.getSurName());
            dtoPerson.setDni(request.getDni());
            dtoPerson.setGender(request.isGender());
            dtoPerson.setBirthDate(new SimpleDateFormat("yyyy-mm-dd").parse(request.getBirthDate()));

            bussinesPerson.insert(dtoPerson);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Person inserted", HttpStatus.OK);
    }

    @GetMapping(path = "getAll")
    public ResponseEntity<ResponseGetAll> getAll() {
        ResponseGetAll responseGetAll = new ResponseGetAll();

        List<DtoPerson> listDtoPerson = bussinesPerson.getAll();

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

            responseGetAll.response.listPerson.add(map);
        }

        return new ResponseEntity<>(responseGetAll, HttpStatus.OK);
    }

    @DeleteMapping(path = "delete/{idPerson}")
    public ResponseEntity<Boolean> delete(@PathVariable String idPerson) {
        bussinesPerson.delete(idPerson);

        return new ResponseEntity<>(bussinesPerson.delete(idPerson), HttpStatus.OK);
    }

    @PostMapping(path = "update", consumes = { "multipart/form-data" })
    public ResponseEntity<String> actionUpdate(@ModelAttribute   RequestUpdate requestUpdate) {

        try {
            DtoPerson dtoPerson = new DtoPerson();

            dtoPerson.setIdPerson(requestUpdate.getIdPerson());
            dtoPerson.setFirstName(requestUpdate.getFirstName());
            dtoPerson.setSurName(requestUpdate.getSurName());
            dtoPerson.setDni(requestUpdate.getDni());
            dtoPerson.setGender(requestUpdate.isGender());
            dtoPerson.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(requestUpdate.getBirthDate()));

            bussinesPerson.update(dtoPerson);

            return new ResponseEntity<>("Person update", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
