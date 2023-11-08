package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ReportePorDeptoDTO;

@Repository
public class EmpleadoRepositoryJDBC {
	
	@Autowired
	JdbcTemplate jdbc;
	
	private static final String QUERY_REPORTE_POR_DEPTO = """
				select departamento, count(*) num_empleados, min(sueldo) sueldo_menor, max(sueldo) sueldo_mayor
				from empleado
				group by departamento
				order by count(*) desc, max(sueldo) desc
			""";
	
	private static final String QUERY_REPORTE_POR_DEPTO_FILTRADO = """
			select departamento, count(*) num_empleados, min(sueldo) sueldo_menor, max(sueldo) sueldo_mayor
			from empleado
			group by departamento
			having max(sueldo) >= ?
			order by count(*) desc, max(sueldo) desc
		""";
	
	private static final String ACTUALIZAR_SUELDO_MINIMO = """
			update empleado
			set sueldo = ?
			where sueldo < ?
			and departamento != 'Becario';
		""";
	
	//El metodo query es para selects
	public List<ReportePorDeptoDTO> reportePorDepto(){
		return jdbc.query(QUERY_REPORTE_POR_DEPTO, new BeanPropertyRowMapper<>(ReportePorDeptoDTO.class));
	}
	
	public List<ReportePorDeptoDTO> reportePorDeptoFiltrado(Double sueldo){
		return jdbc.query(QUERY_REPORTE_POR_DEPTO_FILTRADO, new BeanPropertyRowMapper<>(ReportePorDeptoDTO.class), sueldo);
	}
	
	public int actualizarSueldoMinimo(Double sueldoMinimo, Double sueldoUmbral){
		//El metodo update sirve para hacer updates y deletes
		return jdbc.update(ACTUALIZAR_SUELDO_MINIMO, sueldoMinimo, sueldoUmbral);
	}
}
