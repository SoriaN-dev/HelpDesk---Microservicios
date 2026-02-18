package io.github.nelsonsoria.helpdesk.dto.ticket;

import io.github.nelsonsoria.helpdesk.dto.employee.CommentDto;
import io.github.nelsonsoria.helpdesk.dto.employee.EmployeeDto;
import io.github.nelsonsoria.helpdesk.dto.status.StatusHistoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDetailsDto {
    private Long id;
    private String title;
    private String description;
    private EmployeeDto employee;
    private LocalDateTime createdAt;

    private List<CommentDto> comments;
    private List<StatusHistoryDto> statusHistories;
}
