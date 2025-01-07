package com.atapia.apirfds2.Services.Person.RequestObject;

//import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestInsert {
    private String firstName;
    private String surName;
    private String dni;
    private boolean gender;
    private String birthDate;
    private String email;
    private String password;
}
