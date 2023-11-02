package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Empleado;
import com.example.demo.repository.EmpleadoRepository;

@Service
public class EmpleadoService {
	
	@Autowired
	EmpleadoRepository empleadoRepository;
	
	//El metodo save de JpaRepository sirve para insertar y actualizar en BD
	public Empleado insertaOActualizaEmpleado(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}
	
	public Optional<Empleado> consultaPorId(Integer id) {
		return empleadoRepository.findById(id);
	}
	
	public Empleado consultaPorNombre(String nombre) {
		return empleadoRepository.findByNombre(nombre);
	}
	
	public List<Empleado> consultaPorDepartamento(String departamento) {
		return empleadoRepository.findByDepartamento(departamento);
	}
	
	public List<Empleado> consultaPorDepartamentoYSueldo(String departamento, Double sueldo) {
		return empleadoRepository.findByDepartamentoAndSueldo(departamento, sueldo);
	}
	
	public List<Empleado> consultaPorSueldoMayorQue(Double sueldo) {
		return empleadoRepository.findBySueldoGreaterThan(sueldo);
	}
	


	//saveAll(List<Empleado>) --inserta o actualiza varios registros
	//findAll() --nos da una lista con todos los registros
	//count() --nos da el numero total de registros en la tabla
	//deleteById(Integer id) --borra un registro
	//deleteAll() --borra todos los registros de una tabla

}
