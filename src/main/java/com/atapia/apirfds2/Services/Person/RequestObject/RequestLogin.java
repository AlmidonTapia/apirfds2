package com.atapia.apirfds2.Services.Person.RequestObject;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RequestLogin {
    @NotBlank(message = "El campo \"userName\" es requerido.")
    private String email;

    @NotBlank(message = "El campo \"password\" es requerido.")
    private String password;
}
