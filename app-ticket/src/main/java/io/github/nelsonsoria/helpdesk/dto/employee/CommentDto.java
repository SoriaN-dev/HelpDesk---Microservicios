package io.github.nelsonsoria.helpdesk.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CommentDto {

    private String comment;
    private LocalDateTime createdAt;
}
