package com.jamesferrer.consultorio.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "empleados_roles")
public class EmpleadoRol implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado_rol")
	private Integer idEmpleadoRol;
	
	@NotNull(message="no puede estar vacio!")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rol_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Rol rol;
	
	@NotNull(message="no puede estar vacio!")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="empleado_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Empleado empleado;

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Integer getIdEmpleadoRol() {
		return idEmpleadoRol;
	}

	public void setIdEmpleadoRol(Integer idEmpleadoRol) {
		this.idEmpleadoRol = idEmpleadoRol;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
