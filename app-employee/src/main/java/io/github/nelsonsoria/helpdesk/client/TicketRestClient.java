package io.github.nelsonsoria.helpdesk.client;

import io.github.nelsonsoria.helpdesk.dto.tickets.LastTicketDto;
import io.github.nelsonsoria.helpdesk.dto.tickets.TicketStatsDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/tickets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(baseUri = "stork://tickets-api")
public interface TicketRestClient {
    @GET
    @Path("/{employeeId}/summary")
    public TicketStatsDto getTicketSummary(@PathParam("employeeId") Long employeeId);

    @GET
    @Path("/{employeeId}/last-update")
    public LastTicketDto getLastTicket(@PathParam("employeeId") Long employeeId);

}
