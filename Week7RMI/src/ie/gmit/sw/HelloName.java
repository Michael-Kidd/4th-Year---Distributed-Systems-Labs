package ie.gmit.sw;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloName {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello World!";
	}

	@GET
	@Path("/name/{value}")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(@PathParam("value") String value) {
		return "Hello " + value + "!";
	}
}
