package tacs.tp.app.domain;

import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Representa un viaje
 * 
 * @see Segmento
 */
@XmlRootElement
@XmlType(propOrder={"identificador", "precio", "outbound", "inbound"})
public class Itinerario {
	private Integer identificador;
	private double precio;
	private List<Opcion> outbound = new ArrayList<Opcion>();
	private List<Opcion> inbound = new ArrayList<Opcion>();
	//Constructores
	public Itinerario() {}

	//Getters y setters
	public Integer getIdentificador() {
		return this.identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}

	public double getPrecio(){
		return this.precio;
	}	

	public void setPrecio(double precio){
		this.precio = precio;
	}

	public List<Opcion> getInbound() {
		return this.inbound;
	}

	public void setInbound(List<Opcion> opciones) {
		this.inbound = opciones;
	}

	public List<Opcion> getOutbound() {
		return this.outbound;
	}

	public void setOutbound(List<Opcion> opciones) {
		this.outbound = opciones;
	}

}
