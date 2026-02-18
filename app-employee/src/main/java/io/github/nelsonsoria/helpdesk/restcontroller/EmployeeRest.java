package io.github.nelsonsoria.helpdesk.restcontroller;


import io.github.nelsonsoria.helpdesk.Service.IEmployeeService;
import io.github.nelsonsoria.helpdesk.client.TicketRestClient;
import io.github.nelsonsoria.helpdesk.dto.employee.EmployeeDTO;
import io.github.nelsonsoria.helpdesk.dto.employee.EmployeeSummaryDto;
import io.github.nelsonsoria.helpdesk.dto.tickets.TicketStatsDto;
import io.github.nelsonsoria.helpdesk.repository.EmployeeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
@Transactional
@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeRest {


    @Inject
    private IEmployeeService employeeService;

    @GET
    public List<EmployeeDTO> getAll (){
        return employeeService.getAll();
    }
    @GET
    @Path("/{id}")
    public EmployeeDTO getEmployeeById(@PathParam("id") Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }
    @GET
    @Path("/{id}/summary")
    public EmployeeSummaryDto getEmployeeSummary(@PathParam("id") Long employeeId){
        return employeeService.getEmployeeSummary(employeeId);
    }

    @POST
    public Response saveEmployee(EmployeeDTO dto){
        var created = this.employeeService.saveEmployee(dto);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

}
