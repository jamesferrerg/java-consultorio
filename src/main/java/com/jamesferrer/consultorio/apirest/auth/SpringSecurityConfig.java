package com.jamesferrer.consultorio.apirest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	 
	// interfaz de springSecurity (UserDetailsService)
	@Autowired
	private UserDetailsService usuarioService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {

		return super.authenticationManager();
	}
	
	// Se configura primero en ResourceServerConfig
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		/* Se habilita el acceso a la ruta antMathcer("api/empleados"), para ademas permitir al metodo 
		 * (HttpMethod.GET,"/api/empleados")*/
		// el anyrequest() se ubica siempre al final, para todas las rutas(endpoints) que no se haya asignado permisos
		/* luego del and, el csfr desabilita el metodo de seguridad que evita los ataques que se realizan a traves 
		 * de los formularios*/
		/* deshabilitar el manejo de sesiones, ya que se manejara con web tokens - sessionManagement() - el STATELESS
		 * es sin estado */
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
