package com.jamesferrer.consultorio.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "citas")
public class Cita implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cita")
	private Integer idCita;

	@NotNull(message = "\"fecha de cita\" no puede estar vacio")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@NotEmpty(message = "\"hora programada\" no puede estar vacio")
	private String hora;

	@NotNull(message = "\"costo\" no puede estar vacio")
	private Integer costo;
	
	private Byte saldo;

	@JoinColumn(name = "servicio_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "\"servicio\" no puede estar vacio")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Servicio servicio;

	@JoinColumn(name = "paciente_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "\"datos del paciente\" no puede estar vacio")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Paciente paciente;

	@JoinColumn(name = "empleado_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "\"nombre del dr(a)\" no puede estar vacio")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Empleado empleado;

	public Integer getIdCita() {
		return idCita;
	}

	public void setIdCita(Integer idCita) {
		this.idCita = idCita;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Integer getCosto() {
		return costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Byte getSaldo() {
		return saldo;
	}

	public void setSaldo(Byte saldo) {
		this.saldo = saldo;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
