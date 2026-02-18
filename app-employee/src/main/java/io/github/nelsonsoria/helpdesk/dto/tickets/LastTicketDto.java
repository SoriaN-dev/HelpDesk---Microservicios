package io.github.nelsonsoria.helpdesk.dto.tickets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LastTicketDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
}
