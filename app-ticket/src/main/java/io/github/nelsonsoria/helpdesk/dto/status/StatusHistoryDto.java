package io.github.nelsonsoria.helpdesk.dto.status;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class StatusHistoryDto {

    private String oldStatus;
    private String newStatus;
    private LocalDateTime changedAt;
}
