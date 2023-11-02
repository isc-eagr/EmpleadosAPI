package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
	
	public Empleado findByNombre(String nombre);
	
	public List<Empleado> findByDepartamento(String departamento);
	
	public List<Empleado> findByDepartamentoAndSueldo(String departamento, Double sueldo);
	
	public List<Empleado> findBySueldoGreaterThan(Double sueldo);
	
	//GreaterThan >
	//LessThan <
	//GreaterThanEquals >=
	//LessThanEquals <=
	//Equals = --por confirmar
	
}
