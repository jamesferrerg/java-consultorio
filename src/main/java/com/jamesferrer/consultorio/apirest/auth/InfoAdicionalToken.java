package com.jamesferrer.consultorio.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.services.IEmpleadoService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{

	@Autowired
	private IEmpleadoService empleadoService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Empleado empleado = empleadoService.findByUsername(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("info_adicional", "Probando info: ".concat(authentication.getName()));
		info.put("nombre", empleado.getNombre() + " " + empleado.getApellido());
		info.put("foto", empleado.getFoto());
		info.put("idEmpleado", empleado.getIdEmpleado());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
