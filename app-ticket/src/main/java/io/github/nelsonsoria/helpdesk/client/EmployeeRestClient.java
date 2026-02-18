package io.github.nelsonsoria.helpdesk.client;

import io.github.nelsonsoria.helpdesk.dto.employee.EmployeeDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/employees")
@RegisterRestClient(baseUri = "stork://employees-api")
public interface EmployeeRestClient {
    @GET
    @Path("/{id}")
    public EmployeeDto getEmployeeById(@PathParam("id") Long id);
}
