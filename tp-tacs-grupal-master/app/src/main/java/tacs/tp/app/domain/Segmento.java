package tacs.tp.app.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Segmento de un viaje
 * 
 * @see Viaje
 */

@XmlRootElement
@XmlType(propOrder={"from", "to", "airline", "flight_id", "cabin_type"})
public class Segmento {
	private String from;
	private String to;
	private String airline;
	private String flight_id;
	private String cabin_type;
	
	//Constructores
	public Segmento() {}

	
	//Getters y setters
	public String getFrom() {
		return this.from;
	}
		
	public String getTo() {
		return this.to;
	}

	public String getAirline() {
		return this.airline;
	}

	public String getCabin_type() {
		return this.cabin_type;
	}

	public String getFlight_id() {
		return this.flight_id;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public void setFlight_id(String flight_id) {
		this.flight_id = flight_id;
	}

	public void setCabin_type(String cabin_type) {
		this.cabin_type = cabin_type;
	}

}
