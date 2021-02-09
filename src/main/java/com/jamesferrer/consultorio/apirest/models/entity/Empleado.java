package com.jamesferrer.consultorio.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jamesferrer.consultorio.apirest.models.annotation.UniqueNumeroIdentificacion;

@Entity
@Table(name="empleados")
public class Empleado implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_empleado")
	private Integer idEmpleado;
	
	//No puede ser vacio 
	@Column(nullable=false)
	@NotEmpty(message = "\"nombre\" no puede estar vacio")
	@Size(min=3, max=50, message = "\"nombre\" debe tener un tamaño entre 3 y 50 caracteres")
	private String nombre;
	
	@Column(nullable=false)
	@NotEmpty(message = "\"apellido\" no puede estar vacio")
	private String apellido;
	
	@NotEmpty(message = "\"número de identificación\" no puede estar vacio")
	@Column(name="numero_Identificacion", nullable=false, unique=true)
	@UniqueNumeroIdentificacion(message="\"número de identificación\" ya existe!")
	private String numeroIdentificacion;
	
	private Long telefono;
	
	private Long celular;
	
	// acceso a traves jwt (Tabla=usuario)
	
	@Email(message = "\"email\" no es una dirección de correo bien formada")
	@Column(nullable=false)
	@NotEmpty(message="\"email\" no puede estar vacio")
	private String username;
	
	@Column(nullable=false, length=60)
	@NotEmpty(message="\"password o contraseña\" no puede estar vacio")
	private String password;
	
	private Boolean habilitado;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="empleados_roles", joinColumns=@JoinColumn(name="empleado_id"), 
	inverseJoinColumns=@JoinColumn(name="rol_id"),
	uniqueConstraints= {@UniqueConstraint(columnNames={"empleado_id", "rol_id"})})
	private List<Rol> roles;
	
	// fin jwt
	@NotNull(message = "\"fecha de contrato\" no puede estar vacio")
	@Column(name="fecha_Contrato")
	@Temporal(TemporalType.DATE)
	private Date fechaContrato;
	
	private String foto;
	
	@NotNull(message="\"tipo de identificación\" no puede estar vacio")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_Identificacion_Id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private TipoIdentificacion tipoIdentificacion;

	@NotNull(message="\"dirección\" no puede estar vacio")
	private String direccion;
	
	private String barrio;
	
	@NotNull(message="\"sexo\" no puede estar vacio")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sexo_Id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Sexo sexo;
	
	@NotNull(message="\"cargo\" no puede estar vacio")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cargo_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Cargo cargo;
	
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

	// acceso jwt
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	
	// end jwt

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

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
