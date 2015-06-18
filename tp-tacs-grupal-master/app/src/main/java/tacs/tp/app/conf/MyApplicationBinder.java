package tacs.tp.app.conf;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import tacs.tp.app.helpers.*;
import tacs.tp.app.service.*;


public class MyApplicationBinder extends AbstractBinder {
	
	@Override
	protected void configure() {
		bind(DespegarApiClient.class).to(DespegarApiClient.class);
		bind(GeoObjectFactory.class).to(GeoObjectFactory.class);
		bind(AirportService.class).to(AirportService.class);
		bind(KeyManager.class).to(KeyManager.class);
		bind(GeoPath.class).to(GeoPath.class);
	}
}
