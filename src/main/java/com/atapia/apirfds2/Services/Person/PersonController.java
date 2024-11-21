package com.atapia.apirfds2.Services.Person;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atapia.apirfds2.Business.BussinesPerson;
import com.atapia.apirfds2.Dto.DtoPerson;
import com.atapia.apirfds2.Services.Person.RequestObject.RequestInsert;
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

}
