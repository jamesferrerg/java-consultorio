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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="empleados")
public class Empleado implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_Empleado")
	private Integer idEmpleado;
	
	//No puede ser vacio 
	@Column(nullable=false)
	@NotEmpty(message = "no puede estar vacio.")
	@Size(min=3, max=50, message = "debe tener un tamaño entre 3 y 50 caracteres")
	private String nombre;
	
	@Column(nullable=false)
	@NotEmpty(message = "no puede estar vacio")
	private String apellido;
	
	@NotEmpty(message = "no puede estar vacio")
	@Column(name="numero_Identificacion", nullable=false, unique=true)
	private String numeroIdentificacion;
	
	private Long telefono;
	
	private Long celular;
	
	@Email(message = "no es una dirección de correo bien formada")
	@Column(nullable=false)
	private String email;
	
	@NotNull(message = "no puede estar vacio")
	@Column(name="fecha_Contrato")
	@Temporal(TemporalType.DATE)
	private Date fechaContrato;
	
	private String foto;
	
	@NotNull(message="el tipo de identificación no puede estar vacia.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_Identificacion_Id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private TipoIdentificacion tipoIdentificacion;

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaContrato() {
		return fechaContrato;
	}

	public void setFechaContrato(Date fechaContrato) {
		this.fechaContrato = fechaContrato;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
