package ar.com.codoacodo.controllers;

import ar.com.codoacodo.repository.*;
import jakarta.servlet.http.HttpServlet;

import java.time.LocalDate;
import java.util.List;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.interfaces.IOradorRepository;

public class NuevoOradorController extends HttpServlet{

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
		System.out.println("ok");
		
		
		
		
		/*repository.delete(6L);*/
		/*Date fechaPrueba = Date.valueOf(LocalDate.now());
		
		LocalDate fechaConvertida = fechaPrueba.toLocalDate();
		
		System.out.println("Fecha convertida "+fechaConvertida);
	
		System.out.println("Fecha Prueba "+fechaPrueba.toLocalDate().atStartOfDay(ZoneId.systemDefault()));
		
		/*.getDate(6).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();*/
		
		
		//interface i = new ClaseQueImplementa()..
		
		
		//Trae un orador de la base de datos
		/*Orador orador = repository.getById(1L);
		
		System.out.println(orador);
		
		//Insertar un orador con el metodo save() de la interface
		
		Orador nuevoOrador = new Orador("Carlita", "Pancha", "carlitaPancha@mail.com", "Que es la pachata", LocalDate.now());
		
		System.out.println(nuevoOrador.toString());
		
		repository.save(nuevoOrador);*/
		
				
		//Se elimina registro de la DB
		
		
		//Se despliega lista de oradores
		List<Orador> listado = repository.findAll();
		
		System.out.println(listado);
		
		//Elimino a Panchita!
		//repository.delete(12L);
		
		
		//Vuelvo a listar para ver que ya no esta Panchita
	//	System.out.println(repository.findAll().toString());
		
		
		
		//Se inserta otro orador con metodo save()
		
		//Orador nuevoOrador2 = new Orador("Mickey", "Donald", "mickeydonald@mail.com", "Que es disney", LocalDate.now());
		
		//System.out.println(nuevoOrador2.toString());
		
		//Lo guardo en la DB		
		//repository.save(nuevoOrador2);
		
		//Traigo los datos de Mickey y setteo el apellido
		Orador micky = repository.getById(13L);
		
		micky.setApellido("MouseDeLimon");
		
		//Actualizo en la BD
		repository.update(micky);
		
		//Vuelvo a listar los cambios
		System.out.println(repository.findAll());
		
		
	}
}