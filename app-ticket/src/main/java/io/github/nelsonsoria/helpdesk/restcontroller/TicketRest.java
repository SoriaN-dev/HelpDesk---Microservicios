package io.github.nelsonsoria.helpdesk.restcontroller;

import io.github.nelsonsoria.helpdesk.dto.ticket.LastTicketDto;
import io.github.nelsonsoria.helpdesk.dto.ticket.TicketDetailsDto;
import io.github.nelsonsoria.helpdesk.dto.ticket.TicketAssetDto;
import io.github.nelsonsoria.helpdesk.dto.ticket.TicketStatusDto;
import io.github.nelsonsoria.helpdesk.service.ITicketService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


import java.util.List;

@Path("/tickets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
@ApplicationScoped
public class TicketRest {

    @Inject
    private ITicketService ticketService;

    @GET
    public List<TicketAssetDto> getAll(){
        return ticketService.getAllTicketWithAsset();
    }
    @GET
    @Path("/employees/{employeeId}")
    public List<TicketAssetDto> getTicketForEmployee(@PathParam("employeeId") Long employeeId){
        return ticketService.getTicketForEmployee(employeeId);
    }

    @GET
    @Path("/{ticketId}")
    public TicketDetailsDto getTicketDetails(@PathParam("ticketId") Long ticketId){
        return ticketService.getTicketDetails(ticketId);
    }
    @GET
    @Path("/{employeeId}/summary")
    public TicketStatusDto getTicketSummary(@PathParam("employeeId") Long employeeId) {
        return ticketService.getTicketSummary(employeeId);
    }
    @GET
    @Path("/{employeeId}/last-update")
    public LastTicketDto getLastTicket(@PathParam("employeeId") Long employeeId){
        return  ticketService.getLastTicket(employeeId);
    }

}
