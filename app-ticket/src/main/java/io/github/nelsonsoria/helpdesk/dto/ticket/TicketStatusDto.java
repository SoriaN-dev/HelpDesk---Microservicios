package io.github.nelsonsoria.helpdesk.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketStatusDto {
    private Long open;
    private Long inProgress;
    private Long closed;
    private Long highPriority;

}
