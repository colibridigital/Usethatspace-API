package api;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.engine.application.CorsFilter;
import org.restlet.routing.Router;

import java.util.Arrays;
import java.util.HashSet;

public class UTSApplication extends Application {

	/**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());

		CorsFilter corsFilter = new CorsFilter(getContext(), router);
		corsFilter.setAllowedOrigins(new HashSet(Arrays.asList("http://localhost:8000")));

		router.attach("/register", RegisterResource.class);
		router.attach("/login", LoginResource.class);

		//Driver
		router.attach("/driver/save", SaveProfileResource.class);
		router.attach("/driver/load", LoadProfileResource.class);

		//Business
		router.attach("/business/register", NewBusinessResource.class);

		return router;
	}

}