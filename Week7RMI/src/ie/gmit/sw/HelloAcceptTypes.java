package ie.gmit.sw;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/helloaccepttypes")
public class HelloAcceptTypes {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloPlain() {
		return "Hello World!";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHelloHTML() {
		return "<html><body><h1>Hello World!</h1></body></html>";
	}
}