package com.atapia.apirfds2.Services.Actividad.RequestObject;


//import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestInsert {
    private String nombre;
    private String fecha_hora_inicio;
    private String fecha_hora_termino;
}
