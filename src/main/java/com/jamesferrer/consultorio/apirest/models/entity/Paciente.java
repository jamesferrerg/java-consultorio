package com.jamesferrer.consultorio.apirest.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jamesferrer.consultorio.apirest.models.annotation.UniqueNumIdentPaciente;

@Entity
@Table(name = "pacientes")
public class Paciente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_paciente")
	private Long idPaciente;

	@Column(nullable = false)
	@NotEmpty(message = "\"nombre\" no puede estar vacio")
	@Size(min = 3, max = 50, message = "\"nombre\" debe tener un tamaño entre 3 y 50 caracteres")
	private String nombre;

	@Column(nullable = false)
	@NotEmpty(message = "\"apellido\" no puede estar vacio")
	@Size(min = 3, max = 50, message = "\"apellido\" debe tener un tamaño entre 3 y 50 caracteres")
	private String apellido;

	@NotNull(message = "\"fecha de nacimiento\" no puede estar vacio")
	@Column(name="fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	private Integer edad;

	@Email(message = "\"email\" no es un correo electronico")
	private String email;

	@NotEmpty(message = "\"direccion\" no puede estar vacio")
	@Column(nullable = false)
	private String direccion;

	private String barrio;

	private Long telefono;

	@Column(nullable=false)
	@NotNull(message="\"celular\" no puede estar vacio")
	private Long celular;

	private String ocupacion;

	private String aseguradora;
	
	@NotEmpty(message = "\"número de identificación\" no puede estar vacio")
	@Column(name="numero_identificacion", unique = true)
	@UniqueNumIdentPaciente(message="\"número de identificación\" ya existe!")
	private String numeroIdentificacion;
	
	@JoinColumn(name="tipo_identificacion_id")
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="\"tipo de identificación\" no puede estar vacio")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private TipoIdentificacion tipoIdentificacion;
	
	@JoinColumn(name="sexo_id")
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="\"sexo\" no puede estar vacio")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Sexo sexo;
	
	@JoinColumn(name="departamento_id")
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Departamento departamento;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="municipio_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Municipio municipio;
	
	private String observacion;
	
	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@PrePersist
	public void calculoEdad() {
		LocalDate date = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ahora = LocalDate.now();
		this.edad = Period.between(date, ahora).getYears();
	}

	public Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
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

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getAseguradora() {
		return aseguradora;
	}

	public void setAseguradora(String aseguradora) {
		this.aseguradora = aseguradora;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
