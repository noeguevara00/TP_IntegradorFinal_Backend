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

	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		if(nombre != null) {
			this.nombre = nombre;
		}else {
			this.nombre = "N/D";
		}
	}
 
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		if(apellido != null) {
			this.apellido = apellido;
		}else {
			this.apellido = "N/D";
		}
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		if(mail != null) {
			this.mail = mail;
		}else {
			this.mail = "N/D";
		}
	}
	
	public String getTema() {
		return tema;
	}
	
	public void setTema(String tema) {
		if(tema != null) {
			this.tema = tema;
		}else {
			this.tema = "N/D";
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
		return "Orador [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail + ", tema="
				+ tema + ", fechaAlta=" + fechaAlta + "]";
	}
	
	
}