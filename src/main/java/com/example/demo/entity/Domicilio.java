package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="domicilio")
@Data
public class Domicilio {
	
	@Id
	@Column(name="domicilio_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer domicilioId;
	
	@Column(name="domicilio_cadena")
	private String domicilioCadena;
	
	@ManyToOne
	@JoinColumn(name="empleado_id")
	@JsonBackReference
	private Empleado empleadoId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estado_id")
	@JsonManagedReference
	private Estado estado;
	

}
