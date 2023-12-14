package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.interfaces.IOradorRepository;
import ar.com.codoacodo.repository.MySQLOradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/orador")
public class NuevoOradorController extends ControllerPrincipal {

	private IOradorRepository repository = new MySQLOradorRepository();

	// enviar por POST todos los datos desde el formulario en el front

	protected void doPost(HttpServletRequest request, // aca viene todos lo del front
			HttpServletResponse response) // aca va hacia el front
			throws ServletException, IOException {

		//Captura el id que viene en la url ?id=1
		String id = request.getParameter("id");//F8
		
		//Captura los datos nuevos del orador
		//Obtengo el json desde el frontend
		String json = super.toJson(request);
		
		//Convierto de json String a Objecto java usando libreria de jackson2
		OradorRequest oradorRequest = super.mapper.readValue(json, OradorRequest.class);

		// grabamos en db
		Orador orador = new Orador(oradorRequest.getNombre(), oradorRequest.getApellido(), oradorRequest.getMail(),
				oradorRequest.getTema(), LocalDate.now());

		repository.save(orador);

		response.setStatus(HttpServletResponse.SC_CREATED);// 201

		// convierto ahora Objeto java a String
		// enviar por medio de response al frontend
		response.getWriter().print(mapper.writeValueAsString(orador));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Orador> oradores = this.repository.findAll();

		// convierto de json String a Objecto java usando libreria de jackson2
		String jsonParaEnviarAlFrontend = super.mapper.writeValueAsString(oradores);

		response.setStatus(HttpServletResponse.SC_OK);

		// escribe la respueta en el objeto response (que despues es lo que recibe el front)
		response.getWriter().print(jsonParaEnviarAlFrontend);
	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		this.repository.delete(Long.parseLong(id));

		resp.setStatus(HttpServletResponse.SC_OK);// 200
	}

	
		protected void doPut(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		//capturar el id que viene en la url ?id=1
		String id = request.getParameter("id");//f8
		
		//captura los datos nuevos del orador
		//obtengo el json desde el frontend
		String json = super.toJson(request);//spring
		
		//convierto de json String a Objecto java usando libreria de jackson2
		OradorRequest oradorRequest = super.mapper.readValue(json, OradorRequest.class);
		
		//busco el orador en la db
		Orador orador = repository.getById(Long.parseLong(id));
		
		//actualizo los datos del orador con los nuevo que viene en el OradorRequest 	
		orador.setNombre(oradorRequest.getNombre());
		orador.setApellido(oradorRequest.getApellido()); 
		orador.setMail(oradorRequest.getMail()); 
		orador.setTema(oradorRequest.getTema()); 

		//ahora si actualizo en la db
		repository.update(orador);
		
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
