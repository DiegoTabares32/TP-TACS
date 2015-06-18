package tacs.tp.app.resources;

import tacs.tp.app.helpers.*;

import java.util.List;

import tacs.tp.app.domain.*;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import javax.inject.Inject;

@Path("/itinerarios")
public class ItinerariosResource {

/*	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Itinerario> doGetItinerarios() {
		return new DespegarApiClient().getItinerarios("ar", "BUE", "MIA", "2015-08-21", "2015-12-21");
	}
*/

	@Inject DespegarApiClient despegarApiClient;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Itinerario> doGetItinerarios(
																	@DefaultValue("ar") @QueryParam("site") String site,
																	@DefaultValue("") @QueryParam("from") String from,
																	@DefaultValue("") @QueryParam("to") String to,
																	@DefaultValue("") @QueryParam("departure_date") String departure_date,
																	@DefaultValue("") @QueryParam("return_date") String return_date) {
		return despegarApiClient.getItinerarios(site, from, to, departure_date, return_date);
	}

}
