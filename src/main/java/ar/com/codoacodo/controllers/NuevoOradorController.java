package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.repository.MySQLOradorRepository;
import ar.com.codoacodo.interfaces.IOradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/api/orador")
public class NuevoOradorController extends HttpServlet{
	
	private IOradorRepository repository = new MySQLOradorRepository();		
	
	//enviar por POST todos los datos desde el formulario en el front
	
	protected void doPost(
			HttpServletRequest request,//aca viene todos lo del front 
			HttpServletResponse response)  //aca va hacia el front
					throws ServletException, IOException {
		
		//obtengo el json desde el frontend
		String json = request.getReader()//capturamos desde el request n un json, capturamos o pedimos las lineas y le pedimos que recolecte como 
				.lines()
				.collect(Collectors.joining(System.lineSeparator()));//spring
		
		//convierto de json String a Objecto java usando libreria de jackson2
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		OradorRequest oradorRequest = mapper.readValue(json, OradorRequest.class);
		
		//grabamos en db	
		Orador orador = new Orador(oradorRequest.getNombre(), 
				oradorRequest.getApellido(), 
				oradorRequest.getMail(), 
				oradorRequest.getTema(), 
				LocalDate.now());
		
		repository.save(orador);
		
		response.setStatus(HttpServletResponse.SC_CREATED);//201
		
		//convierto ahora Objeto java a String
		//enviar por medio de response al frontend
		response.getWriter().print(mapper.writeValueAsString(orador));
	}

	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		List<Orador> oradores = this.repository.findAll();
		
		//convierto de json String a Objecto java usando libreria de jackson2
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		String jsonParaEnviarAlFrontend = mapper.writeValueAsString(oradores);
		
		response.setStatus(HttpServletResponse.SC_OK);

		//escribe la respueta en el objeto response (que despues es lo que recibe el front)
		response.getWriter().print(jsonParaEnviarAlFrontend );
	}
	
	protected void doDelete(
			HttpServletRequest req, 
			HttpServletResponse resp) 
		throws ServletException, IOException {
		
		String id = req.getParameter("id");
		
		this.repository.delete(Long.parseLong(id));
		
		resp.setStatus(HttpServletResponse.SC_OK);//200
	}
	
	//Preflight
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin","*");		
		response.addHeader("Access-Control-Allow-Methods","POST, GET, OPTIONS, DELETE, HEAD");	
		response.addHeader("Access-Control-Allow-Headers","X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");	
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.doPut(req, resp);
	}
}
