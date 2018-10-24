package ie.gmit.sw;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/calculate")
public class CalculateResource {

	@GET
	@Path("/add/{value1}/{value2}")
	@Produces(MediaType.TEXT_HTML)
	public Response calcAddTwoValues(@PathParam("value1")  double value1, @PathParam("value2")  double value2) {
		double answer = value1 + value2;
		String msg = String.format("calcAddTwoValues==> value1: %10.4f, value2: %10.4f, answer: %10.4f", value1, value2, answer);    
		return Response.status(200).entity(msg).build();
	}

	@GET
	@Path("/subtract/{value1}/{value2}")
	@Produces(MediaType.TEXT_HTML)
	public Response calcSubTwoValues(@PathParam("value1")  double value1, @PathParam("value2")  double value2) {
		double answer = value1 - value2;
		String msg = String.format("calcSubTwoValues==> value1: %10.4f, value2: %10.4f, answer: %10.4f", value1, value2, answer);    
		return Response.status(200).entity(msg).build();
	}

	@GET
	@Path("/squareroot/{value}")
	@Produces(MediaType.TEXT_HTML)
	public Response calcSqrtHTML(@PathParam("value") double value) {
		double answer = Math.sqrt(value);
		String msg = String.format("calcSqrt==> value: %f, answer: %10.4f", value, answer);    
		return Response.status(200).entity(msg).build();
	}
}