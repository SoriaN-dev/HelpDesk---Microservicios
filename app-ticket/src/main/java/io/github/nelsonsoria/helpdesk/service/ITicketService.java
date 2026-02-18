package io.github.nelsonsoria.helpdesk.service;


import io.github.nelsonsoria.helpdesk.dto.ticket.*;

import java.util.List;

public interface ITicketService {

    TicketDetailsDto getTicketDetails(Long ticketid);
    List<TicketAssetDto> getAllTicketWithAsset();
    List<TicketDTO> getAll();
    List<TicketAssetDto> getTicketForEmployee(Long employeeid);
    TicketStatusDto getTicketSummary(Long employeeid);
    LastTicketDto getLastTicket(Long employeeid);
}
