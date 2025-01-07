package com.atapia.apirfds2.Services.Person.RequestObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestUpdate {
    private String idPerson;
    private String firstName;
    private String surName;
    private String dni;
    private boolean gender;
    private String birthDate;
    private String email;
    private String password;
}
