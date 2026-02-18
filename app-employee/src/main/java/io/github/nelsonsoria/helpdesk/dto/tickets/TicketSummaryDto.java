package io.github.nelsonsoria.helpdesk.dto.tickets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TicketSummaryDto {
    private LastTicketDto last;
    private TicketStatsDto stats;
}
