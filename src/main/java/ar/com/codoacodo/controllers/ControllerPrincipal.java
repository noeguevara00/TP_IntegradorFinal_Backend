package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

public class ControllerPrincipal extends HttpServlet{

	//Convierto de json String a Objecto java usando libreria de jackson2
	protected ObjectMapper mapper = new ObjectMapper();//convierto el mapper en atributo protegido (privado solo para la clase hija)

	public ControllerPrincipal() {//setteo en un constructor lo que necesito del mapper
		super();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
	
	// obtengo el json desde el frontend
	protected String toJson(HttpServletRequest request) throws IOException {
		//Capturamos desde el request en un json, capturamos o pedimos las lineas y le pedimos que recolecte como String
		String json = request.getReader()
		.lines().collect(Collectors.joining(System.lineSeparator()));// spring
		return json;
	}
}
