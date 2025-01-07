package com.atapia.apirfds2.Dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoPerson {
    private String idPerson;
    private String firstName;
    private String surName;
    private String dni;
    private boolean gender;
    private Date birthDate;
    private String email;
    private String password;
    private Date createdAt;
    private Date updatedAt;
}
