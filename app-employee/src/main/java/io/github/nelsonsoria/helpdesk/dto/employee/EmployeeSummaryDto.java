package io.github.nelsonsoria.helpdesk.dto.employee;

import io.github.nelsonsoria.helpdesk.dto.tickets.LastTicketDto;
import io.github.nelsonsoria.helpdesk.dto.tickets.TicketStatsDto;
import io.github.nelsonsoria.helpdesk.dto.tickets.TicketSummaryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EmployeeSummaryDto {
    EmployeeDTO employee;
    TicketSummaryDto tickets;
}
