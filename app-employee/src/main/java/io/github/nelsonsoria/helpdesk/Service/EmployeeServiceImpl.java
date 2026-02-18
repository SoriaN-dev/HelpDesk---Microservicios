package io.github.nelsonsoria.helpdesk.Service;

import io.github.nelsonsoria.helpdesk.client.TicketRestClient;
import io.github.nelsonsoria.helpdesk.db.Employee;
import io.github.nelsonsoria.helpdesk.dto.employee.EmployeeDTO;
import io.github.nelsonsoria.helpdesk.dto.employee.EmployeeSummaryDto;
import io.github.nelsonsoria.helpdesk.dto.tickets.LastTicketDto;
import io.github.nelsonsoria.helpdesk.dto.tickets.TicketStatsDto;
import io.github.nelsonsoria.helpdesk.dto.tickets.TicketSummaryDto;
import io.github.nelsonsoria.helpdesk.repository.EmployeeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Transactional
@ApplicationScoped
public class EmployeeServiceImpl implements  IEmployeeService{

    @Inject
    private EmployeeRepository employeeRepository;

    @Inject
    @RestClient
    private TicketRestClient restClient;

    @Override
    public List<EmployeeDTO> getAll() {
        return  employeeRepository.listAll()
                .stream()
                .map( it -> EmployeeDTO.builder()
                        .name(it.getName())
                        .email(it.getEmail())
                        .build()
                ).toList();
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        var employee = employeeRepository.findById(employeeId);
        if (employee == null) {
            throw new RuntimeException("Empleado no encontrado con id: " + employeeId);
        }
        return EmployeeDTO.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .build();
    }

    @Override
    public EmployeeSummaryDto getEmployeeSummary(Long employeeId) {
        var employee = employeeRepository.findById(employeeId);
        var employeedto = EmployeeDTO.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .build();
        TicketStatsDto ticketStats = restClient.getTicketSummary(employee.getId());
        LastTicketDto lastTicket= restClient.getLastTicket(employee.getId());
        var ticketSummary = TicketSummaryDto.builder()
                .stats(ticketStats)
                .last(lastTicket)
                .build();
        return EmployeeSummaryDto.builder()
                .employee(employeedto)
                .tickets(ticketSummary)
                .build();
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO dto) {
        var employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employeeRepository.persist(employee);

        return EmployeeDTO.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .build();
    }
}
