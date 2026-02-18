package io.github.nelsonsoria.helpdesk.dto.ticket;

import io.github.nelsonsoria.helpdesk.dto.asset.AssetDTO;
import io.github.nelsonsoria.helpdesk.enums.Priority;
import io.github.nelsonsoria.helpdesk.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TicketAssetDto {

    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private Long employee;
    private AssetDTO asset;
    private LocalDateTime createdAt;

}
