package com.atapia.apirfds2.Services.Person.ResponseObject;

import com.atapia.apirfds2.Services.Generic.ResponseGeneric;

public class ResponseLogin extends ResponseGeneric {
     public class Dto {
        public Object person;
    }

    public Dto dto;

    public ResponseLogin() {
        dto = new Dto();
    }
}
