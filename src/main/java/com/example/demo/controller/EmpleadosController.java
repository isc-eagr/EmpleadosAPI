package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Empleado;
import com.example.demo.service.EmpleadoService;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadosController {

	Logger logger = LoggerFactory.getLogger(EmpleadosController.class);
	
	@Autowired
	EmpleadoService empleadoService;
	
	@PostMapping("/alta")
	public ResponseEntity<Empleado> altaEmpleado(@RequestBody Empleado empleado) {
		return new ResponseEntity<>(empleadoService.insertaOActualizaEmpleado(empleado),HttpStatus.OK);
	}
	
	@GetMapping("/consulta/{id}")
	public ResponseEntity<Empleado> consultaEmpleado(@PathVariable Integer id) {
		
		Optional<Empleado> empOptional = empleadoService.consultaPorId(id);
		if(empOptional.isPresent()){ //empOptional.isEmpty()
			return new ResponseEntity<>(empOptional.get(),HttpStatus.OK);
		}
		else {
			
			//Niveles de severidad en loggeo
			//TRACE
			//DEBUG
			//INFO
			//WARN
			//ERROR
			logger.info("El empleado no fue encontrado");
			return new ResponseEntity<>(new Empleado(),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/consultaNombre/{nombre}")
	public ResponseEntity<Empleado> consultaEmpleadoPorNombre(@PathVariable String nombre) {
		return new ResponseEntity<>(empleadoService.consultaPorNombre(nombre),HttpStatus.OK);
	}
	
	@GetMapping("/consultaDepartamento/{departamento}")
	public ResponseEntity<List<Empleado>> consultaEmpleadosPorDepartamento(@PathVariable String departamento) {
		return new ResponseEntity<>(empleadoService.consultaPorDepartamento(departamento),HttpStatus.OK);
	}
	
	@GetMapping("/consultaDepartamentoYSueldo/{departamento}/{sueldo}")
	public ResponseEntity<List<Empleado>> consultaPorDepartamentoYSueldo(@PathVariable String departamento,
																		@PathVariable Double sueldo) {
		return new ResponseEntity<>(empleadoService.consultaPorDepartamentoYSueldo(departamento,sueldo),HttpStatus.OK);
	}
	
	@GetMapping("/consultaSueldoMayorQue/{sueldo}")
	public ResponseEntity<List<Empleado>> consultaPorSueldoMayorQue(@PathVariable Double sueldo) {
		return new ResponseEntity<>(empleadoService.consultaPorSueldoMayorQue(sueldo),HttpStatus.OK);
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<Empleado> actualizarEmpleado(@RequestBody Empleado emp) {
		Empleado empLocal = empleadoService.consultaPorId(emp.getId()).orElse(new Empleado());
		//Consultamos si existe el empleado, de no existir, mandar error ya que no podemos
		//actualizar un empleado no existente
		if(empLocal.getNombre()==null) {
			logger.error("El empleado no existe");
			return new ResponseEntity<>(empLocal,HttpStatus.BAD_REQUEST);
		}else { //Si el empleado existe, actualizarlo
			logger.info("El empleado se actualizò correctamente");
			return new ResponseEntity<>(empleadoService.insertaOActualizaEmpleado(emp),HttpStatus.OK);	
		}
	}
	
	@GetMapping("/consultaPorNombreLike/{nombre}")
	public ResponseEntity<List<Empleado>> consultaPorSueldoMayorQue(@PathVariable String nombre) {
		return new ResponseEntity<>(empleadoService.consultaPorNombreLike(nombre),HttpStatus.OK);
	}
}
