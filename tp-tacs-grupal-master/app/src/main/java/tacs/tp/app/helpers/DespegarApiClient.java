package tacs.tp.app.helpers;

import tacs.tp.app.domain.*;

import java.io.StringReader;
import java.util.*;
import javax.json.*;
import javax.ws.rs.client.*;

import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ProcessingException;

import javax.inject.Inject;

public class DespegarApiClient {

	@Inject KeyManager keyManager;

	private	String despegarApiUrlRoot = "https://api.despegar.com/v3";

	public String getString(String site, String from, String to, String departure_date, String return_date) {
		String despegarApiUrl = "/flights/itineraries";
		Client client = ClientBuilder.newClient();
		client.property(ClientProperties.READ_TIMEOUT, 0);
		client.property(ClientProperties.CONNECT_TIMEOUT, 0);
		WebTarget target = client.target(this.despegarApiUrlRoot);

		try {
			String response = target
													.path(despegarApiUrl)
													.queryParam("site",site)
													.queryParam("from",from)
													.queryParam("to",to)
													.queryParam("departure_date",departure_date)
													.queryParam("adults","1")
													.queryParam("return_date",return_date)
	
													.request()
													.header("X-ApiKey", keyManager.getDespegarApiKey())
													.get(String.class);
			return response;		
		} catch(ProcessingException e) {
				CustomResponseObject errorObject = new CustomResponseObject();
				errorObject.setStatus("503");
				errorObject.setMessage("No se pudo contactar al API de Despegar");
				Response resp = Response.status(503).entity(errorObject).build();
				throw new WebApplicationException(resp);
		}
		
	}

	public List<Itinerario> getItinerarios(String site, String from, String to, String departure_date, String return_date) {

		List<Itinerario> itinerarios = new ArrayList<Itinerario>();

		String response = this.getString(site, from, to, departure_date, return_date);

		JsonReader jsonReader = Json.createReader(new StringReader(response));
		JsonObject fullResponseObject = jsonReader.readObject();
		jsonReader.close();

		JsonArray itemsArray = fullResponseObject.getJsonArray("items");
		Integer identificador = 0;

		for (JsonValue itinerarioValue : itemsArray) {

			Itinerario itinerario = new Itinerario();

			JsonObject itinerarioObject = (JsonObject) itinerarioValue;
			JsonObject precioObject = (JsonObject) itinerarioObject.getJsonObject("price_detail"); 
			itinerario.setPrecio((double) precioObject.getInt("total"));
			itinerario.setOutbound(this.getChoices(itinerarioObject.getJsonArray("outbound_choices")));
			itinerario.setInbound(this.getChoices(itinerarioObject.getJsonArray("inbound_choices")));

			itinerario.setIdentificador(identificador);
			identificador++;

			itinerarios.add(itinerario);

		}

		return itinerarios;
}

private List<Opcion> getChoices(JsonArray choicesArray) {

		List<Opcion> opciones = new ArrayList<Opcion>();

		for (JsonValue choiceValue : choicesArray) {

			JsonArray segmentArray = ((JsonObject) choiceValue).getJsonArray("segments");

			List<Segmento> segmentos = new ArrayList<Segmento>();

			for (JsonValue segmentValue : segmentArray) {

				Segmento segmento = new Segmento();

				segmento.setFrom(((JsonObject) segmentValue).getString("from"));
				segmento.setTo(((JsonObject) segmentValue).getString("to"));
				segmento.setAirline(((JsonObject) segmentValue).getString("airline"));
				segmento.setFlight_id(((JsonObject) segmentValue).getString("flight_id"));
				segmento.setCabin_type(((JsonObject) segmentValue).getString("cabin_type"));
				segmentos.add(segmento);

			}

			Opcion opcion = new Opcion();

			opcion.setOpcion(((JsonObject) choiceValue).getInt("choice",0));
			opcion.setDuracion(((JsonObject) choiceValue).getString("duration"));
			opcion.setSegmentos(segmentos);

			opciones.add(opcion);
		}

		return opciones;
	}
}
