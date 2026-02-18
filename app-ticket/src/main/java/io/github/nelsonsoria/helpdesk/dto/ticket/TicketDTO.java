package io.github.nelsonsoria.helpdesk.dto.ticket;

import io.github.nelsonsoria.helpdesk.dto.asset.AssetDTO;
import io.github.nelsonsoria.helpdesk.enums.Priority;
import io.github.nelsonsoria.helpdesk.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class TicketDTO {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private Long employee;
    private Long asset;
    private LocalDateTime createdAt;
}
