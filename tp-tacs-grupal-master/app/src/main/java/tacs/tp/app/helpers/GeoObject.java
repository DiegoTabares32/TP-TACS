package tacs.tp.app.helpers;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Representa una ubicacion geografica
 * 
 * @see Segmento
 */
@XmlRootElement
@XmlType(propOrder={"latitude", "longitude"})
public class GeoObject {
	private Double latitude;
	private Double longitude;
	
	//Constructores
	public GeoObject() {}
	
	//Getters y setters
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


}
