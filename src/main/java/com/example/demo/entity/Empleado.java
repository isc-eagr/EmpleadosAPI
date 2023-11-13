package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="empleado")
@Data
public class Empleado {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="departamento")
	private String departamento;
	
	@Column(name="sueldo")
	private Double sueldo;
	
	//El fetchtype puede ser LAZY o EAGER
	//Lazy ahorra recursos como memoria, pero es mas lento
	//Eager usa mas recursos, pero es mas rapido
	@OneToMany(mappedBy = "empleadoId", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Domicilio> domicilios;

}
