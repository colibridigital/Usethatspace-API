package api;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class UTSApplication extends Application {

	/**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());

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