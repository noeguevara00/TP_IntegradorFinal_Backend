package ar.com.codoacodo.entity;

import java.time.LocalDate;

public class Orador {

	/*atributos que representan los campos de la tabla oradores en la db*/
	private Long id;
	private String nombre;
	private String apellido;
	private String mail;
	private String tema;
	private LocalDate fechaAlta;
	
	//usado cuando quiera enviar un orador a la db
        //alt+shift+s
	public Orador(String nombre, String apellido, String mail, String tema, LocalDate fechaAlta) {
		this.setNombre(nombre);
		this.setApellido(apellido);;
		this.setMail(mail);
		this.setTema(tema);
		this.setFechaAlta(fechaAlta);
	}
	//cuando quiera traer un orador desde la db
	public Orador(Long id, String nombre, String apellido, String mail, String tema, LocalDate fechaAlta) {
		this.id = id;
		this.setNombre(nombre);
		this.setApellido(apellido);;
		this.setMail(mail);
		this.setTema(tema);
		this.setFechaAlta(fechaAlta);
	}
	//anulo el constructor por defecto
	/*contructor*/
	//alt+shit+s
	
	//agregamos getters/setters
	//un setter es un metodo que permite modificar
	//el contenido de UN SOLO atributos
	
        
        //automaticamente creo los getters y setter con ctrl + shift + s
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id= id;
		
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		if(nombre == null || nombre.isEmpty()) {
			this.nombre = "N/D";
		}else {
			this.nombre = nombre;
		}
	}
 
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		if(apellido == null || apellido.isEmpty()) {
			this.apellido = "N/D";
		}else {
			this.apellido = apellido;
		}
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		if(mail == null || mail.isEmpty()) {
			this.mail = "N/D";
		}else {
			this.mail = mail;
		}
	}
	
	public String getTema() {
		return tema;
	}
	
	public void setTema(String tema) {
		if(tema == null || tema.isEmpty()) {
			this.tema = "N/D";
		}else {
			this.tema = tema;
		}
	}
	
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	@Override
	public String toString() {
		return "\nOrador [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail + ", tema="
				+ tema + ", fechaAlta=" + fechaAlta + "]\n";
	}

	
	
}