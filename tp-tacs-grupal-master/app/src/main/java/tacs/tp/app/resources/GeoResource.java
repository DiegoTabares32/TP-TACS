package tacs.tp.app.resources;

import tacs.tp.app.helpers.*;
import tacs.tp.app.service.*;


import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;

import javax.inject.Inject;

import java.util.List;
import java.util.ArrayList;

@Path("/geo")
public class GeoResource {

	@Inject GeoObjectFactory goFactory;
	@Inject AirportService as;
	@Inject GeoPath gp;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public GeoObject doGeoObject(@DefaultValue("") @QueryParam("airport_code") String airport_code) {
		return this.getGeoObject(airport_code);
	}


	@GET @Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public GeoPath doGetGeoPath(@QueryParam("airport_codes") List<String> airport_codes) {
		List<GeoObject> path = new ArrayList<GeoObject>();

		for (String airport_code : airport_codes) {
			path.add(this.getGeoObject(airport_code));
		}
		
		gp.setPath(path);
		return gp;
	}

	public GeoObject getGeoObject(String airport_code) {
		
		double[] coord;

		GeoObject go = goFactory.createGeoObject();

		try {
			coord =  as.getCoordinatesFromAiport(airport_code);
		} catch (Exception e) {
			CustomResponseObject responseObject = new CustomResponseObject();
			responseObject.setStatus("500");
			responseObject.setMessage("Problema al obtener las coordenadas");
			Response response = Response.status(500).entity(responseObject).build(); 
			throw new WebApplicationException(response);
		}

		go.setLatitude(coord[0]);
		go.setLongitude(coord[1]);

		System.out.println("go.getLatitude: " + go.getLatitude());
		System.out.println("go.getLongitude: " + go.getLongitude());

		return go;
	}

}
