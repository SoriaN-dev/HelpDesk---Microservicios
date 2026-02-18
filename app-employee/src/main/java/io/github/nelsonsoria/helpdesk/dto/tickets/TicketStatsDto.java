package io.github.nelsonsoria.helpdesk.dto.tickets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketStatsDto {
    private Long open;
    private Long inProgress;
    private Long closed;
    private Long highPriority;

}
