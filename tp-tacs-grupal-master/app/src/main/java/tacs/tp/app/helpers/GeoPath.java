package tacs.tp.app.helpers;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.util.List;

/**
 * Representa un recorrido
 * 
 * @see Segmento
 */
@XmlRootElement
public class GeoPath {
	private List<GeoObject> path;
	
	//Constructores
	public GeoPath() {}
	
	//Getters y setters
	public List<GeoObject> getPath() {
		return this.path;
	}

	public void setPath(List<GeoObject> path) {
		this.path = path;
	}

}
