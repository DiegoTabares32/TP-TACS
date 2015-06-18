package tacs.tp.app.helpers;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Representa un viaje
 * 
 * @see Segmento
 */
@XmlRootElement
@XmlType(propOrder={"status", "message"})
public class CustomResponseObject {
	private String status;
	private String message;
	
	//Constructores
	public CustomResponseObject() {}
	
	//Getters y setters
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
