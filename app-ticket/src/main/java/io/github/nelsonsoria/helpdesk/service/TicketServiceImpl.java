package io.github.nelsonsoria.helpdesk.service;

import io.github.nelsonsoria.helpdesk.client.AssetRestClient;
import io.github.nelsonsoria.helpdesk.client.EmployeeRestClient;
import io.github.nelsonsoria.helpdesk.dto.employee.CommentDto;
import io.github.nelsonsoria.helpdesk.dto.status.StatusHistoryDto;
import io.github.nelsonsoria.helpdesk.dto.ticket.*;
import io.github.nelsonsoria.helpdesk.enums.Priority;
import io.github.nelsonsoria.helpdesk.enums.Status;
import io.github.nelsonsoria.helpdesk.repository.TicketRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Transactional
@ApplicationScoped
public class TicketServiceImpl implements ITicketService{
    @Inject
    private TicketRepository ticketRepository;

    @Inject
    @RestClient
    private EmployeeRestClient employeeRestClient;

    @Inject
    @RestClient
    private AssetRestClient assetRestClient;


    @Override
    public TicketDetailsDto getTicketDetails(Long ticketid) {
        var ticket = ticketRepository.findById(ticketid);
        var employe= employeeRestClient.getEmployeeById(ticket.getEmployee());

        return TicketDetailsDto.builder()
                        .id(ticket.getId())
                        .title(ticket.getTitle())
                        .description(ticket.getDescription())
                        .employee(employe)
                        .comments(ticket.getTicketComments()
                                .stream()
                                .map(it -> CommentDto.builder()
                                        .comment(it.getComment())
                                        .createdAt(it.getCreatedAt())
                                        .build()).toList())

                        .statusHistories(ticket.getTicketStatusHistories()
                                .stream()
                                .map(it -> StatusHistoryDto.builder()
                                        .oldStatus(it.getOldStatus())
                                        .newStatus(it.getNewStatus())
                                        .changedAt(it.getChangedAt())
                                        .build()).toList())
                        .build();
    }

    @Override
    public List<TicketAssetDto> getAllTicketWithAsset() {
        return ticketRepository.listAll()
                .stream()
                .map(it -> TicketAssetDto.builder()
                        .id(it.getId())
                        .title(it.getTitle())
                        .description(it.getDescription())
                        .status(it.getStatus())
                        .priority(it.getPriority())
                        .employee(it.getEmployee())
                        .asset(assetRestClient.getAssetById(it.getAsset()))
                        .createdAt(it.getCreatedAt())
                        .build()
                )
                .toList();
    }
    @Override
    public List<TicketDTO> getAll() {
        return ticketRepository.listAll()
                .stream()
                .map(it -> TicketDTO.builder()
                        .id(it.getId())
                        .title(it.getTitle())
                        .description(it.getDescription())
                        .status(it.getStatus())
                        .priority(it.getPriority())
                        .employee(it.getEmployee())
                        .asset(it.getAsset())
                        .createdAt(it.getCreatedAt())
                        .build()
                )
                .toList();
    }

    @Override
    public List<TicketAssetDto> getTicketForEmployee(Long employeeId) {

        return ticketRepository.list("id",employeeId)
                .stream()
                .map(it -> TicketAssetDto.builder()
                        .id(it.getId())
                        .title(it.getTitle())
                        .description(it.getDescription())
                        .status(it.getStatus())
                        .priority(it.getPriority())
                        .employee(it.getEmployee())
                        .asset(assetRestClient.getAssetById(it.getAsset()))
                        .createdAt(it.getCreatedAt())
                        .build()
                )
                .toList();
    }

    @Override
    public TicketStatusDto getTicketSummary(Long employeeId) {
         return TicketStatusDto.builder()
                 .open(ticketRepository.countByStatus(employeeId, Status.OPEN))
                 .inProgress(ticketRepository.countByStatus(employeeId,Status.IN_PROGRESS))
                 .closed(ticketRepository.countByStatus(employeeId,Status.CLOSED))
                 .highPriority(ticketRepository.countByPriority(employeeId, Priority.HIGH))
                 .build();
    }

    @Override
    public LastTicketDto getLastTicket(Long employeeId) {
        return ticketRepository.findLastByEmployee(employeeId).map(t -> LastTicketDto.builder()
                .id(t.getId())
                .title(t.getTitle())
                .createdAt(t.getCreatedAt())
                .build()).orElse(null);

    }
}
