package tacs.tp.app.conf;

import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {
	public MyApplication() {
		register(new MyApplicationBinder());

		packages(true, "tacs.tp.app");
	}
}
