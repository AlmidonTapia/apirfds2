package com.atapia.apirfds2.Services.Person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import com.atapia.apirfds2.Services.Person.ResponseObject.ResponseGetData;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping(path = "get/{dni}")
    public ResponseEntity<ResponseGetData> getPersonByDni(@PathVariable String dni) {
        try {
            DtoPerson dtoPerson = bussinesPerson.getByDni(dni);
            if (dtoPerson == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            ResponseGetData responseGetData = new ResponseGetData();
            responseGetData.firstName = dtoPerson.getFirstName();
            responseGetData.surName = dtoPerson.getSurName();
            responseGetData.dni = dtoPerson.getDni();
            return new ResponseEntity<>(responseGetData, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "delete/{dni}")
    public ResponseEntity<String> delete(@PathVariable String dni) {
        try {
            bussinesPerson.deleteByDni(dni);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error deleting person", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Person deleted", HttpStatus.OK);
    }

    @PutMapping(path = "update/{dni}", consumes = "multipart/form-data")
    public ResponseEntity<String> update(@PathVariable String dni, @RequestBody RequestInsert request) {
        try {
            DtoPerson dtoPerson = new DtoPerson();

            dtoPerson.setFirstName(request.getFirstName());
            dtoPerson.setSurName(request.getSurName());
            dtoPerson.setDni(dni);
            dtoPerson.setGender(request.isGender());
            dtoPerson.setBirthDate(new SimpleDateFormat("dd-MM-yyyy").parse(request.getBirthDate()));

            bussinesPerson.update(dtoPerson);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating person", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Person updated", HttpStatus.OK);
    }

    @GetMapping(path = "list")
    public ResponseEntity<List<ResponseGetData>> listAllPersons() {
        try {
            List<DtoPerson> dtoPersons = bussinesPerson.getAll();
            List<ResponseGetData> responseList = new ArrayList<>();

            for (DtoPerson dtoPerson : dtoPersons) {
                ResponseGetData responseGetData = new ResponseGetData();
                responseGetData.firstName = dtoPerson.getFirstName();
                responseGetData.surName = dtoPerson.getSurName();
                responseGetData.dni = dtoPerson.getDni();
                responseList.add(responseGetData);
            }

            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
