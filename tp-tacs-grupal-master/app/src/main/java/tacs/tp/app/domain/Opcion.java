package tacs.tp.app.domain;


import java.util.*;



import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representa un viaje
 * 
 * @see Segmento
 */
@XmlRootElement
public class Opcion {
	private Integer opcion;
	private String duracion;
	private List<Segmento> segmentos = new ArrayList<Segmento>();
	//Constructores
	public Opcion() {}

	//Getters y setters
	public List<Segmento> getSegmentos() {
		return this.segmentos;
	}

	public void setSegmentos(List<Segmento> segmentos) {
		this.segmentos = segmentos;
	}

	public void setOpcion(Integer opcion) {
		this.opcion = opcion;
	}

	public Integer getOpcion() {
		return this.opcion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getDuracion() {
		return this.duracion;
	}

}
