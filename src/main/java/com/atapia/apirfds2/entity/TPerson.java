package com.atapia.apirfds2.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Actividad")
@NoArgsConstructor
@Getter
@Setter
public class TPerson implements Serializable {
	@Id
	@Column(name = "idActividad")
	private String idActividad;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "fecha_hora_inicio")
	private Date fecha_hora_inicio;

	@Column(name = "fecha_hora_termino")
	private Date fecha_hora_termino;
}