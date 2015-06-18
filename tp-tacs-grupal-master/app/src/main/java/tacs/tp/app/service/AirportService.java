package tacs.tp.app.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;



/**
* Servicio que provee informacion de las coordenadas de un Aeropuerto dado 
*/

public class AirportService 
{

	public double[] getCoordinatesFromAiport(String airportID) throws Exception
	{
		Response response = this.getcoordinatesFromAirportService(airportID);	   
		Document doc = this.parse(response);
		
		int latitudeDegree = Integer.parseInt(doc.getElementsByTagName("LatitudeDegree").item(0).getTextContent());
		int latitudeMinute = Integer.parseInt(doc.getElementsByTagName("LatitudeMinute").item(0).getTextContent());
		double latitudeSecond = Double.parseDouble(doc.getElementsByTagName("LatitudeSecond").item(0).getTextContent());
		String latitudeNpeerS = doc.getElementsByTagName("LatitudeNpeerS").item(0).getTextContent();
		
		int longitudeDegree = Integer.parseInt(doc.getElementsByTagName("LongitudeDegree").item(0).getTextContent());
		int longitudeMinute = Integer.parseInt(doc.getElementsByTagName("LongitudeMinute").item(0).getTextContent());
		double longitudeSeconds = Double.parseDouble(doc.getElementsByTagName("LongitudeSeconds").item(0).getTextContent());
		String longitudeEperW = doc.getElementsByTagName("LongitudeEperW").item(0).getTextContent();
		
		double latitude = this.DMSaDecimal(latitudeDegree, latitudeMinute, latitudeSecond, latitudeNpeerS);
		double longitude = this.DMSaDecimal(longitudeDegree, longitudeMinute, longitudeSeconds, longitudeEperW);
	
		return new double[]{latitude, longitude};
	}
	
	public Response getcoordinatesFromAirportService(String airportID)
	{
		Client client = ClientBuilder.newClient();		
		WebTarget webTarget = client.target("http://www.webservicex.com");		
		Response response = webTarget.path("/airport.asmx/getAirportInformationByAirportCode").
				queryParam("airportCode", airportID).
				request(MediaType.APPLICATION_XML).get();		
		return response;
	}
	
	public Document parse (Response response) throws Exception
	{
		String xml = response.readEntity(String.class);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		InputSource is = new InputSource();		
		is.setCharacterStream(new StringReader(xml));		    		
		Document document = documentBuilder.parse(is);	
		String rootElement =  document.getElementsByTagName("string").item(0).getTextContent();
		is.setCharacterStream(new StringReader(rootElement));		    		
		document = documentBuilder.parse(is);	
		document.getDocumentElement().normalize();
		return document;		
	}
	
	public double DMSaDecimal(int grados, int minutos, double segundos, String direccion) 
	{
	    double decimal = Math.signum(grados) * (Math.abs(grados) + (minutos / 60.0) + (segundos / 3600.0));
	    //reverse for south or west coordinates; north is assumed
	    if (direccion.equals("S") || direccion.equals("W")) {
	        decimal *= -1;
	    }
	    return decimal;
	}
		
}


