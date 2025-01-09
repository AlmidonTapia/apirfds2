package com.atapia.apirfds2.Services.Person.RequestObject;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestUpdate {
    private String idActividad;
    private String nombre;
    private String fecha_hora_inicio;
    private String fecha_hora_termino;
}
