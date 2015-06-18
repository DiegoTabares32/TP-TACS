package tacs.tp.app.domain;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Representa un usuario
 *
 */
@XmlRootElement
@XmlType(propOrder={"id", "username","nombre","apellido","token","amigos"})
public class Usuario {
	private Integer id;
	private String token;
	private String username;
	private String nombre;
	private String apellido;
	private List<Usuario> amigos = new ArrayList<Usuario>();
	private List<Viaje> viajes = new ArrayList<Viaje>();
	
	//Constructores
	public Usuario() {}
	
	public Usuario(long id, String username, String nombre, String apellido, String token,ArrayList<Usuario> amigos) {
		this.id=(int) id;
		this.username = username;
		this.nombre = nombre;
		this.apellido = apellido;
		this.token = token;
		this.amigos = amigos; 
 	}
	
	public Usuario(int id, String username, String nombre, String apellido) {
		this.id=(int) id;
		this.username = username;
		this.nombre = nombre;
		this.apellido = apellido;
 	}

	//Getters y setters
	public void addAmigo(Usuario amigo){
		this.amigos.add(amigo);
	}
		
	public void setAmigos(List<Usuario> amigos){
		this.amigos = amigos;
	}

	public List<Usuario> getAmigos() {
		return this.amigos;
	}

	public void addViaje(Viaje viaje) {
		this.viajes.add(viaje);
	}

	public List<Viaje> getViajes() {
		return this.viajes;
	}

	public String getToken() {
		return this.token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
