package ar.com.codoacodo.controllers;

import ar.com.codoacodo.repository.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.interfaces.IOradorRepository;

public class Test extends HttpServlet{
	
	public static void main(String[] args) {

	/*
		String nombre = "Noe";
		String apellido = "Gue";
		String email = "noegue@mail.com";
		String tema = "temaX";
	
		//validaciones
		/*if(nombre == null || apellido == null || email == null || tema == null) {
			//response json de error
		}*/
		
		
		IOradorRepository repository = new MySQLOradorRepository();
		
		
		//respondo al frontend con un json ok
		System.out.println("Connection ok");
		
		
		
		
		/*repository.delete(6L);
		Date fechaPrueba = Date.valueOf(LocalDate.now());
		
		LocalDate fechaConvertida = fechaPrueba.toLocalDate();
		
		System.out.println("Fecha convertida "+fechaConvertida);
	
		System.out.println("Fecha Prueba "+fechaPrueba.toLocalDate().atStartOfDay(ZoneId.systemDefault()));
		
		.getDate(6).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();*/
		
		
		//interface i = new ClaseQueImplementa()..
		
		
		//Trae un orador de la base de datos
//		Orador orador = repository.getById(1L);
//		
//		System.out.println(orador);
		
		
		//Insertar un orador con el metodo save() de la interface
		
//		Orador nuevoOrador = new Orador("Carlita", "Pancha", "carlitaPancha@mail.com", "Que es la pachata", LocalDate.now());
//		
//		System.out.println(nuevoOrador.toString());
//		
//		repository.save(nuevoOrador);
		
				
		//Se elimina registro Panchita de la DB!
	//	repository.delete(19L);
		
		//Se despliega lista de oradores para ver que ya no esta Panchita
		
	  List<Orador> listado = repository.findAll();
//		  
//		  listarRegitros(listado);
		 
		
		//Se inserta otro orador con metodo save()
		
//		Orador nuevoOrador3 = new Orador("", "Donald", "mickeydonald4@mail.com", "Que es disney", LocalDate.now());
//		
//		System.out.println(nuevoOrador3.toString());
//		
//		//Lo guardo en la DB		
//		repository.save(nuevoOrador3);
		
//		repository.delete(21L);
		
		//Traigo los datos de ND Donal y setteo el nombre
		
		Orador donaldND = repository.getById(23L);
		
		donaldND.setNombre("Duck");
		
		//Actualizo en la BD
		repository.update(donaldND);
	
		
		//Vuelvo a listar los cambios
		listarRegitros(listado);
		
		
	}
	
	public static void listarRegitros(List listado) {
		System.out.println("Total de registros: "+listado.size()+"\n-----------------------------------\n"
				+ ""+listado);
	}
}
