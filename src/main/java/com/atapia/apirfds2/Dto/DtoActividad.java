package com.atapia.apirfds2.Dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoActividad {
    private String idActividad;
    private String nombre;
    private Date fecha_hora_inicio;
    private Date fecha_hora_termino;
}
