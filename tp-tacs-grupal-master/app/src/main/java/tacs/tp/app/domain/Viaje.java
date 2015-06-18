package tacs.tp.app.domain;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Representa un viaje
 * 
 * @see Segmento
 */
@XmlRootElement
@XmlType(propOrder={"from", "to", "departure_date", "return_date", "precio", "outbound", "inbound"})
public class Viaje {
	private String from;
	private String to;
	private String departure_date;
	private String return_date;
	private double precio;
	private Opcion outbound;
	private Opcion inbound;
	
	//Constructores
	public Viaje() {}
	
	//Getters y setters
	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return this.to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getDeparture_date() {
		return this.departure_date;
	}

	public void setDeparture_date(String departure_date) {
		this.departure_date = departure_date;
	}

	public String getReturn_date() {
		return this.return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	public double getPrecio(){
		return this.precio;
	}	

	public void setPrecio(double precio){
		this.precio = precio;
	}

	public Opcion getOutbound() {
		return this.outbound;
	}

	public void setOutbound(Opcion opcion) {
		this.outbound = opcion;
	}

	public Opcion getInbound() {
		return this.inbound;
	}

	public void setInbound(Opcion opcion) {
		this.inbound = opcion;
	}
	
}
