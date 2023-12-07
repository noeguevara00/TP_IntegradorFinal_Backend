package ar.com.codoacodo.repository;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.interfaces.IOradorRepository;

public class MySQLOradorRepository implements IOradorRepository {

	// implementar todos los metodos de la interface

	@Override
	public void save(Orador orador) {
		String sql = "insert into oradores (nombre, apellido, mail, tema, fecha_alta) values(?,?,?,?,?)";

		try (Connection conn = AdministradorDeConexiones.getConnection()) {// para que conecte desconecte, o sea abrir la conexion y cerrarla automaticamente, solo se pasa
																			// dentro del try
			// sql injection!!!
			PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			// cargar los ? con los valores
			statement.setString(1, orador.getNombre());
			statement.setString(2, orador.getApellido());
			statement.setString(3, orador.getMail());
			statement.setString(4, orador.getTema());
			/* statement.setDate(5, new java.sql.Date(System.currentTimeMillis())); */ // tph: ver como pasar de
																						// LocalDate a java.sql.Date
			statement.setDate(5, Date.valueOf(LocalDate.now()));

			statement.executeUpdate();// INSERT,UPDATE,DELETE
			System.out.println("Registro cargado en DB");
			
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo crear el orador", e);
		}
	}

	@Override
	public Orador getById(Long id) {
		String sql = "select id_orador, nombre, apellido, tema, mail, fecha_alta from oradores where id_orador = ?";

		Orador orador = null;
		try (Connection conn = AdministradorDeConexiones.getConnection()) {// para que conecte desconecte solo se pasa
																			// dentro del try
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, id);

			ResultSet res = statement.executeQuery();// SELECT

			// hay datos?
			if (res.next()) {
				// obtengo los datos desde el ResultSet
				Long id_orador = res.getLong(1);
				String nombre = res.getString(2);
				String apellido = res.getString(3);
				String tema = res.getString(4);
				String mail = res.getString(5);
				/* LocalDate fechaAlta = res.getDate(6); */
				
				LocalDate fecha_alta = res.getDate(6).toLocalDate();
				

				orador = new Orador(id, nombre, apellido, mail, tema, fecha_alta);
				
				System.out.println("Registro "+id+" obtenido");

			}
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo obtener el orador", e);
		}
		return orador;
	}

	@Override
	public void update(Orador orador) {
		String sql = "update oradores set nombre = ?, apellido = ?, mail = ?, tema = ? where id_orador = ?";
		
		// try with resource
		try (Connection conn = AdministradorDeConexiones.getConnection()) {// para que conecte desconecte solo se pasa
																			// dentro del try
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, orador.getNombre());
			statement.setString(2, orador.getApellido());			
			statement.setString(3, orador.getMail());
			statement.setString(4, orador.getTema());
			statement.setLong(5, orador.getId());

			statement.executeUpdate();
			System.out.println("Registro "+orador.getId()+" actualizado");
			
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo actualizar el orador", e);
		}
	}

	@Override
	public void delete(Long id) {
		String sql = "delete from oradores where id_orador = ?";

		try (Connection conn = AdministradorDeConexiones.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sql);
			
			//se settea el id
			statement.setLong(1, id);
			 
			statement.executeUpdate();//para ejecutar INSERT, UPDATE Y DELETE
			System.out.println("Registro "+id+" eliminado");
			
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo eliminar el orador", e);
		}
	}

	@Override
	public List<Orador> findAll() {
		String sql = "select id_orador, nombre, apellido, tema, mail, fecha_alta from oradores";

		List<Orador> oradores = new ArrayList<>();// ver bien power en Spring!
		try (Connection conn = AdministradorDeConexiones.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sql);

			ResultSet res = statement.executeQuery();// SELECT

			// hay datos?
			while (res.next()) {
				// obtengo los datos desde el ResultSet
				Long _id = res.getLong(1);
				String nombre = res.getString(2);
				String apellido = res.getString(3);
				String tema = res.getString(4);
				String mail = res.getString(5);
				Date fechaAlta = res.getDate(6);

				Orador orador = new Orador(_id, nombre, apellido, mail, tema, LocalDate.now());/* tph fechaAlta de java.sql.Date a LocalDate */
				oradores.add(orador);
				
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo obtener el orador", e);
		}

		return oradores;
	}

	// implementar todos los metodos de la interface
	

}

