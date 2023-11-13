package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="estado")
@Data
public class Estado {
	
	@Id
	@Column(name="estado_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer estadoId;
	
	@Column(name="nombre_estado")
	private String nombreEstado;
	
	@OneToOne(mappedBy = "estado")
	@JsonBackReference
	private Domicilio domicilio;
	

}
